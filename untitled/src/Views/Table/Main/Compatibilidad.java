package Views.Table.Main;

public class Compatibilidad {
    private double compatibilidad;

    public Compatibilidad(double compatibilidad){
        this.compatibilidad = compatibilidad;
    }

    @Override
    public String toString(){
        return compatibilidad + "%";
    }
}
