package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import data.DataTipoHabitacion;
import entidades.TipoHabitacion;

public class CtrlTipoHabitacion {
	

DataTipoHabitacion DTH;
	
public CtrlTipoHabitacion(){
		DTH = new DataTipoHabitacion();
	}

	public ArrayList<TipoHabitacion> listarTiposHabitacion () throws ApplicationException{
		return DTH.listarTiposHabitacion();
	}

	public void modificarPrecioTipoHabitacion(TipoHabitacion th)throws ApplicationException {
		
		DTH.modificarPrecioTipoHabitacion(th);
		
	}

}
