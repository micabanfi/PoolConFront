package root.Ride;

import root.User.User;

import java.util.ArrayList;
import java.util.List;


public abstract class RideAdmin {
    protected Ride ride;
    protected List<User> passengers;

    protected RideAdmin(Ride ride){
        this.ride = ride;
        passengers = new ArrayList<>();
    }

    protected RideAdmin(Ride ride, List<User> passengers){
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
        return "Viaje\nRuta"+ride.getRoute()+"\nChofer"+ride.getDriver();
    }

}
