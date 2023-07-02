public class CriterioHoraLlegada implements Criterio{
    private LocalDate horaLlegada1, horaLlegada2;
    public CriterioHoraLlegada(LocalDate horaLlegada1, LocalDate horaLlegada2){
        this.horaLlegada1 = horaLlegada1;
        this.horaLlegada2 = horaLlegada2;
    }
    public boolean seCumple(Viaje v){
        return ((horaLlegada1 == null || horaLlegada2 == null)||
        (horaLlegada1.isBefore(LocalTime.parse(aux.getHoraLlegada())) 
        && horaLlegada2.isAfter(LocalTime.parse(aux.getHoraLlegada()))))
    }
    public LocalDate getHoraLlegada1(){
        return this.horaLlegada1;
    }
    public LocalDate getHoraLlegada2(){
        return this.horaLlegada2;
    }
    public void setHoraLlegada1(LocalDate hl1){
        this.horaLlegada1 = hs1;
    }
    public void setHoraLlegada2(LocalDate hl2){
        this.horaLlegada2 = hs2;
    }
}
