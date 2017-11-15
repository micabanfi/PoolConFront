package Views.Table.Main;

import root.Ride.Route;

public class Ruta {
    private Route route;

    public Ruta(Route route){
        this.route = route;
    }

    @Override
    public String toString(){
        return "Desde:"+route.getFrom()+"\nHasta:"+route.getTo()+"\nRuta:"+route.getThrough();
    }
}
