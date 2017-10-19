package data;

import entidades.Habitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import appExceptions.ApplicationException;

public class DataHabitacion {

	public void agregarHabitacion(Habitacion h) throws ApplicationException {
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
	
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into habitaciones(numero, piso, cod_th) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, h.getNumero());
			stmt.setInt(2, h.getPiso());
			stmt.setInt(3, h.getTipo());
			stmt.execute();

		} catch (SQLException e) {
			throw new ApplicationException("Error al agregar habitacion en base de datos", e);
		}
		finally{
			
			try {
				if(rs!=null ) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error cerrar conexion en base de datos", e);
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
		
		
	}

	public ArrayList<Habitacion> listarHabitaciones() throws ApplicationException {
		
		ResultSet rs=null;
		Statement stmt=null;
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		
		try {
	        stmt = FactoryConexion.getInstancia().getConn().createStatement();
	   
	        rs = stmt.executeQuery("select id_habitacion, numero, piso, cod_th from habitaciones");
	        
	        while (rs.next())
	         {
	           
	            Habitacion h = new Habitacion();
	            h.setId_habitacion(rs.getInt("id_habitacion"));
	            h.setNumero(rs.getInt("numero"));
	            h.setPiso(rs.getInt("piso"));
	            h.setTipo(rs.getInt("cod_th"));
	            
	        	habitaciones.add(h);
	            
	         }
	      
	    }catch (SQLException e) {
	    	throw new ApplicationException("Error lectura habitaciones en base de datos", e);
	    } finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error cerrar conexion en base de datos", e);
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		
		return habitaciones;
	}

	public void borrarHabitacion(int idhab) throws ApplicationException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					  "delete from habitaciones where id_habitacion=?"
					);
			stmt.setInt(1, idhab);
			stmt.execute();
			
		} catch (SQLException e) {
			throw new ApplicationException("Error al eliminar habitacion de la base de datos", e);
		} finally{
			
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexion base de datos", e);
				
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	}

	public Habitacion getById(int idhab) throws ApplicationException {
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Habitacion h = null;
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_habitacion, numero, piso, cod_th from habitaciones where id_habitacion = ?"
					);
			stmt.setInt(1, idhab);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
				h = new Habitacion();
				h.setId_habitacion(rs.getInt("id_habitacion"));
				h.setNumero(rs.getInt("numero"));
				h.setPiso(rs.getInt("piso"));
				h.setTipo(rs.getInt("cod_th"));
				
				
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
		return h;
	}

	public void modificarHabitacion(Habitacion h)throws ApplicationException {
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update habitaciones set numero=?, piso=?, cod_th=? where id_habitacion =?"
					);
			stmt.setInt(1, h.getNumero());
			stmt.setInt(2, h.getPiso());
			stmt.setInt(3, h.getTipo());
			stmt.setInt(4, h.getId_habitacion());
			
		
			stmt.execute();
			
			
			
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConn().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al recuperar habitacion en la base de datos", e);
			}
			throw new ApplicationException("Error al modificar habitacion en la base de datos", e);
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
	
	

	public ArrayList<Habitacion> getHabitacionesDisponibles(Date fecha_desde,Date fecha_hasta) throws ApplicationException {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		ArrayList<Habitacion> habitaciones_disponibles = new ArrayList<Habitacion>();
		
		
		/*
		 FALTA EL OR ID_ESTADO = ...
		select `HABITACIONES`.*
from `HABITACIONES`
where habitaciones.`ID_HABITACION` NOT IN
      ( select reservas.`ID_HABITACION`
        from reservas
        where
             reservas.`FECHA_DESDE`> fdi and fhi BETWEEN reservas.`FECHA_DESDE`
             AND reservas.`FECHA_HASTA`
             OR
             fdi BETWEEN reservas.`FECHA_DESDE` AND reservas.`FECHA_HASTA` AND
             fhi BETWEEN reservas.`FECHA_DESDE` AND reservas.`FECHA_HASTA`
             OR
             fdi BETWEEN reservas.`FECHA_DESDE` AND reservas.`FECHA_HASTA` AND
             reservas.`FECHA_HASTA`< fhi
             OR
             reservas.`FECHA_DESDE`>= fdi AND reservas.`FECHA_HASTA`<= fhi
        )
				*/
			
	  try {
		  stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					  "select habitaciones.id_habitacion, habitaciones.numero, habitaciones.piso, habitaciones.cod_th"
					+ " from habitaciones"
					+ " where habitaciones.id_habitacion NOT IN"
					+ "		(select reservas_habitaciones.id_habitacion"
					+ "		 from reservas_habitaciones"
					+ "		 inner join reservas"
					+ "		 on reservas.id_reserva=reservas_habitaciones.id_reserva"
					+ "		 where (reservas.id_estado=1 or reservas.id_estado=2) and("
					+ "			reservas.fecha_desde> ? and ? BETWEEN reservas.fecha_desde AND reservas.fecha_hasta"
					+ "			OR"
					+ "			? BETWEEN reservas.fecha_desde AND reservas.fecha_hasta AND"
					+ " 		? BETWEEN reservas.fecha_desde AND reservas.fecha_hasta"
					+ "			OR"
					+ "			? BETWEEN reservas.fecha_desde AND reservas.fecha_hasta AND"
					+ "			reservas.fecha_hasta< ?"
					+ "			OR"
					+ "			reservas.fecha_desde>= ? AND reservas.fecha_hasta<= ?))"
					);
		    stmt.setDate(1, new java.sql.Date(fecha_desde.getTime()));
			stmt.setDate(2, new java.sql.Date(fecha_hasta.getTime()));
			
			stmt.setDate(3, new java.sql.Date(fecha_desde.getTime()));
			stmt.setDate(4, new java.sql.Date(fecha_hasta.getTime()));
			
			stmt.setDate(5, new java.sql.Date(fecha_desde.getTime()));
			stmt.setDate(6, new java.sql.Date(fecha_hasta.getTime()));
			
			stmt.setDate(7, new java.sql.Date(fecha_desde.getTime()));
			stmt.setDate(8, new java.sql.Date(fecha_hasta.getTime()));
			 
				
			rs = stmt.executeQuery();
			
			
		        while (rs.next())
		         {
		        	
		        		Habitacion h = new Habitacion();
			            h.setId_habitacion(rs.getInt("id_habitacion"));
			            h.setNumero(rs.getInt("numero"));
			            h.setPiso(rs.getInt("piso"));
			            h.setTipo(rs.getInt("cod_th"));
			            
			            habitaciones_disponibles.add(h);        	
		         }
		      
		    }catch (SQLException e) {
		    	throw new ApplicationException("Error lectura en base de datos", e);
		    } finally
			{
				try {
					if(rs!=null)rs.close();
					if(stmt!=null) stmt.close();
				} catch (SQLException e) {
					throw new ApplicationException("Error cerrar conexion en base de datos", e);
				}
				FactoryConexion.getInstancia().releaseConn();
			}
			
			
		return habitaciones_disponibles;
	}
	

}
