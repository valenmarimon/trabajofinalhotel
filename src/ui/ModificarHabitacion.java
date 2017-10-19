package ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlHabitacion;
import appExceptions.ApplicationException;
import entidades.Habitacion;

/**
 * Servlet implementation class ModificarHabitacion
 */
@WebServlet("/ModificarHabitacion")
public class ModificarHabitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarHabitacion() {
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
		
		
		Habitacion h = new Habitacion();
		
		int idhab = Integer.parseInt(request.getParameter("id_habitacion"));
		h.setId_habitacion(idhab);
		
		
		h.setNumero(Integer.parseInt(request.getParameter("numero")));
		h.setPiso(Integer.parseInt(request.getParameter("piso")));
		h.setTipo(Integer.parseInt(request.getParameter("tipo_habitacion")));
		
		CtrlHabitacion ctrlH = new CtrlHabitacion();

		
	try {
		ctrlH.modificarHabitacion(h);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		request.setAttribute("mensaje", "Habitacion modificada correctamente");
		request.getRequestDispatcher("admHabitaciones.jsp").forward(request, response);
	}
}
