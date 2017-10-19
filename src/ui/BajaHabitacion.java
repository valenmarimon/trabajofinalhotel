package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import negocio.CtrlHabitacion;

/**
 * Servlet implementation class BajaHabitacion
 */
@WebServlet("/BajaHabitacion")
public class BajaHabitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BajaHabitacion() {
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

		int idhab = Integer.parseInt(request.getParameter("numero"));
		 CtrlHabitacion ctrl = new CtrlHabitacion();
		 
		 try {
			ctrl.borrarHabitacion(idhab);
		} catch (ApplicationException e) {
			request.setAttribute("mensaje", e);
			request.getRequestDispatcher("admHabitaciones.jsp").forward(request, response);
		}
		 request.setAttribute("mensaje", "habitacion eliminada correctamente");
		 response.sendRedirect("admHabitaciones.jsp");
	}

}
