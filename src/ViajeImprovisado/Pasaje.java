import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pasaje {
    private String nombre;
    private String apellido;
    private int nroAsiento;
    private double precio;
    private String origen;
    private LocalDateTime fecha;
    private String empresaViaje;
    public Pasaje(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public void setNroAsiento(int n){
        this.nroAsiento = n;
    }
    public void setPrecio(double p){
        this.precio = p;
    }
    public void setOrigen(String o){
        this.origen = o;
    }
    public void setFecha(LocalDateTime f){
        this.fecha = f;
    }
    public void setEmpresaViaje(String e){
        this.empresaViaje = e;
    }
    public void completarDatos(int n,double p, String o, LocalDateTime f, String e){
        setEmpresaViaje(e);
        setFecha(f);
        setNroAsiento(n);
        setOrigen(o);
        setPrecio(p);
    }
}
