package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlReserva;
import appExceptions.ApplicationException;

/**
 * Servlet implementation class ConfirmarPagoReserva
 */
@WebServlet("/ConfirmarPagoReserva")
public class ConfirmarPagoReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarPagoReserva() {
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
		int idreserva = Integer.parseInt(request.getParameter("idreserva"));
	    CtrlReserva ctrlR = new CtrlReserva();
	    try {
			ctrlR.confirmarPago(idreserva);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    request.setAttribute("mensaje", "Reserva registrada como paga correctamente");
		request.getRequestDispatcher("reservas.jsp").forward(request, response);
	}

}
