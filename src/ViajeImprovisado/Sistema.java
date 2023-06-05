package ViajeImprovisado;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;


public class Sistema {
    private ArrayList<Cliente> clientes;
    private ArrayList<Viaje> viajes;


    public Sistema(){
        this.usuarios = new ArrayList<Cliente>();
        this.viajes = new ArrayList<Viaje>();
    }

    public void iniciarSesion(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese su DNI: ");
        String dni = sc.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        Cliente cliente = buscarCliente(dni, contrasena);

        if (cliente != null) {
            System.out.println("¡Bienvenido " + cliente.getNombre() + " " + cliente.getApellido());
        }
        else
            System.out.println("Inicio de sesion fallido, vuelva a ingresarse");
    }

        private Cliente buscarCliente(String dni, String contrasena){
            for (int i=0; i<clientes.size();i++){
                if (clientes.get(i).getDni().equals(dni) && clientes.get(i).getContrasenia().equals(contrasena))
                    return clientes.get(i);
            }
            return null;
        }
        public void registrarse(){
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Ingresar nombre: ");
            String nombre = sc1.nextLine();
            System.out.println("Ingresar apellido: ");
            String apellido = sc1.nextLine();
            System.out.println("Ingresar dni: ");
            String dni = sc1.nextLine();
            System.out.println("Ingresar mail: ");
            String mail = sc1.nextLine();
            System.out.println("Ingresar password: ");
            String password = sc1.nextLine();
            if (validarPassword(password)){
                Cliente cliente = new Cliente(nombre,apellido,dni,mail,password);
                clientes.add(cliente);
            }
            else
                System.out.println("La password no cumple con los requisitos");
        }
        public boolean validarPassword(String pass){
            //logica para validar una password
            return true;
        }
        public void comprarPasaje(Cliente comprador){
            //logica para seleccionar y comprar un pasaje
            //cuando este el viaje comprado
            //dar la opcion de suscribirse con ese viaje
            //...
        }
        public void suscribirseViajesImprovisados(Cliente cliente){
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Ingresar Origen");
            String origen = sc1.nextLine();
            System.out.println("Ingresar Destino");
            String destino = sc1.nextLine();
            cliente.suscribirse(origen,destino);
        }
        public void notificarViaje(Viaje viaje){
            LocalDateTime fechaActual = LocalDateTime.now();
            for (int i=0;i< viajes.size();i++){
                Duration period = Duration.between(viaje.getFecha(),fechaActual);
                if (period.getSeconds()<21600){
                    notificarSuscripto(viaje);
                }
            }
        }
        public void notificarSuscripto(Viaje viaje){
            for (int i=0;i<clientes.size();i++){
                if (clientes.get(i).isSuscripto(viaje))
                    System.out.println("Notificar cliente");
            }
        }
        public void mostrarMenu(){
            //Desarrollar opciones de menu
            //...
        }
    }

