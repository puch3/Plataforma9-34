package ViajeImprovisado;

public class Cliente {
	private String apellido, nombre, dni, contrasenia;

	public Cliente(String apellido, String nombre, String dni, String contrasenia) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.contrasenia = contrasenia;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public String toString() {
		return "Cliente [Apellido y Nombre= " + this.apellido + ", " + this.nombre +
				"\nDni= " + dni + "\nContrasenia= " + contrasenia
				+ "]";
	}
	
	
}
