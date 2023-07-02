import java.time.LocalTime;

public class CriterioHoraSalida implements Criterio{
    private LocalTime horaSalida1, horaSalida2;
    public CriterioHoraSalida(LocalTime horaSalida1, LocalTime horaSalida2){
        this.horaSalida1 = horaSalida1;
        this.horaSalida2 = horaSalida2;
    }
    public boolean seCumple(Viaje v){
        return ((horaSalida1 == null || horaSalida2 == null)||
        (horaSalida1.isBefore(LocalTime.parse(v.getHoraSalida())) && 
        horaSalida2.isAfter(LocalTime.parse(v.getHoraSalida()))));
    }
    public LocalTime getHoraSalida1(){
        return this.horaSalida1;
    }
    public LocalTime getHoraSalida2(){
        return this.horaSalida2;
    }
    public void setHoraSalida1(LocalTime hs1){
        this.horaSalida1 = hs1;
    }
    public void setHoraSalida2(LocalTime hs2){
        this.horaSalida2 = hs2;
    }
}
