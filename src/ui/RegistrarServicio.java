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

import appExceptions.ApplicationException;
import negocio.CtrlServicio;
import entidades.Servicio;

/**
 * Servlet implementation class RegistrarServicio
 */
@WebServlet("/RegistrarServicio")
public class RegistrarServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarServicio() {
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
		
		Servicio s = new Servicio();
		String errorMsg="";
		

		String fechaString = ((String)request.getParameter("fecha")).trim();
		Date fecha_desde = null;	
		try {
			fecha_desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
			errorMsg+="Error formato fecha \n";
			
		}
		
		
		s.setNombre(request.getParameter("nombre"));
		s.setDescripcion(request.getParameter("descripcion"));
		s.setFecha(fecha_desde);
		try{
			s.setPrecio(Float.parseFloat(request.getParameter("precio")));
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			errorMsg+="El precio debe ser un numero separando decimales con '.'(punto) \n";
			
		}
		
		
		
		CtrlServicio ctrlS = new CtrlServicio();

	if(errorMsg==""){
		try {
			ctrlS.agregarServicio(s);
		} catch (ApplicationException e) {
			e.printStackTrace();
			request.setAttribute("mensaje", e);
			request.getRequestDispatcher("admServicios.jsp").forward(request, response);
		}
		request.setAttribute("mensaje", "Servicio agregado correctamente");
		}else{
			request.setAttribute("mensaje", errorMsg);
		}
		
		request.getRequestDispatcher("admServicios.jsp").forward(request, response);		
	}
}
