import java.util.ArrayList;
import java.util.Scanner;

public class Pasajero {
	private String apellido;
	private String nombre;
	private String dni;

	public Pasajero(String apellido, String nombre, String dni) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDni() {
		return this.dni;
	}


	public String toString() {
		return "Pasajero [Apellido y Nombre= " + this.apellido + ", " + this.nombre +
				"\nDni= " + dni + "]\n";
	}
	
	
}
