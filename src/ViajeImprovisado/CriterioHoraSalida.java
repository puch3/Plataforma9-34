public class CriterioHoraSalida implements Criterio{
    private LocalDate horaSalida1, horaSalida2;
    public CriterioHoraSalida(LocalDate horaSalida1, LocalDate horaSalida2){
        this.horaSalida1 = horaSalida1;
        this.horaSalida2 = horaSalida2;
    }
    public boolean seCumple(Viaje v){
        return ((horaSalida1 == null || horaSalida2 == null)||
        (horaSalida1.isBefore(LocalTime.parse(v.getHoraSalida())) && 
        horaSalida2.isAfter(LocalTime.parse(v.getHoraSalida()))));
    }
    public LocalDate getHoraSalida1(){
        return this.horaSalida1;
    }
    public LocalDate getHoraSalida2(){
        return this.horaSalida2;
    }
    public void setHoraSalida1(LocalDate hs1){
        this.horaSalida1 = hs1;
    }
    public void setHoraSalida2(LocalDate hs2){
        this.horaSalida2 = hs2;
    }
}