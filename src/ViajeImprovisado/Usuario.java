import java.util.ArrayList;
import java.util.Scanner;

public class Usuario extends Pasajero{
        private String email;
        private String claveAcceso;
        private SuscripcionViajeImprovisado suscripcion;
        private TarjetaDeCredito tarjetaAsociada;


        public Usuario(String apellido, String nombre, String dni, String email, String claveAcceso) {
            super(apellido, nombre, dni);
            this.email = email;
            this.claveAcceso = claveAcceso;
            this.suscripcion = null;
            this.tarjetaAsociada = null;
        }

        public String getClaveAcceso() {
            return this.claveAcceso;
        }

        public void asociarTarjeta(TarjetaDeCredito t){
            this.tarjetaAsociada = t;

            System.out.println("Datos de tarjeta actualizados");
        }

        public void asociarTarjeta(){
            Scanner sc =new Scanner(System.in);
            TarjetaDeCredito t = new TarjetaDeCredito();

            System.out.print("Ingrese nro de tarjeta: ");
            t.setNro(Integer.parseInt(sc.nextLine()));
            System.out.println("\nIngrese banco de la tarjeta: ");
            t.setBanco(sc.nextLine());
            System.out.println("\nIngrese marca de su tarjeta: ");
            t.setMarca(sc.nextLine());

            //validar tarjeta...
        }

        public ArrayList<String> getDatosTarjeta(){
            if( this.tarjetaAsociada == null){

                this.asociarTarjeta();
            }

            return this.tarjetaAsociada.getDatos();
        }

        public boolean isSuscripto(Viaje viaje){
            if(this.suscripcion != null) {
                if (this.suscripcion.getOrigen().equals(viaje.getOrigen()) &&
                        this.suscripcion.getDestino().equals(viaje.getDestino()))
                    return true;
                else
                    return false;
            } else{
                return false;
            }
        }


        //Suscribir pasajero a  Viajes Improvisados
        //Suscripcion desde la compra de un pasaje
        public void suscribirse(Viaje v){

            String o = v.getOrigen();
            String d = v.getDestino();

            suscribirse(o, d);
        }
        //Suscripcion cargando origen y destino
        public void suscribirse(String origen, String destino){
            SuscripcionViajeImprovisado s = new SuscripcionViajeImprovisado(origen, destino);
            this.suscripcion = s;

        }

        public void recibirNotificacion(Viaje viaje){
            String mensaje = ("Hola" + this.getNombre() + ", en 6 horas sale el colectivo " +
                    viaje.getOmnibus() + " de la terminal de " + viaje.getOrigen() + " con destino a "
                    + viaje.getDestino() + ".\n" + "No te pierdas la oportunidad de acceder a este viaje" +
                    " con un 50% de descuento!");
            //Codigo para enviar el email
        }

        public String toString() {
            return "Cliente [Apellido y Nombre= " + this.getApellido() + ", " + this.getNombre() +
                    "\nDni= " + getDni() +
                    "\nContrasenia= " + claveAcceso	+ "]\n";
        }




}
