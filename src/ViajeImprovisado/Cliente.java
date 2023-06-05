package ViajeImprovisado;

public class Cliente {
	private String apellido;
	private String nombre;
	private String dni;
	private String email;
	private String contrasenia;
	private SuscripcionViajeImprovisado suscripcion;


	public Cliente(String apellido, String nombre, String dni, String email, String contrasenia) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
		this.contrasenia = contrasenia;
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

	public String getContrasenia() {
		return this.contrasenia;
	}

	public boolean isSuscripto(){return this.suscripcion != null;}


	//Suscribir pasajero a  Viajes Improvisados
	//Suscripcion desde la compra de un pasaje
	public void suscribirse(Viaje v){

		String o = v.getOrigen();
		String d = v.getDestino();

		suscribirse(o, d);
	}
	//Suscripcion cargando origen y destino
	public void suscribirse(String origen, String destino){
		SuscripcionViajeImprovisado s = new SuscripcionViajeImprovisado(String origen, String destino)
		this.suscripcion = s;

	}

	public void recibirNotificacion(Viaje viaje){
		String mensaje = "Hola" + this.getNombre() + ", en 6 horas sale el colectivo " +
						viaje.getOmnibus() + " de la terminal de " + viaje.getOrigen() + " con destino a "
						+ viaje.getDestino() ".\n" + "No te pierdas la oportunidad de acceder a este viaje" +
						" con un 50% de descuento!";

		//Codigo para enviar el email
	}

	public void comprarPasaje(Viaje viaje){
		//Codigo para comprar pasaje
	}

	public String toString() {
		return "Cliente [Apellido y Nombre= " + this.apellido + ", " + this.nombre +
				"\nDni= " + dni +
				"\nContrasenia= " + contrasenia	+ "]\n";
	}
	
	
}
