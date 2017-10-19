package negocio;


import appExceptions.ApplicationException;
import data.DataPersona;
import entidades.Persona;
import entidades.Cliente;


public class CtrlPersona {
	
	
	private DataPersona catalogo;
	
	public CtrlPersona(){
		catalogo = new DataPersona();
	}
	
	public void agregar(Cliente cli)throws ApplicationException {
		
			catalogo.addCliente(cli);	
	}
	
	public boolean verificarUser(String usuario) throws ApplicationException{
		return catalogo.verificarUser(usuario);
	}
	public boolean verificarDni(int dni) throws ApplicationException{
		return catalogo.verificarDni(dni);
	}
	
	public Persona getByUsuarioyContrase�a(String usuario, String contrase�a) throws ApplicationException  {
		return catalogo.getByUsuarioyContrase�a(usuario, contrase�a);
	}

	public Persona getByDni(int dni) throws ApplicationException {
		return catalogo.getByDni(dni);
	}
	
	
	
	/*
	public void delete(Persona p){
		catalogo.delete(p);
	}
	
	public Persona getByDni(int dni){
		return catalogo.getByDni(dni);
	}
	
	public ArrayList<Persona> Listar(){    
		return catalogo.listar();
	}
	
	public void modif(Persona newPer) throws ApplicationException{
		catalogo.update(newPer);
	} */

}
