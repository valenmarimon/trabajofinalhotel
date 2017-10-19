package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import entidades.Servicio;
import data.DataServicio;

public class CtrlServicio {
	
	DataServicio cat;
	
	public CtrlServicio(){
		cat = new DataServicio();
	}

	public void agregarServicio(Servicio s) throws ApplicationException {		
		cat.agregarServicio(s);		
		
	}
	
	public ArrayList<Servicio> listarServicios() throws ApplicationException{    
		return cat.listarServicios();
	}

	public void borrarServicio(int codServ) throws ApplicationException {
		cat.borrarServicio(codServ);
		
	}
	
	public Servicio getByCodigo(int codServicio) throws ApplicationException {
	   return cat.getByCodigo(codServicio);
	    
	}

	public void modificarServicio(Servicio s) throws ApplicationException {
		
		cat.modificarServicio(s);
		
	}

	public void modificarPrecioServicio(Servicio s) throws ApplicationException{
		cat.modificarPrecioServicio(s);
	}

	public ArrayList<Servicio> getByDescripcion(String descripcion, int desde,int hasta) throws ApplicationException {
		return cat.getByDescripcion(descripcion,desde,hasta);

	}
}
