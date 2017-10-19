package entidades;

public class Habitacion {
	
	private int id_habitacion;
	public int getId_habitacion() {
		return id_habitacion;
	}
	public void setId_habitacion(int id_habitacion) {
		this.id_habitacion = id_habitacion;
	}

	private int numero;
	private int piso;
	private int tipo;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getDescripcionTipo(){
		String desc="";
		switch (tipo) {
		case 1:
			desc= "Doble Single";
			break;
		case 2:
			desc= "Deluxe";
			break;
		case 3:
			desc= "Doble Deluxe";
			break;
		default:
			desc= "Error. Tipo de habitación no se encuentra";
			break;
		}
		return desc;
	}
	
}
