package entidades;

import java.util.*;

public class Reserva {
	
	private int id_reserva;
	
	public int getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	private Date fecha_desde;
	private Date fecha_hasta;
	private Cliente cliente;
	private EstadoReserva estado; // A PAGAR (reserva registrada, pero aun no abonada. Se paga el dia en que se presente el cliente)
								  // PAGADA (el cliente se presentó y abonó la reserva, por ende ocupó lo que reservó. Habitaciones liberadas después de fecha_hasta)
								  // CANCELADA ( el cliente canceló/no se presentó/no abonó. Las habitaciones quedan liberadas en el instante)
	
	private ArrayList<Servicio> servicios; // coleccion de servicios para esta reserva
	private ArrayList<Habitacion> habitaciones; // habitaciones dentro de esta reserva
	/* public int getNumero_reserva() {
		return numero_reserva;
	}
	public void setNumero_reserva(int numero_reserva) {
		this.numero_reserva = numero_reserva;
	}
	public Date getFecha_reserva() {
		return fecha_reserva;
	}
	public void setFecha_reserva(Date fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	} */
	public Date getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public Date getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(Date fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public EstadoReserva getEstado() {
		return estado;
	}
	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	public ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	

}
