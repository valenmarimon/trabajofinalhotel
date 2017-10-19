package ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Habitacion;
import entidades.Reserva;
import negocio.CtrlHabitacion;
import negocio.CtrlReserva;


/**
 * Servlet implementation class RegistrarReserva
 */
@WebServlet("/RegistrarReserva")
public class RegistrarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				
		// OBTENGO DEL FORMULARIO FECHA DESDE Y FECHA HASTA		 
		String fechaFROMString = ((String)request.getParameter("from")).trim();
		String fechaTOString = ((String)request.getParameter("to")).trim();
		Date fecha_desde = null;
		Date fecha_hasta = null;
		try {
			fecha_desde = new SimpleDateFormat("MM/dd/yyyy").parse(fechaFROMString);
			fecha_hasta = new SimpleDateFormat("MM/dd/yyyy").parse(fechaTOString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// OBTENGO DEL FORMULARIO(si es que no estan vacias) LAS CANTIDADES INGRESADAS POR CADA TIPO DE HABITACION
		int cant_sol_doblesingle=0;
		int cant_sol_deluxe=0;
		int cant_sol_dobledeluxe=0;
		
		if(!request.getParameter("doblesingle").equals("")){
			cant_sol_doblesingle = Integer.parseInt(request.getParameter("doblesingle"));
		}
		
		if(!request.getParameter("deluxe").equals("")){
			cant_sol_deluxe = Integer.parseInt(request.getParameter("deluxe"));
		}
		
		if(!request.getParameter("dobledeluxe").equals("")){
			cant_sol_dobledeluxe = Integer.parseInt(request.getParameter("dobledeluxe"));
		}
		
		
		
		
		 
		// PRIMERO OBTENGO COLECCION DE HABITACIONES QUE ¡NO! ESTAN RESERVADAS EN LA FECHA INGRESADA (LAS DISPONIBLES)
		
			CtrlHabitacion ctrlH = new CtrlHabitacion();
			
			ArrayList<Habitacion> habitaciones_disponibles = new ArrayList<Habitacion>();			
			try {
				habitaciones_disponibles = ctrlH.getHabitacionesDisponibles(fecha_desde,fecha_hasta);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// CUENTO LA CANTIDAD DE CADA TIPO DE HABITACIONES(DISPONIBLES)
			
			int cant_disp_doblesingle =0;
			int cant_disp_deluxe =0;
			int cant_disp_dobledeluxe =0;
			
			for (int indice = 0; indice < habitaciones_disponibles.size(); indice++){
				
				switch (habitaciones_disponibles.get(indice).getTipo()) {
				case 1:
					cant_disp_doblesingle++;
					break;
				case 2:
					cant_disp_deluxe++;
					break;
				case 3:
					cant_disp_dobledeluxe++;
					break;
				}
			}
					
			
		// OBTENIDA LAS 2 VARIBLES(DISPONIBLES Y SOLICITADAS) VALIDO :		
			ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
			String mensaje = "";
			
			if(cant_disp_doblesingle>=cant_sol_doblesingle && cant_sol_doblesingle!=0){
				// CREO Y AGREGO A LA COLECCION DE HABITACIONES(DE LA RESERVA) LAS CANTIDADES SOLICITADAS								
				int i=0;
				int j=0;
				while(i < habitaciones_disponibles.size() && j < cant_sol_doblesingle){
					if(habitaciones_disponibles.get(i).getTipo()==1){
						habitaciones.add(habitaciones_disponibles.get(i));
						j++;
					}
					i++;
				}
				
			}else{ if(cant_sol_doblesingle!=0){ mensaje+="No existe cantidad disponible habitación Doble Single\n";} }
			
			if(cant_disp_deluxe>=cant_sol_deluxe && cant_sol_deluxe!=0){
				// CREO Y AGREGO A LA COLECCION DE HABITACIONES(DE LA RESERVA) LAS CANTIDADES SOLICITADAS								
				int i=0;
				int j=0;
				while(i < habitaciones_disponibles.size() && j < cant_sol_deluxe){
					if(habitaciones_disponibles.get(i).getTipo()==2){
						habitaciones.add(habitaciones_disponibles.get(i));
						j++;
					}
					i++;
				}
				
			}else{ if(cant_sol_deluxe!=0){ mensaje+="No existe cantidad disponible habitación Deluxe\n";} }
			
			if(cant_disp_dobledeluxe>=cant_sol_dobledeluxe && cant_sol_dobledeluxe!=0){
				// CREO Y AGREGO A LA COLECCION DE HABITACIONES(DE LA RESERVA) LAS CANTIDADES SOLICITADAS								
				int i=0;
				int j=0;
				while(i < habitaciones_disponibles.size() && j < cant_sol_dobledeluxe){
					if(habitaciones_disponibles.get(i).getTipo()==3){
						habitaciones.add(habitaciones_disponibles.get(i));
						j++;
					}
					i++;
				}
				
			}else{ if(cant_sol_dobledeluxe!=0){ mensaje+="No existe cantidad disponible habitación Doble Deluxe\n";} }
		
		/*  ESTO VA EN OTRO SERVLET
		// CREO UNA COLECCIÓN DE SERVICIOS 
		CtrlServicio ctrlS = new CtrlServicio();
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		
		// Y VOY AGREGANDO EN ÉL CADA SERVICIO MARCADO POR EL CLIENTE
		for (int indice = 0; indice < ctrlS.listarServicios().size(); indice++){
			
			int checked = Integer.parseInt(request.getParameter("indice"));
			if(checked!=0){
				    Servicio s = new Servicio();
					s = ctrlS.getByCodigo(ctrlS.listarServicios().get(indice).getCod_servicio());
					servicios.add(s);
			}
		}   */
		
		if(!mensaje.equals("")){
			
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("realizarReserva.jsp").forward(request, response);
			
		}else{		
			
			// OBTENGO CLIENTE DE LA VARIABLE DE SESIÓN
		
		Cliente cliente = (Cliente) request.getSession().getAttribute("userSession");
		
		
		// CREO RESERVA. MANDO TODOS LOS DATOS OBTENIDOS ANTERIORMENTE
		
		CtrlReserva ctrlR = new CtrlReserva();
		Reserva r = new Reserva();
		
		r.setCliente(cliente);		
		r.setFecha_desde(fecha_desde);
		r.setFecha_hasta(fecha_hasta);
		r.setHabitaciones(habitaciones);
		
		
		try {
			ctrlR.crearReserva(r);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("reserva", r);
		request.getRequestDispatcher("agregarServicios.jsp").forward(request, response);
		}
				 
	}

}