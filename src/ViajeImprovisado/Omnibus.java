package ViajeImprovisado;

import java.util.ArrayList;

public class Omnibus {
	private String nombre, empresa;
	private int asientos;
	private Asiento[] arrayAsientos;
	
	public Omnibus(String nombre, int asientos) {
		super();
		this.nombre = nombre;
		this.asientos = asientos;
		this.arrayAsientos = new Asiento[asientos];
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
		return "Omnibus [Nombre= " + nombre +
									"\nAsientos= " + asientos + "]\n";
	}

	public void ocuparAsiento(int numeroDeAsiento, Cliente nuevoPasajero){
		if(arrayAsientos[numeroDeAsiento]==null){
			arrayAsientos[numeroDeAsiento]= new Asiento(nuevoPasajero, numeroDeAsiento);
		}
		else{
			if (!(arrayAsientos[numeroDeAsiento].estaOcupado())){
				arrayAsientos[numeroDeAsiento].setPasajero(nuevoPasajero);
			}
		}
	}
	public void desocuparAsiento(int numeroDeAsiento){
		if(arrayAsientos[numeroDeAsiento]==null){
			//no habia asiento ocupado
		}else{
			if (!(arrayAsientos[numeroDeAsiento].estaOcupado())){
				arrayAsientos[numeroDeAsiento].desocuparAsiento();
			}
		}
	}

}
