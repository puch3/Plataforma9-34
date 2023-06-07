import java.util.ArrayList;

public class Omnibus {
	private String nombre;
	private String empresa;
	private ArrayList<Asiento> asientos;
	
	public Omnibus(String nombre, int capacidad) {
		super();
		this.nombre = nombre;

		ArrayList<Asiento> aux = new ArrayList<>();
		for(int i = 0; i < capacidad; i++){
			Asiento a = new Asiento(i+1);
			aux.add(a);
		}

		this.asientos = aux;
	}

	public void addAsiento(Asiento a){
		asientos.add(a);
	}

	public void removeAsiento(Asiento a){
		asientos.remove(a);
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return asientos.size();
	}

	public String toString() {
		return "Omnibus [Nombre= " + nombre +
									"\nAsientos= " + asientos + "]\n";
	}
}
