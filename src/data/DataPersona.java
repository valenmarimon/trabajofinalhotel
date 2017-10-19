package data;


import java.sql.*;
import entidades.Persona;
import entidades.Cliente;
import entidades.Recepcionista;
import appExceptions.ApplicationException;

public class DataPersona {

	public DataPersona(){
		
	}
	
	
	public void addCliente(Cliente cli) throws ApplicationException{		
		PreparedStatement stmt=null;		
		ResultSet rs=null;
	    
	    	
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into personas (dni, nombre, apellido, usuario, contraseña, email, tipo) values (?,?,?,?,?,?,?)" );
			stmt.setInt(1, cli.getDni());
			stmt.setString(2, cli.getNombre());
			stmt.setString(3, cli.getApellido());
			stmt.setString(4, cli.getUsuario());
			stmt.setString(5, cli.getContraseña());
			stmt.setString(6, cli.getEmail());
			stmt.setString(7, "cliente");
			stmt.execute();

			
		} catch (SQLException e) {
			throw new ApplicationException("Error agregar un cliente en la base de datos", e);
			
		}
		finally{
			
			try {
				if(rs!=null ) rs.close();
				if(stmt != null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
			
		}
	    
	}
	


	public boolean verificarUser(String usuario) throws ApplicationException{ 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from personas where usuario=?");
			stmt.setString(1, usuario);
			rs=stmt.executeQuery();
			if(rs.getRow()==0){
				return true;
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al recuperar nombres de usuario de la base de datos", e);
		}finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConn().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		return false;
		
	}
	
	

	
	public boolean verificarDni(int dni) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from personas where dni=?");
			stmt.setInt(1, dni);
			rs=stmt.executeQuery();
			if(rs.getRow()==0){
				return true;
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al recuperar dni de clientes de la base de datos", e);
		}finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConn().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		return false;
		
	}


	public Persona getByUsuarioyContraseña(String usuario, String contraseña) throws ApplicationException {
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Persona p =null;
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select dni, nombre, apellido, usuario, contraseña, direccion, email, tipo from personas where usuario=? and contraseña=?"
					);
			stmt.setString(1, usuario);
			stmt.setString(2, contraseña);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
				
				
				 if (rs.getString("direccion")==null){
					 
					 Cliente c = new Cliente();
					 
					 	c.setDni(rs.getInt("dni"));
					 	c.setNombre(rs.getString("nombre"));
					 	c.setApellido(rs.getString("apellido"));	
						c.setUsuario(rs.getString("usuario"));
						c.setContraseña(rs.getString("contraseña"));
						c.setEmail(rs.getString("email"));
						c.setTipo(rs.getString("tipo"));
				
					p = c;
				 } else {
					 
					 	Recepcionista r = new Recepcionista();
					 	
					 	r.setUsuario(rs.getString("usuario"));
						r.setApellido(rs.getString("apellido"));
						r.setDireccion(rs.getString("direccion"));
						r.setDni(rs.getInt("dni"));						
						r.setNombre(rs.getString("nombre"));						
						r.setUsuario(usuario);
						
						p=r;
					}
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al recuperar usuario de la base de datos", e.getCause());
		}
		finally
		{
			try {
				if(rs!=null)rs.close();				
				if(stmt!=null) stmt.close();				
				FactoryConexion.getInstancia().getConn().setAutoCommit(true);
			} catch (SQLException e) {
				throw new ApplicationException("Error al liberar recursos de la base de datos", e.getCause());
			}
			FactoryConexion.getInstancia().releaseConn();
			
		}
		return p;
		
		
	}


	public Persona getByDni(int dni) throws ApplicationException {
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Persona p=null;
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select dni, nombre, apellido, usuario, contraseña, direccion, email, tipo from personas where dni = ?"
					);
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
				p=new Persona();
				p.setDni(rs.getInt("dni"));				
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setUsuario(rs.getString("usuario"));
				p.setTipo(rs.getString("tipo"));
				
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error lectura base de datos", e);
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error cerrar conexion en base de datos", e);
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		return p;
	}



}


	
