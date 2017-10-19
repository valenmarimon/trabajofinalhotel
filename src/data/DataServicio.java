package data;


import java.util.ArrayList;
import java.sql.*;

import appExceptions.ApplicationException;
import entidades.Servicio;

public class DataServicio {

	public void agregarServicio(Servicio s) throws ApplicationException{
		
		ResultSet rs=null;
		PreparedStatement stmtServ =null;
		PreparedStatement stmtPrecio = null;
		
		try {
			stmtServ = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into servicios (nombre_servicio, descripcion_servicio) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmtServ.setString(1, s.getNombre());
			stmtServ.setString(2, s.getDescripcion());
			
			stmtServ.execute();
			
			//Recupero el id asignado por la bd para poder insertar <precio> en tabla "precios_servicios"
			
			rs = stmtServ.getGeneratedKeys();
			if(rs!=null && rs.next()){
				s.setCod_servicio(rs.getInt(1));
				
			}
			stmtPrecio = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Insert into precios_servicios (cod_servicio, fecha_desde, precio)"
					+"values (?,?,?)");
			stmtPrecio.setInt(1, s.getCod_servicio());
						
			stmtPrecio.setDate(2, new java.sql.Date(s.getFecha().getTime()));				
			stmtPrecio.setFloat(3, s.getPrecio());
			stmtPrecio.execute();
			
			
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConn().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al recuperar servicio en la base de datos", e);
			}
			throw new ApplicationException("Error al registrar nuevo servicio en la base de datos", e);
		} finally {
			try {
			if(stmtServ!=null) stmtServ.close();
			if(stmtPrecio!=null) stmtPrecio.close();
			if(rs!=null) rs.close();
			FactoryConexion.getInstancia().getConn().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
		}
	
}
	
public ArrayList<Servicio> listarServicios() throws ApplicationException {
		
		ResultSet rs=null;
		
		Statement stmt=null;
		
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		
		/* select s.*, ps.precio
		from servicios s
		inner join precios_servicios ps
		      on s.cod_servicio=ps.cod_servicio
		inner join (
		          select cod_servicio, max(fecha_desde) ult_fec
		          from precios_servicios
		          where fecha_desde <=current_date
		          group by cod_servicio
		          ) val_act
		          on ps.cod_servicio=val_act.cod_servicio
		          and ps.fecha_desde=val_act.ult_fec; */
		
  try {
	     stmt = FactoryConexion.getInstancia().getConn().createStatement();
	   
	     rs = stmt.executeQuery(
	    		 "select servicios.cod_servicio, servicios.nombre_servicio, servicios.descripcion_servicio, precios_servicios.precio"
	     		+ " from servicios"
	     		+ " inner join precios_servicios"
	     		+ " on servicios.cod_servicio=precios_servicios.cod_servicio"
	     		+ " inner join (select precios_servicios.cod_servicio, max(precios_servicios.fecha_desde) 'ult_fec'"
	     		+ " from precios_servicios"
	     		+ " where precios_servicios.fecha_desde <= current_date()"
	     		+ " group by precios_servicios.cod_servicio)val_act"
	     		+ " on precios_servicios.cod_servicio=val_act.cod_servicio and precios_servicios.fecha_desde=val_act.ult_fec ");
	        
	        while (rs.next())
	         {
	           
	            Servicio s = new Servicio();
	            s.setCod_servicio(rs.getInt("cod_servicio"));
	            s.setNombre(rs.getString("nombre_servicio"));
	            s.setDescripcion(rs.getString("descripcion_servicio"));
	            s.setPrecio(rs.getFloat("precio"));
	       
     
	        	servicios.add(s);
	        	
	            
	         }
	      
	    }catch (SQLException e) {
	    	throw new ApplicationException("Error lectura en la base de datos", e);
	    } finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexion en la base de datos", e);
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		
		return servicios;
	}




	public void borrarServicio(int codServ) throws ApplicationException {
		
			PreparedStatement stmtPS=null;
			PreparedStatement stmtS=null;
		
		try { // borro primero de la tabla precios_servicio
			stmtPS=FactoryConexion.getInstancia().getConn().prepareStatement(
					  "delete from precios_servicio where cod_servicio=?"
					);
			stmtPS.setInt(1, codServ);
			stmtPS.execute();
			
			
		} catch (SQLException e) {
			throw new ApplicationException("Error al eliminar servicio de la base de datos", e);
		} finally{
			
			try {
				if(stmtPS != null) stmtPS.close();
				
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexion en la base de datos", e);
			}
			
			
		}
		
		try { // luego la tabla servicios
			stmtS=FactoryConexion.getInstancia().getConn().prepareStatement(
					  "delete from servicios where cod_servicio=?"
					);
			stmtS.setInt(1, codServ);
			stmtS.execute();
			
			
		} catch (SQLException e) {
			throw new ApplicationException("Error escritura en la base de datos", e);
		} finally{
			
			try {
				
				if(stmtS != null) stmtS.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar en la base de datos", e);
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
		
		
	}

	public Servicio getByCodigo(int codServicio) throws ApplicationException {

		ResultSet rs=null;		
		PreparedStatement stmt=null;		
		Servicio servicio = new Servicio();
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
	    		 "select servicios.cod_servicio, servicios.nombre_servicio, servicios.descripcion_servicio, precios_servicios.precio"
	     		+ " from servicios"
	     		+ " inner join precios_servicios"
	     		+ " on servicios.cod_servicio=precios_servicios.cod_servicio"
	     		+ " inner join (select precios_servicios.cod_servicio, max(precios_servicios.fecha_desde) 'ult_fec'"
	     		+ " from precios_servicios"
	     		+ " where precios_servicios.fecha_desde <= current_date()"
	     		+ " group by precios_servicios.cod_servicio)val_act"
	     		+ " on precios_servicios.cod_servicio=val_act.cod_servicio and precios_servicios.fecha_desde=val_act.ult_fec "
	     		+ "where servicios.cod_servicio = ?");
			stmt.setInt(1, codServicio);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){	
				servicio.setCod_servicio(rs.getInt("cod_servicio"));
				servicio.setNombre(rs.getString("nombre_servicio"));
				servicio.setDescripcion(rs.getString("descripcion_servicio"));
				servicio.setPrecio(rs.getFloat("precio"));	       
     
	         }
	      
	    }catch (SQLException e) {
	    	throw new ApplicationException("Error lectura en la base de datos", e);
	    } finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexion en la base de datos", e);
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		
		return servicio;
	}


	public void modificarServicio(Servicio s)throws ApplicationException {
		
		ResultSet rs=null;
		PreparedStatement stmtServ =null;
		//PreparedStatement stmtPrecio =null;
		
		
		try {
			stmtServ = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update servicios set nombre_servicio=?, descripcion_servicio=? where cod_servicio=?"
					);
			stmtServ.setString(1, s.getNombre());
			stmtServ.setString(2, s.getDescripcion());
			stmtServ.setInt(3, s.getCod_servicio());
			stmtServ.execute();
			/*
			stmtPrecio = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Insert into precios_servicios (cod_servicio, fecha_desde, precio)"
					+"values (?,?,?)");
			stmtPrecio.setInt(1, s.getCod_servicio());						
			stmtPrecio.setDate(2, new java.sql.Date(s.getFecha().getTime()));				
			stmtPrecio.setFloat(3, s.getPrecio());
			stmtPrecio.execute();*/
			
			
			
			
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConn().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al recuperar servicio en la base de datos", e);
			}
			throw new ApplicationException("Error al actualizar servicio en la base de datos", e);
		} finally {
			try {
			if(stmtServ!=null) stmtServ.close();
			//if(stmtPrecio!=null) stmtPrecio.close();			
			if(rs!=null) rs.close();
			FactoryConexion.getInstancia().getConn().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
		}
		
		
		
	}

	public void modificarPrecioServicio(Servicio s) throws ApplicationException{
		
		ResultSet rs=null;
		PreparedStatement stmt=null;		
		
		try {			
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Insert into precios_servicios (cod_servicio, fecha_desde, precio) values (?,?,?)"
					);
			stmt.setInt(1, s.getCod_servicio());
			stmt.setDate(2, new java.sql.Date(s.getFecha().getTime()));
			stmt.setFloat(3, s.getPrecio());	
			stmt.execute();	
			
			
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConn().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al recuperar Tipo habitacion en la base de datos", e);
			}
			throw new ApplicationException("Error al Agregar Precio Tipo habitacion en la base de datos", e);
		} finally {
			try {
			if(stmt!=null) stmt.close();
			
					
			if(rs!=null) rs.close();
			FactoryConexion.getInstancia().getConn().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
		}
		
	}

	public ArrayList<Servicio> getByDescripcion(String descripcion,int desde, int hasta) throws ApplicationException {
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		Servicio serv;
		ResultSet rs= null;
		PreparedStatement stmt = null;
		try{
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"select servicios.cod_servicio,servicios.nombre_servicio,precios_servicios.precio "
					+ "from servicios "
					+ "inner join precios_servicios "
					+ "on precios_servicios.cod_servicio=servicios.cod_servicio "
					+ "inner join (select precios_servicios.cod_servicio, max(precios_servicios.fecha_desde) 'fecha_desde'"
					+ "	from precios_servicios"
					+ "	where precios_servicios.fecha_desde <= current_date()"
					+ "	group by precios_servicios.cod_servicio)val_ser "
					+ "on precios_servicios.cod_servicio=val_ser.cod_servicio and precios_servicios.fecha_desde=val_ser.fecha_desde "
					+ "where servicios.nombre_servicio like ?"
					+ "group by servicios.cod_servicio"
					+ " order by servicios.nombre_servicio"
					+ " limit ?,?");
			stmt.setString(1, "%"+descripcion+"%");
			stmt.setInt(2, desde);
			stmt.setInt(3, hasta);
			rs=stmt.executeQuery();
			while(rs.next()){
				serv = new Servicio();
				serv.setCod_servicio(rs.getInt("cod_servicio"));
				serv.setNombre(rs.getString("nombre_servicio"));
				serv.setPrecio(rs.getFloat("precio"));
				servicios.add(serv);
			}
			
		} catch (SQLException e){
			throw new ApplicationException("Error al obtener la lista de servicios desde la base de datos", null);			
			
		} finally{
			try {
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConn().close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}
		return servicios;
	}




}
