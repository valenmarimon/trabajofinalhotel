package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlPersona;
import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Persona;

/**
 * Servlet implementation class RegistrarUsuario
 */
@WebServlet("/RegistrarUsuario")
public class RegistrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarUsuario() {
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
		
		CtrlPersona ctrl= new CtrlPersona();
		
		
		int dni = Integer.parseInt(request.getParameter("dni"));
		
		Persona p = new Persona();
		
		try {
			p = ctrl.getByDni(dni);
		} catch (ApplicationException e1) {
			request.setAttribute("mensaje", e1);
			request.getRequestDispatcher("registrarse.jsp").forward(request, response);
		}
		
		if(p==null){
		
		
	
		
					Cliente c= new Cliente();	
				
					c.setUsuario(request.getParameter("usuario"));
					c.setContraseña(request.getParameter("contrasena"));		
					c.setNombre(request.getParameter("nombre"));
					c.setApellido(request.getParameter("apellido"));
					c.setDni(Integer.parseInt(request.getParameter("dni")));
					c.setEmail(request.getParameter("email"));
					
					try {
						ctrl.agregar(c);
					} catch (ApplicationException e) {
						request.setAttribute("mensaje", e);
						request.getRequestDispatcher("registrarse.jsp").forward(request, response);
					}
					
					
					
					 
					try {
						p = ctrl.getByUsuarioyContraseña(request.getParameter("usuario"), request.getParameter("contrasena"));
					} catch (ApplicationException e) {
						request.setAttribute("mensaje", e);
						request.getRequestDispatcher("registrarse.jsp").forward(request, response);
					}
					
					request.getSession().setAttribute("userSession", p);
					response.sendRedirect("index.jsp");
					
		}else{
			

			request.setAttribute("mensaje", "Ya existe una persona con ese dni");
			request.getRequestDispatcher("registrarse.jsp").forward(request, response);
		}
					
					
				
		

	}
}
