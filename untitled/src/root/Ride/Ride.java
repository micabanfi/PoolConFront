package root.Ride;

import root.User.Person;
import root.User.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Ride {
    private Route route;
    private Vehicle vehicle;
    private Person driver;
    private Permissions permissions;
    private Date date;

    public Ride(Route route, Vehicle vehicle, Person driver, Permissions permissions, Date date){
        this.route=route;
        this.vehicle=vehicle;
        this.driver=driver;
        this.permissions = permissions;
        this.date=date;
    }

    public Route getRoute(){
        return route;
    }

    public Person getDriver(){
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


}

