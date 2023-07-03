import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Sistema {
    private static ArrayList<Usuario> pasajeros;
    private static ArrayList<Viaje> viajes;

    private final static int cantHorasViajeImprovisado = 6;
    private final static int asientosVaciosViajeImpro = 20;


    public Sistema(){
        this.pasajeros = new ArrayList<Usuario>();
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
    public void addPasajero(Usuario p){
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
                Usuario pasajero = new Usuario(apellido, nombre, dni, mail, password);
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
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos.get(4)),
                Integer.parseInt(datos.get(3)),
                Integer.parseInt(datos.get(2)));


        Criterio cc = armarFiltro();

        ArrayList<Viaje> coincidentes = mostrarViajesCoincidentes(datos.get(0), datos.get(1), fecha, cc);

        if (coincidentes.size() > 0) {
            Viaje viaje = seleccionarViaje(coincidentes);

            if(viaje != null){
                ArrayList<Asiento> asientos = seleccionarAsientos(viaje);

                asignarAsientos(asientos, comprador);
                //ya se pude pasar a confirmar la compra, marcar los aientos como ocupados
                if (compraConfirmada(viaje, asientos)) {
                    //se hace el pago
                    //se da opcion a suscribir usuario
                } else {
                    //venta cancelada -> desocupar asientos
                    for (int i = 0; i < asientos.size(); i++) {
                        asientos.get(i).desocuparAsiento();
                    }
                }
            }
        }else{System.out.println("No se encontraron viajes coincidentes!");}

    }

    public boolean compraConfirmada(Viaje v, ArrayList<Asiento> aa){
        //Confirma (o no) la compra, se carga el pago en la tarjeta de credito
        Scanner sc = new Scanner(System.in);

        //mostrar resumen venta
        System.out.println(v);
        System.out.println("Asientos: ");
        for(int i = 0; i < aa.size(); i++){
            System.out.println(aa.get(i));
        }

        System.out.println("\nDesea confirmar la compra? (s/n): ");
        return sc.nextLine().equals('s');
    }

    public void asignarAsientos(ArrayList<Asiento> asientos, Pasajero comprador){
        Scanner sc1 =new Scanner(System.in);

        if (!asientos.isEmpty())
            asientos.get(0).setPasajero(comprador); //Se asigna el primer asiento seleccionado al usuario que esta logeado

        if(asientos.size() > 1){
            for(int i=1; i<asientos.size(); i++){
                System.out.println("Ingrese el documento para el asiento nro: " + asientos.get(i).getNumeroDeAsiento());
                String dniIngresado = sc1.nextLine();
                asientos.get(i).setPasajero(verificarDni(dniIngresado));
            }
        }
    }

    public Pasajero verificarDni(String dniIngresado){

        for (int i = 0; i < pasajeros.size(); i++) {
            Usuario u = pasajeros.get(i);
           if( u.getDni().equals(dniIngresado) )
               return u;
        }
        Scanner sc1 = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo Pasajero ");
        String nombre = sc1.nextLine();
        System.out.println("Ingrese el apellido del nuevo Pasajero ");
        String apellido = sc1.nextLine();
        Pasajero pasajeroNuevo = new Pasajero(nombre,apellido, dniIngresado);
        return pasajeroNuevo;
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
    
    public Viaje seleccionarViaje(ArrayList<Viaje> listaParaSeleccion) {
        for (int i = 0; i < listaParaSeleccion.size(); i++) {
            Viaje aux = listaParaSeleccion.get(i);
            System.out.println((i + 1) + ". " + listaParaSeleccion.get(i));
        }
        System.out.println("Seleccione un viaje (ingrese el numero)");
        Scanner sc1 = new Scanner(System.in);


        try {
            int nroViaje = Integer.parseInt(sc1.nextLine());
            if (nroViaje <= listaParaSeleccion.size()) {
                return listaParaSeleccion.get(nroViaje - 1);
            } else {
                System.out.println("El numero de viaje es invalido");
            }
            return null;
        } catch (InputMismatchException ex) {
            System.out.println("Debe ingresar un numero de viaje valido");
            //seleccionarViaje(listaParaSeleccion);
        } catch (NumberFormatException exeption){
            System.out.println("Debe ingresar un numero");
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
                }
            }
            return salida;
        }

        public void suscribirseViajesImprovisados(Usuario pasajero){
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

            Usuario p1 = new Usuario("Perez", "Juan", "100", "", "clave1");
            Usuario p2 = new Usuario("Garcia", "Jose", "101", "", "clave1");
            Usuario p3 = new Usuario("Rodriguez", "Pedro", "102", "", "clave1");
            Usuario p4 = new Usuario("Ramires", "Lucas", "103", "", "clave1");
            Usuario p5 = new Usuario("Zarate", "Monica", "104", "", "clave1");

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

            //plataforma.suscribirseViajesImprovisados(p1);

            //plataforma.notificarViaje(v1);
            plataforma.ComprarPasaje(p3);
            plataforma.ComprarPasaje(p1);
        }
    }

