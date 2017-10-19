package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import appExceptions.ApplicationException;
import entidades.TipoHabitacion;

public class DataTipoHabitacion {

	public ArrayList<TipoHabitacion> listarTiposHabitacion() throws ApplicationException {
		
		ResultSet rs=null;
		
		Statement stmt=null;
		
		
		ArrayList<TipoHabitacion> TiposHabitacion = new ArrayList<TipoHabitacion>();
		
		/*select th.cod_th, pth.precio
		 from `TIPOS_HABITACION` th
		inner join `PRECIOS_TH` pth
		      on th.`COD_TH`=`pth`.`COD_TH`
		inner join (
		          select cod_th, max(fecha_desde)ult_fec
		          from `PRECIOS_TH`
		          where fecha_desde <=current_date
		          group by cod_th
		          ) val_act
		          on pth.`COD_TH`=val_act.cod_th
		          and pth.`FECHA_DESDE`=val_act.ult_fec; */
		
  try {
	     stmt = FactoryConexion.getInstancia().getConn().createStatement();
	   
	     rs = stmt.executeQuery("select tipos_habitacion.cod_th, tipos_habitacion.nombre_th, precios_th.precio"
	     		+ " from tipos_habitacion"
	     		+ " inner join precios_th"
	     		+ " on tipos_habitacion.cod_th=precios_th.cod_th"
	     		+ " inner join (select precios_th.cod_th, max(precios_th.fecha_desde) 'ult_fec'"
	     		+ " from precios_th"
	     		+ " where precios_th.fecha_desde <= current_date()"
	     		+ " group by precios_th.cod_th)val_act"
	     		+ " on precios_th.cod_th=val_act.cod_th and precios_th.fecha_desde=val_act.ult_fec ");
	        
	        while (rs.next())
	         {
	           
	         
	            TipoHabitacion th = new TipoHabitacion();
	            th.setCod_th(rs.getInt("cod_th"));
	            th.setNombre_th(rs.getString("nombre_th"));
	            th.setPrecio_th(rs.getFloat("precio"));
	            
	            TiposHabitacion.add(th);
	        	
	            
	         }
	      
	    }catch (SQLException e) {
	    	throw new ApplicationException("Error escritura en la base de datos", e);
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
		
		return TiposHabitacion;
	}

	public void modificarPrecioTipoHabitacion(TipoHabitacion th)throws ApplicationException {
		
		ResultSet rs=null;
		PreparedStatement stmt=null;	
		
		try {			
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Insert into precios_th (cod_th, fecha_desde, precio) values (?,?,?)"
					);
			stmt.setInt(1, th.getCod_th());
			stmt.setDate(2, new java.sql.Date(th.getFecha().getTime()));
			stmt.setFloat(3, th.getPrecio_th());
			
			
		
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

}
