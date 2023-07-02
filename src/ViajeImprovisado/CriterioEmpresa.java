public  class CriterioEmpresa implements Criterio{
    private String empresa;
    public CriterioEmpresa(String empresa){
            this.empresa = empresa;
        }
    public boolean seCumple(Viaje v){
        return ((empresa == null)||(v.getEmpresa().equals(empresa)));
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}