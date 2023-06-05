package ViajeImprovisado;

import java.util.ArrayList;
import java.util.Scanner;


public class Sistema {

    private ArrayList<Cliente> usuarios;

    private ArratList<Viaje> viajes;

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
            System.out.println("¡Bienvenido " + cliente.getNombre());
        }

    }

    private boolean buscarCliente(dni, contrasena){


    }

}
