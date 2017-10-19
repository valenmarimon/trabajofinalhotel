package ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlServicio;
import appExceptions.ApplicationException;
import entidades.Servicio;

/**
 * Servlet implementation class ModificarServicio
 */
@WebServlet("/ModificarServicio")
public class ModificarServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarServicio() {
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
		
		int cod_servicio = Integer.parseInt(request.getParameter("codservicio"));
		Servicio s = new Servicio();
		CtrlServicio ctrlS = new CtrlServicio();
		try {
			s = ctrlS.getByCodigo(cod_servicio);
		} catch (ApplicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*if(request.getParameter("fecha") != "" && request.getParameter("precio") != ""){ 
			
			String fechaString = ((String)request.getParameter("fecha")).trim();
			Date fecha_desde = null;	
			try {
				fecha_desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
		
			s.setFecha(fecha_desde);
			s.setPrecio(Float.parseFloat(request.getParameter("precio")));
		 } */
		
		
		s.setNombre(request.getParameter("nombre"));
		s.setDescripcion(request.getParameter("descripcion"));
		

		
	try {
		ctrlS.modificarServicio(s);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		request.setAttribute("mensaje", "Servicio modificado correctamente");
		request.getRequestDispatcher("admServicios.jsp").forward(request, response);
	
	}

}
