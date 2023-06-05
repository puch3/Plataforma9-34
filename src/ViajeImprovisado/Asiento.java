package ViajeImprovisado;
public class Asiento {

    private Cliente pasajero;

    private int numeroDeAsiento;

    //cargar asiento ocupado directamente
    public Asiento(Cliente pasajero, int numeroDeAsiento){
        this.pasajero = pasajero;

        if (numeroDeAsiento >= 0){
            this.numeroDeAsiento = numeroDeAsiento;
        }
    }

    //crear asiento sin pasajero para luego ser ocupado
    public Asiento(int numeroDeAsiento){
        this.pasajero = null;
        this.numeroDeAsiento = numeroDeAsiento;
    }

    public Cliente getPasajero() {
        return this.pasajero;
    }

    public void setPasajero(Cliente pasajero) {
        if (!estaOcupado()) {
            this.pasajero = pasajero;
        } else {
            //System.out.println("El asiento ya está ocupado por otro pasajero.");
        }
    }
    public int getNumeroDeAsiento() {
        return this.numeroDeAsiento;
    }

    public void setNumeroDeAsiento(int numeroDeAsiento) {
        if (numeroDeAsiento >= 0){
            this.numeroDeAsiento = numeroDeAsiento;
        }
    }
    
    public boolean estaOcupado(){
        return pasajero != null;
    }
    
    public void desocuparAsiento() {
        pasajero = null;
    }

}
