public class Asiento {

    private Pasajero pasajero;

    private int numeroDeAsiento;

    //cargar asiento ocupado directamente
    public Asiento(Pasajero pasajero, int numeroDeAsiento){
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

    public Pasajero getPasajero() {
        return this.pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
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

    @Override
    public String toString() {
        if(this.pasajero != null) {
            return "asiento [" + this.numeroDeAsiento + "]  asignado a "
                    + this.pasajero.getApellido() + ", " + this.pasajero.getApellido() +
                    " con DNI= " + this.pasajero.getDni();
        }else{
            return "asiento[" + this.numeroDeAsiento + "] libre";
        }
    }
}
