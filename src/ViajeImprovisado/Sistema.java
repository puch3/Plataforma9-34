import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Sistema {
    private static ArrayList<Pasajero> pasajeros;
    private static ArrayList<Viaje> viajes;

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

    public ArrayList<String> cargarDatosCompra(){
    //El usuario ingresa Origen, destino y fecha de salida
    // La funcion retorna una lista de string con los datos
        ArrayList<String> salida = new ArrayList<>();
        Scanner sc1 = new Scanner(System.in);

        System.out.println("Ingresar origen: ");
        salida.add(sc1.nextLine());

        System.out.println("Ingresar destino: ");
        salida.add(sc1.nextLine());

        System.out.print("Ingrese el día: ");
        salida.add(sc1.nextLine());

        System.out.print("Ingrese el mes (1-12): ");
        salida.add(sc1.nextLine());

        System.out.println("Ingrese el anio: ");
        salida.add(sc1.nextLine());

        return salida;
    }


    public void ComprarPasaje(Pasajero comprador){
    //El usuario ingresa los datos, se muestran los pasajes disponibles y se confirma o no la compra
        ArrayList<String> datos  = this.cargarDatosCompra();
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos.get(2)),
                Integer.parseInt(datos.get(3)),
                Integer.parseInt(datos.get(4)));

        Criterio cc = armarFiltro();

        ArrayList<Viaje> coincidentes = mostrarViajesCoincidentes(datos.get(0), datos.get(1), fecha, cc);

        if (coincidentes.size() > 0) {
            System.out.println(coincidentes);
            Viaje viaje = seleccionarViaje(coincidentes);
            ArrayList<Asiento> asientos = seleccionarAsientos(viaje);
        }else{System.out.println("No se encontraron viajes coincidentes");}

        //logica para seleccionar y comprar un pasaje
        //cuando este el viaje comprado
        //dar la opcion de suscribirse con ese viaje
        //...
    }
    public ArrayList<Pasaje> armarPasajes(ArrayList<Asiento> asientos, Viaje viaje,Pasajero comprador){
        Scanner sc1 = new Scanner(System.in);
        String dniIngresado = null;
        ArrayList<Pasaje> pasajes = new ArrayList<>();
        if (asientos.size() > 1){
            for (int i = 0; i < asientos.size();i++) {
                System.out.println("Ingrese el documento para el asiento nro: " + asientos.get(i).getNumeroDeAsiento());
                dniIngresado = sc1.nextLine();
                Pasaje nuevoPasaje = this.VerificarDni(dniIngresado);
                nuevoPasaje.completarDatos(asientos.get(i).getNumeroDeAsiento(),viaje.getPrecio(), viaje.getOrigen(),viaje.getFecha(),viaje.getEmpresa());
                pasajes.add(nuevoPasaje);
            }
        }else{
            if(asientos.size() == 1) {
                Pasaje nuevoPasaje = this.VerificarDni(comprador.getDni());
                nuevoPasaje.completarDatos(asientos.get(0).getNumeroDeAsiento(),viaje.getPrecio(), viaje.getOrigen(),viaje.getFecha(),viaje.getEmpresa());
                pasajes.add(nuevoPasaje);
            }
        }
        return pasajes;
    }
    public static Pasaje VerificarDni(String dniIngresado){
        for (int i = 0; i < pasajeros.size(); i++) {
            Pasajero pasajeroAux = pasajeros.get(i);
            if (pasajeroAux.getDni().equals(dniIngresado)) {
                Pasaje pasajeNuevo = new Pasaje(pasajeroAux.getNombre(), pasajeroAux.getApellido());
                //notificar pasajero.
                return pasajeNuevo;
            }
        }
        Scanner sc1 = new Scanner(System.in);
        String nombre = null;
        String apellido = null;
        System.out.println("Ingrese el nombre del nuevo Pasajero ");
        nombre = sc1.nextLine();
        System.out.println("Ingrese el apellido del nuevo Pasajero ");
        apellido = sc1.nextLine();
        Pasaje pasajeNuevo = new Pasaje(nombre,apellido);
        return pasajeNuevo;
    }
    public Criterio armarFiltro(){
        Criterio salida = new CriterioTrue();

        Scanner sc1 = new Scanner(System.in);

        System.out.println("Desea ver los viajes disponibles de una empresa especifica?(s/n)");
        if (sc1.nextLine().equals("s")){
            System.out.println("Ingrese la empresa deseada: ");
            salida = new CriterioAnd(salida, new CriterioEmpresa(sc1.nextLine()));
        }

        System.out.println("Desea ver los viajes disponibles menor a algun costo especifico?(s/n)");
        if (sc1.nextLine().equals("s")){
            System.out.println("Ingrese el monto maximo");
            salida = new CriterioAnd(salida, new CriterioPrecio(sc1.nextDouble()));
        }

        System.out.println("Desea ver los viajes disponibles en los que su hora de salida este dentro de un margen especifico?(s/n)");
        if (sc1.nextLine().equals("s")){
            System.out.println("Ingrese la hora de salida mas temprana. (HH:mm)");
            String horaIngresada = sc1.nextLine();
            LocalTime horaSalida1 = LocalTime.parse(horaIngresada);
            System.out.println("Ingrese la hora de salida mas tardia. (HH:mm)");
            horaIngresada = sc1.nextLine();
            LocalTime horaSalida2= LocalTime.parse(horaIngresada);

            salida = new CriterioAnd(salida, new CriterioHoraSalida(horaSalida1, horaSalida2));
        }


        System.out.println("Desea ver los viajes disponibles en los que su hora de llegada este dentro de un margen especifico?(s/n)");
        if (sc1.nextLine().equals("s")){
            System.out.println("Ingrese la hora de llegada mas temprana. (HH:mm)");
            String horaIngresada = sc1.nextLine();
            LocalTime horaLlegada1 = LocalTime.parse(horaIngresada);
            System.out.println("Ingrese la hora de llegada mas tardia. (HH:mm)");
            horaIngresada = sc1.nextLine();
            LocalTime horaLlegada2 = LocalTime.parse(horaIngresada);
            salida = new CriterioAnd(salida, new CriterioHoraLlegada(horaLlegada1, horaLlegada2));
        }

        return salida;
    }
    
    public Viaje seleccionarViaje(ArrayList<Viaje> listaParaSeleccion){
        for (int i = 0; i < listaParaSeleccion.size(); i++){
            Viaje aux = listaParaSeleccion.get(i);
            System.out.println((i+1)+". Empresa: "+aux.getEmpresa()+
                    ", Hora de salida: "+aux.getHoraSalida()+
                    ", Hora de llegada: "+aux.getHoraLlegada());
        }
        System.out.println("Seleccione un viaje (ingrese el numero)");
        Scanner sc1 = new Scanner(System.in);
        int nroViaje = Integer.parseInt(sc1.nextLine());
        if (nroViaje <= listaParaSeleccion.size()) {
            return listaParaSeleccion.get(nroViaje - 1);
        }else {
            System.out.println("El numero de viaje es invalido");
        }
        return null;
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
        public ArrayList<Viaje> mostrarViajesCoincidentes(String origen, String destino, LocalDate fecha, Criterio cc){
        //Dado un origen, un destino, una fecha y un criterio se devuelve una lista de los viajes que coincidan con los datos
            ArrayList<Viaje> salida = new ArrayList<Viaje>();

            for (int i = 0; i < viajes.size(); i++){
                Viaje aux = viajes.get(i);
                if(aux.viajeCoincide(origen, destino, fecha) && cc.seCumple(aux)){
                    salida.add(aux);

                    //Muestra resultado **TEMPORAL**
                    System.out.println("Empresa: "+aux.getEmpresa()+
                                    ", Hora de salida: "+aux.getHoraSalida()+
                                    ", Hora de llegada: "+aux.getHoraLlegada() + "\n");

                }
            }
            return salida;
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

        public static void main(String[] args) {

            Pasajero p1 = new Pasajero("Perez", "Juan", "100", "", "clave1");
            Pasajero p2 = new Pasajero("Garcia", "Jose", "101", "", "clave1");
            Pasajero p3 = new Pasajero("Rodriguez", "Pedro", "100", "", "clave1");
            Pasajero p4 = new Pasajero("Ramires", "Lucas", "100", "", "clave1");
            Pasajero p5 = new Pasajero("Zarate", "Monica", "100", "", "clave1");

            LocalDateTime f1 = LocalDateTime.of(2023, 06, 7, 15, 30);
            LocalDateTime f2 = LocalDateTime.of(2023, 06, 7, 19, 10);
            Omnibus o1 = new Omnibus("C1", 35);
            o1.setEmpresa("EmpresaX");

            Viaje v1 = new Viaje(f1, f2, "Tandil", "Azul", 3850, o1);

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
            plataforma.ComprarPasaje(p3);
        }
    }

