package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Reserva;
import entidades.Servicio;
import appExceptions.ApplicationException;

/**
 * Servlet implementation class borrarServicio
 */
@WebServlet("/borrarServicio")
public class borrarServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrarServicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("nro")==null){
				throw new ApplicationException("Error al recibir el numero de linea a borrar", null);
			} else {
				int nro = Integer.parseInt(request.getParameter("nro"));
				Reserva reservaActual = (Reserva) request.getSession().getAttribute("reservaActual");
				reservaActual.getServicios().remove(nro-1);
				request.getSession().setAttribute("reservaActual", reservaActual);
				response.sendRedirect("agregarServiciosReserva.jsp");
			}
			
		} catch (ApplicationException e) {
			request.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("error404.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		doGet(request, response);
	}

}
