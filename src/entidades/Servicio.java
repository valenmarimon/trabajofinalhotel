package entidades;


import java.util.Date;

public class Servicio {

	private int cod_servicio;
	private String nombre;	
	private String descripcion;	
	private float precio;
	private Date fecha;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCod_servicio() {
		return cod_servicio;
	}
	public void setCod_servicio(int cod_servicio) {
		this.cod_servicio = cod_servicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	} 
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	} 
	
}
