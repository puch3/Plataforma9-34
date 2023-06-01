package ViajeImprovisado;

public class Omnibus {
	private String nombre, empresa;
	private int asientos;
	
	public Omnibus(String nombre, int asientos) {
		super();
		this.nombre = nombre;
		this.asientos = asientos;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public int getAsientos() {
		return asientos;
	}

	public String toString() {
		return "Omnibus [nombre=" + nombre + ", asientos=" + asientos + "]";
	}
}
