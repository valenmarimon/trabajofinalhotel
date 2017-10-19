package entidades;

import java.util.Date;

public class TipoHabitacion {
	
	int cod_th;
	String nombre_th;
	private Date fecha;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCod_th() {
		return cod_th;
	}
	public void setCod_th(int cod_th) {
		this.cod_th = cod_th;
	}
	public String getNombre_th() {
		return nombre_th;
	}
	public void setNombre_th(String nombre_th) {
		this.nombre_th = nombre_th;
	}
	public float getPrecio_th() {
		return precio_th;
	}
	public void setPrecio_th(float precio_th) {
		this.precio_th = precio_th;
	}
	float precio_th;

}
