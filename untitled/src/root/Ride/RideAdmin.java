package root.Ride;

import root.Exceptions.AlreadyRated;
import root.User.Person;
import root.Exceptions.SeatsTaken;
import root.Exceptions.NotInRide;
import root.Exceptions.NotRequested;
import root.Exceptions.NoPermission;
import root.Exceptions.AlreadyRequested;
import root.Exceptions.AlreadyInRide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class RideAdmin {
    protected Ride ride;
    protected List<Person> passengers;

    public RideAdmin(Ride ride){
        this.ride = ride;
        passengers = new ArrayList<>();
    }

    public RideAdmin(Ride ride, List<Person> passengers){
        this(ride);
        this.passengers = passengers;
    }

    public Ride getRide() {
        return ride;
    }

    public List<Person> getPassengers(){
        return passengers;
    }

    public String toString(){
        return "Viaje\nRuta"+ride.getRoute()+"\nChofer"+ride.getDriver();
    }

}
