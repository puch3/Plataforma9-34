public class CriterioPrecio implements Criterio{
    private double precio;
    public CriterioPrecio(double precio){
        this.precio = precio;
    }
    public boolean seCumple(Viaje v){
        return ((precio == 0.0)||(v.getPrecio() < precio));
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
