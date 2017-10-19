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

/**
 * Servlet implementation class borrarReservaServicios
 */
@WebServlet("/borrarReservaServicios")
public class borrarReservaServicios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrarReservaServicios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Reserva reservaActual = (Reserva) request.getSession().getAttribute("reservaActual");
    	reservaActual.getServicios().clear();
    	request.getSession().setAttribute("reservaActual", reservaActual);
    	//request.getSession().removeAttribute("reservaActual");
		response.sendRedirect("agregarServiciosReserva.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
