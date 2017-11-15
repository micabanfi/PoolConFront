package root.Ride;

import root.User.User;
import root.User.Vehicle;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public abstract class RideAdmin implements Serializable {
    protected Ride ride;
    protected List<User> passengers;

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(ride);
        out.writeObject(passengers);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        ride = (Ride) ois.readObject();
        passengers = (List<User>) ois.readObject();
    }

    public RideAdmin(Ride ride){
        this.ride = ride;
        passengers = new ArrayList<>();
    }

    public RideAdmin(Ride ride, List<User> passengers){
        this(ride);
        this.passengers = passengers;
    }

    public Ride getRide() {
        return ride;
    }

    public List<User> getPassengers(){
        return passengers;
    }

    public String toString(){
        return "Viaje\nRuta"+ride.getRoute()+"\nChofer"+ride.getDriver()+"seats:"+ride.getVehicle().getSeats();

    }
}
