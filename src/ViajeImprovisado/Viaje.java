
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Viaje {
	private LocalDateTime fecha;
	private String origen, destino;
	private ArrayList<String> paradasIntermedias;
	private double precio;
	private Omnibus omnibus;
	private ArrayList<Pasajero> pasajeros;
	
	public Viaje(LocalDateTime fecha, String origen, String destino, double precio, Omnibus omnibus) {
		super();
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.paradasIntermedias = new ArrayList<String>();
		this.precio = precio;
		this.omnibus = omnibus;
		this.pasajeros = new ArrayList<Pasajero>();
	}
	
	public String toString() {
		return "Viaje [fecha=" + fecha + ", origen=" + origen + ", destino=" + destino + ", empresa=" + this.omnibus.getEmpresa()
				+ ", precio=" + precio + ", asientos disponibles=" + (this.omnibus.getCapacidad()-this.pasajeros.size()) + "]";
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public ArrayList<String> getParadasIntermedias() {
		return paradasIntermedias;
	}

	public double getPrecio() {
		return precio;
	}

	public Omnibus getOmnibus() {
		return omnibus;
	}

	public ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public boolean faltaMenosHoras(int horas){
		LocalDateTime ahora = LocalDateTime.now();

		Duration aux = Duration.between(ahora, this.fecha);

		long horasEntremedio = aux.getSeconds();

		return (horasEntremedio/3600) <= horas;
	}

	public int getLugaresDisponibles(){
		return this.omnibus.getCapacidad() - this.pasajeros.size();
	}

}