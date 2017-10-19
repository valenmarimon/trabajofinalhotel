package data;

import entidades.Cliente;
import entidades.EstadoReserva;
import entidades.Habitacion;
import entidades.Reserva;
import entidades.Servicio;

import java.sql.*;
import java.util.ArrayList;

import appExceptions.ApplicationException;


public class DataReserva {

	public void crearReserva(Reserva r) throws ApplicationException {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into reservas(fecha_desde, fecha_hasta, dni) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS
				   );
			stmt.setDate(1, new java.sql.Date(r.getFecha_desde().getTime()));		
			stmt.setDate(2, new java.sql.Date(r.getFecha_hasta().getTime()));	
			stmt.setInt(3, r.getCliente().getDni());
			stmt.execute();

			rs=stmt.getGeneratedKeys();
			
			if(rs!=null && rs.next()){
				r.setId_reserva(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			throw new ApplicationException("Error escritura en la base de datos", e);
		}
		
		for (int indice = 0; indice < r.getHabitaciones().size(); indice++){		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into reservas_habitaciones(id_reserva, id_habitacion) values (?,?)"
				   );
			stmt.setInt(1, r.getId_reserva());
			stmt.setInt(2, r.getHabitaciones().get(indice).getId_habitacion());		
			stmt.execute();

		} catch (SQLException e) {
			throw new ApplicationException("Error al cerrar conexion en la base de datos", e);	
		}
		
		// falta agregar servicios. O en otro metodo?
		
		finally{
			
			try {
				if(rs!=null ) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}		
	}
		
  }

	public ArrayList<Reserva> listarReservas() throws ApplicationException { // NECESITO AGRUPAR POR CLIENTE Y DENTRO DE CLIENTE AGRUPAR POR FECHA
		ResultSet rsR=null; // rs de la reserva
		ResultSet rsH=null; // rs de las habitaciones
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		
		try {
			 stmt = FactoryConexion.getInstancia().getConn().createStatement();
			   
		        rsR = stmt.executeQuery(
		        		  "select reservas.id_reserva, reservas.fecha_desde, reservas.fecha_hasta, reservas.dni, reservas.id_estado, "
		        		+ "personas.nombre, personas.apellido,personas.email "
		        		+ "from reservas "
		        		+ "inner join personas "
		        		+ "on personas.dni=reservas.dni"		        		
					);
			
			while(rsR.next()){
				Reserva r = null;				
				r = new Reserva();
				r.setId_reserva(rsR.getInt("id_reserva"));
				r.setFecha_desde(rsR.getDate("fecha_desde"));
				r.setFecha_hasta(rsR.getDate("fecha_hasta"));
				Cliente cliente = new Cliente();
				cliente.setDni(rsR.getInt("dni"));
				cliente.setNombre(rsR.getString("nombre"));
				cliente.setApellido(rsR.getString("apellido"));
				cliente.setEmail(rsR.getString("email"));
				r.setCliente(cliente);
				EstadoReserva ER = null;
				ER = new EstadoReserva();
				ER.setId(rsR.getInt("id_estado")); 				
				r.setEstado(ER);
				//a esta reserva que obtuve del rs, le añado sus correspondientes habitaciones
				ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
				try {
					pstmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
							  "select habitaciones.id_habitacion, habitaciones.numero, habitaciones.piso, habitaciones.cod_th "
							+ "from habitaciones "
							+ "inner join reservas_habitaciones "
							+ "on habitaciones.id_habitacion=reservas_habitaciones.id_habitacion "
							+ "where reservas_habitaciones.id_reserva = ?"
							);
					pstmt.setInt(1, r.getId_reserva());
					rsH = pstmt.executeQuery();
					 while (rsH.next()){
						Habitacion h = new Habitacion();
						h.setId_habitacion(rsH.getInt("id_habitacion"));
						h.setNumero(rsH.getInt("numero"));
						h.setPiso(rsH.getInt("piso"));
						h.setTipo(rsH.getInt("cod_th"));
						habitaciones.add(h);						
					}					
					r.setHabitaciones(habitaciones);
					
				} catch (SQLException e) {
					throw new ApplicationException("Error escritura en la base de datos", e);
				}				
				
				reservas.add(r);				
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
		}		
		finally
		{
			try {
				if(rsR!=null)rsR.close();
				if(rsH!=null)rsH.close();
				if(stmt!=null) stmt.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error cerrar conexion en la base de datos", e);
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		return reservas;
	}

	public void cancelarReserva(int idreserva) throws ApplicationException{
		
			PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					 "update reservas set id_estado=? where id_reserva=?"
					);
			stmt.setInt(1, 3);
			stmt.setInt(2, idreserva);
			stmt.execute();
			
		} catch (SQLException e) {
			throw new ApplicationException("Error al actualizar datos reserva", e);
		} finally{
			
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error cerrar conexion en la base de datos", e);
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
		
	}

	public void confirmarPago(int idreserva)throws ApplicationException {
	PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					 "update reservas set id_estado=? where id_reserva=?"
					);
			stmt.setInt(1, 2);
			stmt.setInt(2, idreserva);
			stmt.execute();
			
		} catch (SQLException e) {
			throw new ApplicationException("Error al actualizar datos reserva", e);
		} finally{
			
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error cerrar conexion en la base de datos", e);
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
		
	}

	public void AgregarServicios(Reserva reservaActual) throws SQLException, ApplicationException {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try{
			
		
	
			for(Servicio sr: reservaActual.getServicios()){
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into servicios_solicitados(id_reserva, cod_servicio) values (?,?)");
			stmt.setInt(1, reservaActual.getId_reserva());
			stmt.setInt(2, sr.getCod_servicio());
			
			stmt.execute();
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al actualizar datos reserva", e);
		} finally{
			
			try {
				if(rs!=null ) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	}	
}



