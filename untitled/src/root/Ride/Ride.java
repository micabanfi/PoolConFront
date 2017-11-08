package root.Ride;

import root.User.Person;
import root.User.Vehicle;
import root.User.User;

import java.util.Date;


public class Ride implements Comparable<Ride>{
    private Route route;
    private Vehicle vehicle;
    private User driver;
    private Permissions permissions;
    private Date date;

    public Ride(Route route, Vehicle vehicle, User driver, Permissions permissions, Date date){
        this.route=route;
        this.vehicle=vehicle;
        this.driver=driver;
        this.permissions = permissions;
        this.date=date;
    }

    public Route getRoute(){
        return route;
    }

    public User getDriver(){
        return driver;
    }

    public Permissions getPermissions(){
        return permissions;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Ride))
            return false;
        Ride aux = (Ride) obj;

        return aux.date.equals(date) && aux.driver.equals(driver); //DEBERIAMOS DE PROHIBIR Q UN USUARIO CREE
        //VIAJES DISTINTOS A LA MISMA HORA pq sino este equals no sirve.
        //O cambiar el equals y que el usuario se joda. Porque tambien podria crearlo 1 min dps y eso no lo podemos validar
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + driver.hashCode();
        result = 31*result + date.hashCode();
        return result;
    }

    @Override
    public int compareTo(Ride ride){
        if(ride==null)
        return 0;
        return ride.getDate().compareTo(date);

    }

    @Override
    public String toString() {
        return "Ride{" +
                "route=" + route.toString() +
                ", vehicle=" + vehicle.toString() +
                ", driver=" + driver.toString()  +
                ", date=" + date.toString() +
                '}';
    }


}

