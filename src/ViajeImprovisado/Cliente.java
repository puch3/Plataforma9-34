package ViajeImprovisado;

public class Cliente {
	private String apellido;
	private String nombre;
	private String dni;
	private String email;
	private String claveAcceso;
	private boolean suscripto;


	public Cliente(String apellido, String nombre, String dni, String email, String calveAcceso) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
		this.claveAcceso = claveAcceso;
		this.suscripto = false;
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

	public String getClaveAcceso() {
		return this.claveAcceso;
	}

	public boolean isSuscripto(){return this.suscripto;}

	public void suscribirse(){this.suscripto = true;}

	public void recibirNotificacion(Viaje viaje){
		String mensaje = "Hola" + this.getNombre() + ", en 6 horas sale el colectivo " +
						viaje.getOmnibus() + " de la terminal de " + viaje.getOrigen() + " con destino a "
						+ viaje.getDestino() ".\n" + "No te pierdas la oportunidad de acceder a este viaje" +
						" con un 50% de descuento!";

		//Codigo para enviar el email
	}

	public String toString() {
		return "Cliente [Apellido y Nombre= " + this.apellido + ", " + this.nombre +
				"\nDni= " + dni +
				"\nContrasenia= " + contrasenia	+ "]\n";
	}
	
	
}
