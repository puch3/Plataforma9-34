import java.util.ArrayList;

public class TarjetaDeCredito {
    private int nro;
    private String marca;
    private String banco;

    public TarjetaDeCredito(int nro, String marca, String banco) {
        this.nro = nro;
        this.marca = marca;
        this.banco = banco;
    }

    public TarjetaDeCredito() {
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public ArrayList<String> getDatos(){
        ArrayList<String> salida = new ArrayList<>();

        salida.add(String.valueOf(this.nro));
        salida.add(this.banco);
        salida.add(this.marca);

        return salida;
    }
}
