import java.util.ArrayList;
import java.util.Scanner;

public class Omnibus {
	private String nombre;
	private String empresa;
	private ArrayList<Asiento> asientos;
	private int numColumnas;
	private int numFilas;
	
	public Omnibus(String nombre, int capacidad) {
		super();
		this.nombre = nombre;

		ArrayList<Asiento> aux = new ArrayList<>();
		for(int i = 0; i < capacidad; i++){
			Asiento a = new Asiento(i+1);
			aux.add(a);
		}

		this.asientos = aux;
		this.numColumnas = 4;//lo usual en omnibus
		this.numFilas =(int)Math.ceil(capacidad / numColumnas);
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
	public void mostrarAsientos() {
		System.out.println("Asientos del omnibus:");
		System.out.println();

		for (int i = 0; i < asientos.size(); i++) {
			if (i % numColumnas == 0 && i != 0) {
				System.out.println();
			}

			if (i % numColumnas == numColumnas / 2) {
				System.out.print("    ");
			}

			if (i + 1 < 10) {
				System.out.print(" ");
			}

			System.out.print((asientos.get(i).getNumeroDeAsiento()) + ": ");
			if ((asientos.get(i).estaOcupado())) {
				System.out.print("X  ");
			} else {
				System.out.print("O  ");
			}
		}
		System.out.println();
	}
	public ArrayList<Asiento> seleccionarAsientos() {
		ArrayList<Asiento> asientosSeleccionados = new ArrayList<Asiento>();
		Scanner scA = new Scanner(System.in);
		System.out.print("Seleccione el/los asiento/s deseado/s (numero-numero)");
		String in = scA.nextLine();
		String[] numerosAsientos = in.split("-");
		for (String numero : numerosAsientos) {
			int numeroAsiento = Integer.parseInt(numero);
			asientosSeleccionados.add(asientos.get(numeroAsiento - 1));
		}
		return asientosSeleccionados;
	}
}
