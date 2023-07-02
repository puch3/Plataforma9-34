import java.time.LocalTime;

public class CriterioHoraLlegada implements Criterio{
    private LocalTime horaLlegada1, horaLlegada2;
    public CriterioHoraLlegada(LocalTime horaLlegada1, LocalTime horaLlegada2){
        this.horaLlegada1 = horaLlegada1;
        this.horaLlegada2 = horaLlegada2;
    }
    public boolean seCumple(Viaje v){
        return ((horaLlegada1 == null || horaLlegada2 == null)||
        (horaLlegada1.isBefore(LocalTime.parse(v.getHoraLlegada()))
        && horaLlegada2.isAfter(LocalTime.parse(v.getHoraLlegada()))));
    }
    public LocalTime getHoraLlegada1(){
        return this.horaLlegada1;
    }
    public LocalTime getHoraLlegada2(){
        return this.horaLlegada2;
    }
    public void setHoraLlegada1(LocalTime hs1){
        this.horaLlegada1 = hs1;
    }
    public void setHoraLlegada2(LocalTime hs2){
        this.horaLlegada2 = hs2;
    }
}
