import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Sistema {
    private ArrayList<Pasajero> pasajeros;
    private ArrayList<Viaje> viajes;

    private final static int cantHorasViajeImprovisado = 6;
    private final static int asientosVaciosViajeImpro = 20;


    public Sistema(){
        this.pasajeros = new ArrayList<Pasajero>();
        this.viajes = new ArrayList<Viaje>();
    }

    public void iniciarSesion(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese su DNI: ");
        String dni = sc.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        Pasajero pasajero = buscarCliente(dni, contrasena);

        if (pasajero != null) {
            System.out.println("¡Bienvenido " + pasajero.getNombre() + " " + pasajero.getApellido());
        }
        else
            System.out.println("Inicio de sesion fallido");
    }

        private Pasajero buscarCliente(String dni, String contrasena){
            for (int i = 0; i< pasajeros.size(); i++){
                if (pasajeros.get(i).getDni().equals(dni) && pasajeros.get(i).getClaveAcceso().equals(contrasena))
                    return pasajeros.get(i);
            }
            return null;
        }


        //Metodo de testing
    public void addPasajero(Pasajero p){
        pasajeros.add(p);
    }

    public void addViaje(Viaje v){
        viajes.add(v);
    }

    public void removeViaje(Viaje v){
    //    viajes.remove(v);   //EQUALS VIAJE
    }


        public void registrarse(){
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Ingresar nombre: ");
            String nombre = sc1.nextLine();
            System.out.println("Ingresar apellido: ");
            String apellido = sc1.nextLine();
            System.out.println("Ingresar dni: ");
            String dni = sc1.nextLine();


            boolean existe = false;
            int i = 0;
            while((i < pasajeros.size()) && (!existe)){

                if(pasajeros.get(i).getDni().equals(dni)){
                    System.out.println("El dni ya se encuentra registrado!");
                    existe = true;
                }
                i++;
            }

            if(!existe) {
                System.out.println("Ingresar mail: ");
                String mail = sc1.nextLine();
                System.out.println("Ingresar password: ");
                String password = sc1.nextLine();


                if (validarPassword(password)) {
                    Pasajero pasajero = new Pasajero(nombre, apellido, dni, mail, password);
                    pasajeros.add(pasajero);
                } else
                    System.out.println("La password no cumple con los requisitos");
            }else{
                //Si el dni existe, lo manda a iniciar sesion.
                iniciarSesion();
            }
        }
        public boolean validarPassword(String pass){
            //logica para validar una password
            return true;
        }
        public void comprarPasaje(Pasajero comprador){
            //logica para seleccionar y comprar un pasaje
            //cuando este el viaje comprado
            //dar la opcion de suscribirse con ese viaje
            //...
        }
        public void suscribirseViajesImprovisados(Pasajero pasajero){
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Ingresar Origen");
            String origen = sc1.nextLine();
            System.out.println("Ingresar Destino");
            String destino = sc1.nextLine();
            pasajero.suscribirse(origen,destino);
        }
        public void notificarViaje(Viaje viaje){

            if(viaje.faltaMenosHoras(this.cantHorasViajeImprovisado) &&
                    viaje.getLugaresDisponibles() > this.asientosVaciosViajeImpro){

                for (int i = 0; i< pasajeros.size(); i++){
                    if (pasajeros.get(i).isSuscripto(viaje))
                        System.out.println("Notificar cliente");
                        pasajeros.get(i).recibirNotificacion(viaje);
                }
            }
        }

        public void mostrarMenu(){
            //Desarrollar opciones de menu
            //...
        }

        public static void main(String[] args){

            Pasajero p1 = new Pasajero("Perez", "Juan", "100", "", "clave1");
            Pasajero p2 = new Pasajero("Garcia", "Jose", "101", "", "clave1");
            Pasajero p3 = new Pasajero("Rodriguez", "Pedro", "100", "", "clave1");
            Pasajero p4 = new Pasajero("Ramires", "Lucas", "100", "", "clave1");
            Pasajero p5 = new Pasajero("Zarate", "Monica", "100", "", "clave1");

            LocalDateTime f1 = LocalDateTime.of(2023,06,7,15,30);
            Omnibus o1 = new Omnibus("C1", 35);


            Viaje v1 = new Viaje(f1, "Tandil", "Azul", 3850,o1);

            Sistema plataforma = new Sistema();

            plataforma.addViaje(v1);

            plataforma.addPasajero(p1);
            plataforma.addPasajero(p2);
            plataforma.addPasajero(p3);
            plataforma.addPasajero(p4);
            plataforma.addPasajero(p5);

            //plataforma.registrarse();
            //plataforma.iniciarSesion();

            plataforma.suscribirseViajesImprovisados(p1);

            plataforma.notificarViaje(v1);



        }
    }

