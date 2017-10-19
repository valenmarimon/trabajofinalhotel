package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import negocio.CtrlReserva;
import negocio.CtrlServicio;
import entidades.Reserva;
import entidades.Servicio;

/**
 * Servlet implementation class AgregarServiciosReserva
 */
@WebServlet("/AgregarServiciosReserva")
public class AgregarServiciosReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarServiciosReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reserva reserva = new Reserva();
		if(request.getSession().getAttribute("reservaActual")==null){
			int idReservaActual =Integer.valueOf(request.getParameter("idreserva"));
			reserva.setId_reserva(idReservaActual);		
		}else{
			Reserva reservaActual = (Reserva) request.getSession().getAttribute("reservaActual");
			reserva = reservaActual;
		}
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		int codigo=Integer.valueOf(request.getParameter("txtCod"));
		
		
		//Reserva reservaActual = (Reserva) request.getSession().getAttribute("reservaActual");
		
		CtrlServicio Ctrl = new CtrlServicio();
		Servicio servicio = new Servicio();
		try {
			servicio = Ctrl.getByCodigo(codigo);
		} catch (ApplicationException e) {
			request.setAttribute("mensajeError",e);			
		}
		if(reserva.getServicios()==null){
			servicios.add(servicio);
			reserva.setServicios(servicios);
		} else {
			
			reserva.getServicios().add(servicio);
					
		}
		request.getSession().setAttribute("reservaActual", reserva);
		response.sendRedirect("agregarServiciosReserva.jsp");
	}
}


