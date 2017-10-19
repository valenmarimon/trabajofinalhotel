package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import negocio.CtrlPersona;
import entidades.Persona;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/IniciarSesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String usuario = request.getParameter("usuario");
		String contraseña = request.getParameter("contrasena");
		
		CtrlPersona ctrl = new CtrlPersona();
		Persona p = new Persona();
		 
		try {
			p = ctrl.getByUsuarioyContraseña(usuario, contraseña);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(p == null){
			request.setAttribute("mensaje", usuario);
			request.getRequestDispatcher("login.jsp").forward(request, response);}
		else{ 
			
			request.getSession().setAttribute("userSession", p);
			response.sendRedirect("index.jsp");
		}
	}

	
	
	
	
	
	
}
