package entidades;

public class EstadoReserva {

	int id;
	String estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcionEstado(){
		String desc="";
		switch (id) {
		case 1:
			desc= "A Pagar";
			break;
		case 2:
			desc= "Pagado";
			break;
		case 3:
			desc= "Cancelado";
			break;
		default:
			desc= "Error. Descripción estado no encontrado";
			break;
		}
		return desc;
	}
}
