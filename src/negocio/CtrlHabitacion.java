package negocio;

import java.util.ArrayList;
import java.util.Date;

import appExceptions.ApplicationException;
import data.DataHabitacion;
import entidades.Habitacion;

public class CtrlHabitacion {
	

	private DataHabitacion catalogo;
	
	public CtrlHabitacion(){
		catalogo = new DataHabitacion();
	}

	public void agregarHabitacion(Habitacion h) throws ApplicationException {

		catalogo.agregarHabitacion(h);
		
	}
	
	public ArrayList<Habitacion> listarHabitaciones() throws ApplicationException{    
		return catalogo.listarHabitaciones();
	}

	public void borrarHabitacion(int idhab) throws ApplicationException {

		catalogo.borrarHabitacion(idhab);
		
	}
	
	public Habitacion getById(int idhab) throws ApplicationException {
		   return catalogo.getById(idhab);
		    
		}

	public void modificarHabitacion(Habitacion h)throws ApplicationException {
		  	catalogo.modificarHabitacion(h);
		
	}

	
	public ArrayList<Habitacion> getHabitacionesDisponibles(Date fecha_desde, Date fecha_hasta) throws ApplicationException {
		return catalogo.getHabitacionesDisponibles(fecha_desde,fecha_hasta);
		
	}
	
	
		
	
	

}
