package root.Ride;

import root.User.Vehicle;
import root.User.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Ride implements Comparable<Ride>, Serializable{

    private static final long serialVersionUID = 1L;

    private Route route;
    private Vehicle vehicle;
    private User driver;
    private Permissions permissions;
    private LocalDateTime date;
   
    public Ride(Route route, Vehicle vehicle, User driver, Permissions permissions, LocalDateTime date){
        this.route=route;
        this.vehicle=vehicle;
        this.driver=driver;
        this.permissions = permissions;
        this.date=date;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(route);
        out.writeObject(vehicle);
        out.writeObject(driver);
        out.writeObject(permissions);
        out.writeObject(date);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        route = (Route) ois.readObject();
        vehicle = (Vehicle) ois.readObject();
        driver = (User) ois.readObject();
        permissions = (Permissions) ois.readObject();
        date = (LocalDateTime) ois.readObject();
    }


    public Route getRoute(){
        return route;
    }

    public User getDriver(){
        return driver;
    }

    //????????
    @Deprecated
    public Ride getRide(){
        return this;
    }

    public Permissions getPermissions(){
        return permissions;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Ride))
            return false;
        Ride aux = (Ride) obj;

        return aux.date.equals(date) && aux.driver.equals(driver);
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
