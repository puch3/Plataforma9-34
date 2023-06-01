package ViajeImprovisado;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Viaje {
	private LocalDateTime fecha;
	private String origen, destino;
	private double precio;
	private Omnibus omnibus;
	private ArrayList<Cliente> pasajeros;
	
	public Viaje(LocalDateTime fecha, String origen, String destino, String empresa, double precio, Omnibus omnibus) {
		super();
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.precio = precio;
		this.omnibus = omnibus;
		this.pasajeros = new ArrayList<Cliente>();
	}
	
	public String toString() {
		return "Viaje [fecha=" + fecha + ", origen=" + origen + ", destino=" + destino + ", empresa=" + this.omnibus.getEmpresa()
				+ ", precio=" + precio + ", asientos disponibles=" + (this.omnibus.getAsientos()-this.pasajeros.size()) + "]";
	}
}