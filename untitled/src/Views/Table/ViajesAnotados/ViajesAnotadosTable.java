package Views.Table.ViajesAnotados;

import Views.Table.Main.Ruta;
import root.Ride.ActiveRideAdmin;

import java.time.LocalDateTime;

public class ViajesAnotadosTable {
    private ActiveRideAdmin activeRideAdmin;
    private Ruta ruta;
    private LocalDateTime fecha;

    public ViajesAnotadosTable(ActiveRideAdmin activeRideAdmin){
        this.activeRideAdmin = activeRideAdmin;
        ruta = new Ruta(activeRideAdmin.getRide().getRoute());
        fecha = activeRideAdmin.getRide().getDate();
    }

    public ActiveRideAdmin getActiveRideAdmin() {
        return activeRideAdmin;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
