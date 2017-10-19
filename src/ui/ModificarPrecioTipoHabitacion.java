package ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import negocio.CtrlTipoHabitacion;
import appExceptions.ApplicationException;

import entidades.TipoHabitacion;

/**
 * Servlet implementation class ModificarPrecioTipoHabitacion
 */
@WebServlet("/ModificarPrecioTipoHabitacion")
public class ModificarPrecioTipoHabitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarPrecioTipoHabitacion() {
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
		
		
		
		TipoHabitacion th = new TipoHabitacion();
		

		String fechaString = ((String)request.getParameter("fecha")).trim();
		Date fecha_desde = null;	
		try {
			fecha_desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		th.setCod_th(Integer.parseInt(request.getParameter("tipo_habitacion")));
		th.setFecha(fecha_desde);
		try{
			
		th.setPrecio_th(Float.parseFloat(request.getParameter("precio")));
		
		 } catch (NumberFormatException nfe) {     
			 //request.setAttribute("mensaje", nfe.getMessage());
			 System.out.println(nfe.getMessage());
		 }
		
		CtrlTipoHabitacion ctrlTH = new CtrlTipoHabitacion();

		
	try {
		ctrlTH.modificarPrecioTipoHabitacion(th);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		request.setAttribute("mensaje", "Precio modificado correctamente");
		request.getRequestDispatcher("cambiarPrecioTipoHabitacion.jsp").forward(request, response);
	}

}
