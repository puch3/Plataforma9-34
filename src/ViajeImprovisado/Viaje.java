
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Viaje {
	private LocalDateTime fecha;
	private String origen, destino;
	private ArrayList<String> paradasIntermedias; //ojo
	private double precio;
	private Omnibus omnibus;
	private ArrayList<Pasajero> pasajeros;
	private LocalDateTime fechaLlegada;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Viaje viaje = (Viaje) o;
		return Double.compare(viaje.precio, precio) == 0 && Objects.equals(fecha, viaje.fecha) && Objects.equals(origen, viaje.origen) && Objects.equals(destino, viaje.destino) && Objects.equals(paradasIntermedias, viaje.paradasIntermedias) && Objects.equals(omnibus, viaje.omnibus) && Objects.equals(pasajeros, viaje.pasajeros);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, origen, destino, paradasIntermedias, precio, omnibus, pasajeros);
	}

	public Viaje(LocalDateTime fecha,LocalDateTime fecha2, String origen, String destino, double precio, Omnibus omnibus) {
		super();
		this.fecha = fecha;
		this.fechaLlegada =fecha2;
		this.origen = origen;
		this.destino = destino;
		this.paradasIntermedias = new ArrayList<String>();
		this.precio = precio;
		this.omnibus = omnibus;
		this.pasajeros = new ArrayList<Pasajero>();
	}
	
	public String toString() {
		return "Empresa: "+ this.getEmpresa()+
				", Hora de salida: "+ this.getHoraSalida()+
				", Hora de llegada: "+ this.getHoraLlegada() + "\n";
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
	public boolean viajeCoincide(String o, String d, LocalDate f){
		return (this.origen.equals(o) && (this.destino.equals(d)||this.paradasIntermedias.contains(d))&& 
		//si se permite el abordaje de pasajeros en las paradas intermedias, ademas de la descarga, se debera tambien incluir los siguiente:
		//this.paradasIntermedias.contains(o) && (this.destino.equals(d))
		(f.getDayOfMonth() == this.fecha.getDayOfMonth() &&
	        f.getMonthValue() == this.fecha.getMonthValue() &&
	        f.getYear() == this.fecha.getYear())) && (this.getLugaresDisponibles()>0);
	}
	public String getEmpresa(){
		return this.omnibus.getEmpresa();
	}
	public String getHoraSalida(){
		return this.fecha.getHour() + ":" + this.fecha.getMinute();
	}
	public String getHoraLlegada(){
		return this.fechaLlegada.getHour() + ":" + this.fechaLlegada.getMinute();
	}

}
