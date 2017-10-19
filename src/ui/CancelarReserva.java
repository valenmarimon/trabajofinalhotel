package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import appExceptions.ApplicationException;
import negocio.CtrlReserva;

/**
 * Servlet implementation class CancelarReserva
 */
@WebServlet("/CancelarReserva")
public class CancelarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarReserva() {
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
		String persona = request.getParameter("persona");
	    CtrlReserva ctrlR = new CtrlReserva();
	    try {
			ctrlR.cancelarReserva(idreserva);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    request.setAttribute("mensaje", "Reserva cancelada correctamente");
	    if(persona.equals("cliente")){	    
		request.getRequestDispatcher("reservar.jsp").forward(request, response);
	    }else{request.getRequestDispatcher("reservas.jsp").forward(request, response); }
	}

}
