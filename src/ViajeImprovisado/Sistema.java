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
        viajes.remove(v);   //PROBAR
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

                    //dar opcion a asociar tarjeta

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
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Ingresar origen: ");
            String origen = sc1.nextLine();
            System.out.println("Ingresar destino: ");
            String destino = sc1.nextLine();
            Scanner scannerFecha = new Scanner(System.in);
            System.out.print("Ingrese el día: ");
            int dia = scannerFecha.nextInt();
            System.out.print("Ingrese el mes (1-12): ");
            int mes = scannerFecha.nextInt();
            int anio = LocalDate.now().getYear();
            LocalDate fecha = LocalDate.of(anio, mes, dia);
            ArrayList<Viaje> coincidentes = mostrarViajesCoincidentes(origen, destino, fecha);
            ArrayList<Viaje> listaParaSeleccion = filtrarViajesCoincidentes(coincidentes);
            Viaje viajeSeleccionado = seleccionarViaje(listaParaSeleccion);
            ArrayList<Asiento> asientosSeleccionados = seleccionarAsientos(viajeSeleccionado);//para ocuparlos si la compra es concretada
            //logica para seleccionar y comprar un pasaje
            //cuando este el viaje comprado
            //dar la opcion de suscribirse con ese viaje
            //...
        }
    public Viaje seleccionarViaje(ArrayList<Viaje> listaParaSeleccion){
        for (int i = 0; i < listaParaSeleccion.size(); i++){
            Viaje aux = listaParaSeleccion.get(i);
            System.out.println((i+1)+". Empresa: "+aux.getEmpresa()+
                    ", Hora de salida: "+aux.getHoraSalida()+
                    ", Hora de llegada:P "+aux.getHoraLlegada());
        }
        System.out.println("Seleccione un viaje (ingrese el numero)");
        Scanner sc1 = new Scanner(System.in);
        int nroViaje = Integer.parseInt(sc1.nextLine());
        return listaParaSeleccion.get(nroViaje-1);
    }
    public ArrayList<Asiento> seleccionarAsientos(Viaje viajeS) {
        Omnibus omnibusAux = viajeS.getOmnibus();
        ArrayList<Asiento> asientosSeleccionados = null;
        if (omnibusAux != null) {
            omnibusAux.mostrarAsientos();
            asientosSeleccionados = omnibusAux.seleccionarAsientos();
        }
        return asientosSeleccionados;
    }
        public ArrayList<Viaje> mostrarViajesCoincidentes(String origen, String destino, LocalDate fecha){
            ArrayList<Viaje> coincidentes = new ArrayList<Viaje>();
            for (int i = 0; i < viajes.size(); i++){
                Viaje aux = viajes.get(i);
                if(aux.viajeCoincide(origen, destino, fecha)){
                    coincidentes.add(aux);
                    System.out.println("Empresa: "+aux.getEmpresa()+
                    ", Hora de salida: "+aux.getHoraSalida()+
                    ", Hora de llegada:P "+aux.getHoraLlegada());
                }
            }
            return coincidentes;
        }
        public ArrayList<Viaje> filtrarViajesCoincidentes(ArrayList<Viaje> coincidantes){
            ArrayList<Viaje> coincidentesFiltrado = new ArrayList<Viaje>();
            Scanner sc1 = new Scanner(System.in);
            String empresa = null;
            double precio = 0.0;
            LocalTime horaSalida1 = null;
            LocalTime horaSalida2 = null;
            LocalTime horaLlegada1 = null;
            LocalTime horaLlegada2 = null;
            System.out.println("Desea ver los viajes disponibles de una empresa especifica?(s/n)");
            String rta = sc1.nextLine();
            if (rta.equals("s")){
                 System.out.println("Ingrese la empresa deseada");
                empresa = sc1.nextLine();
            }
            Criterio c1 = new CriterioEmpresa(empresa);

            System.out.println("Desea ver los viajes disponibles menor a algun costo especifico?(s/n)");
                rta = sc1.nextLine();
            if (rta.equals("s")){
                 System.out.println("Ingrese el monto maximo");
                 precio = sc1.nextDouble();
            }
            Criterio c2 = new CriterioPrecio(precio);

            System.out.println("Desea ver los viajes disponibles en los que su hora de salida este dentro de un margen especifico?(s/n)");
                rta = sc1.nextLine();
            if (rta.equals("s")){
                 System.out.println("Ingrese la hora de salida mas temprana. (HH:mm)");
                 String horaIngresada = sc1.nextLine();
                 horaSalida1 = LocalTime.parse(horaIngresada);
                 System.out.println("Ingrese la hora de salida mas tardia. (HH:mm)");
                 horaIngresada = sc1.nextLine();
                 horaSalida2 = LocalTime.parse(horaIngresada);
            }
            Criterio c3 = new CriterioHoraSalida(horaSalida1,horaSalida2);

            System.out.println("Desea ver los viajes disponibles en los que su hora de llegada este dentro de un margen especifico?(s/n)");
                rta = sc1.nextLine();
            if (rta.equals("s")){
                 System.out.println("Ingrese la hora de salida mas temprana. (HH:mm)");
                 String horaIngresada = sc1.nextLine();
                 horaLlegada1 = LocalTime.parse(horaIngresada);
                 System.out.println("Ingrese la hora de salida mas tardia. (HH:mm)");
                 horaIngresada = sc1.nextLine();
                 horaLlegada2 = LocalTime.parse(horaIngresada);
            }
            Criterio c4 = new CriterioHoraLlegada(horaLlegada1,horaLlegada2);

            Viaje aux = null;
            for(int i = 0; i < coincidantes.size(); i++){
                aux = coincidantes.get(i);
                if (c1.seCumple(aux) && c2.seCumple(aux) && c3.seCumple(aux) && c4.seCumple(aux)){ 
                    coincidentesFiltrado.add(aux);
                }
            }
            return coincidentesFiltrado;
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

            if( (viaje.faltaMenosHoras(this.cantHorasViajeImprovisado) ) &&
                    (viaje.getLugaresDisponibles() > this.asientosVaciosViajeImpro)){

                for (int i = 0; i< pasajeros.size(); i++){
                    if (pasajeros.get(i).isSuscripto(viaje)) {
                        System.out.println("Notificar pasajero " + pasajeros.get(i).getNombre());
                        pasajeros.get(i).recibirNotificacion(viaje);
                    }

                }
                //Opcion fachera  de programacion funcional
                //pasajeros.stream()
                //        .filter(x -> x.isSuscripto(viaje))
                //        .forEach(x ->x.recibirNotificacion(viaje));
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

