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

import negocio.CtrlServicio;

import appExceptions.ApplicationException;
import entidades.Servicio;


/**
 * Servlet implementation class ModificarPrecioServicio
 */
@WebServlet("/ModificarPrecioServicio")
public class ModificarPrecioServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarPrecioServicio() {
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
		

		String fechaString = ((String)request.getParameter("fecha")).trim();
		Date fecha_desde = null;	
		try {
			fecha_desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		s.setCod_servicio(Integer.parseInt(request.getParameter("codservicio")));
		s.setFecha(fecha_desde);
		s.setPrecio(Float.parseFloat(request.getParameter("precio")));
		
				
		CtrlServicio ctrlS = new CtrlServicio();
		try {
			ctrlS.modificarPrecioServicio(s);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		request.setAttribute("mensaje", "Precio modificado correctamente");
		request.getRequestDispatcher("admServicios.jsp").forward(request, response);
	}

}
