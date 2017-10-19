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
 * Servlet implementation class RegistrarHabitacion
 */
@WebServlet("/RegistrarHabitacion")
public class RegistrarHabitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarHabitacion() {
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
		
		
		CtrlHabitacion ctrl = new CtrlHabitacion();

		
		int numero = Integer.parseInt(request.getParameter("numero")); // numero de habitacion
		int piso = Integer.parseInt(request.getParameter("piso")); // numero de piso
		int cod_tipohabitacion = Integer.parseInt(request.getParameter("tipo_habitacion")); // codigo de tipo de habitacion
		
		Habitacion h = new Habitacion();
					
					h.setNumero(numero);
					h.setPiso(piso);
					h.setTipo(cod_tipohabitacion);
					
					try {
						ctrl.agregarHabitacion(h);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
					request.setAttribute("mensaje", "Habitacion registrada");
				    request.getRequestDispatcher("admHabitaciones.jsp").forward(request, response);
				
				
		
		
	}

}
