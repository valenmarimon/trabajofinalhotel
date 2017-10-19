package negocio;

import java.sql.SQLException;
import java.util.ArrayList;






import appExceptions.ApplicationException;
import entidades.Reserva;
import entidades.Servicio;
import data.DataReserva;

public class CtrlReserva {
	
private DataReserva catr;
	
	public CtrlReserva(){
		catr = new DataReserva();
	}

	public void crearReserva(Reserva r) throws ApplicationException {
		catr.crearReserva(r);				
	}
	
	public ArrayList<Reserva> listarReservas() throws ApplicationException{    
		return catr.listarReservas();
	}

	public void cancelarReserva(int idreserva) throws ApplicationException {
		catr.cancelarReserva(idreserva);
		
	}

	public void confirmarPago(int idreserva) throws ApplicationException {
		catr.confirmarPago(idreserva);
		
	}

	public void agregarServicios(Reserva reservaActual) throws SQLException, ApplicationException {
		catr.AgregarServicios(reservaActual);
		
	}

}
