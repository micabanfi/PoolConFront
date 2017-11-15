package Views.Table.Main;

import root.Ride.*;
import root.User.User;

import java.time.LocalDateTime;


public class MainTable {
    private ActiveRideAdmin activeRideAdmin;
    private Ruta ruta;
    private LocalDateTime fecha;
    private Conductor conductor;
    private Compatibilidad compatibilidad;
    private SePermite sePermite;
    private Asientos asientos;

    public MainTable(ActiveRideAdmin activeRideAdmin, User user){
        Ride aux = activeRideAdmin.getRide();
        this.activeRideAdmin = activeRideAdmin;
        this.ruta = new Ruta(aux.getRoute());
        this.fecha = aux.getDate();
        this.conductor = new Conductor(aux.getDriver());
        this.compatibilidad = new Compatibilidad(activeRideAdmin.compatibility(user.getPreferences()));
        this.sePermite = new SePermite(activeRideAdmin.getRide().getPermissions());
        this.asientos = new Asientos(activeRideAdmin.getPassengers(), activeRideAdmin.getRide().getVehicle());
    }

    public ActiveRideAdmin getActiveRideAdmin(){
        return activeRideAdmin;
    }

    public Ruta getRuta(){
        return ruta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Conductor getConductor(){
        return conductor;
    }

    public SePermite getSePermite() {
        return sePermite;
    }

    public Asientos getAsientos() {
        return asientos;
    }

    public Compatibilidad getCompatibilidad() {
        return compatibilidad;
    }
}
