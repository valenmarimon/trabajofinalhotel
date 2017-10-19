package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlReserva;
import entidades.Reserva;
import entidades.Servicio;
import appExceptions.ApplicationException;

/**
 * Servlet implementation class confirmarReservaServicios
 */
@WebServlet("/confirmarReservaServicios")
public class confirmarReservaServicios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmarReservaServicios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getSession().getAttribute("reservaActual")==null){
				throw new ApplicationException("No se encontró un servicios en proceso de reserva en la sesión", null);
			} else {
				Reserva reservaActual = (Reserva) request.getSession().getAttribute("reservaActual");
				CtrlReserva ctrl = new CtrlReserva();
					try {
						ctrl.agregarServicios(reservaActual);
					} catch (SQLException e) {
						throw new ApplicationException("No se encontró un servicios en proceso de reserva en la sesión", null);
					}					
					request.getSession().removeAttribute("serviciosReserva");
					request.getSession().removeAttribute("idReserva");
					request.setAttribute("mensajeConfirmacion", "Sus servicios ha sido registrado con éxito.");
					request.getRequestDispatcher("agregarServiciosReserva.jsp").forward(request, response);
				}	
			
		} catch (ApplicationException e) {
			request.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("agregarServiciosReserva.jsp").forward(request, response);
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
