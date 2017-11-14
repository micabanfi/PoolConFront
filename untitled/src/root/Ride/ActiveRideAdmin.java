package root.Ride;

import root.Exceptions.*;
import root.User.Person;
import root.User.Preferences;
import root.User.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActiveRideAdmin extends RideAdmin implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<User> requests;

    public ActiveRideAdmin(Ride ride){
        super(ride);
        requests = new ArrayList<>();
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(ride);
        out.writeObject(passengers);
        out.writeObject(requests);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        ride = (Ride) ois.readObject();
        passengers = (List<User>) ois.readObject();
        requests = (List<User>) ois.readObject();
    }
    
    public Ride getRide(){
        return ride;
    }

    public List<User> getRequests() {
        return requests;
    }

    public void leaveRide(User passenger){
    	System.out.println("active rides del passenger antes");
        System.out.println(passenger.getActiveRides());
            passengers.remove(passenger);
            passenger.getActiveRides().remove(this);
            System.out.println("active rides del passenger luego");
            System.out.println(passenger.getActiveRides());
    }

    public void addRequest(User person) throws AlreadyRequested, AlreadyInRide, SeatsTaken {
    	if(requests.contains(person)){
            throw new AlreadyRequested();
        }
        if(passengers.contains(person) || person.equals(ride.getDriver())){
            throw new AlreadyInRide();
        }
        if(freeSeats()==0){
            throw new SeatsTaken();
        }
        requests.add(person);
    }

    private void validateRequest(User driver, Person request) throws NoPermission, NotRequested{
        if(!requests.contains(request)) {
            throw new NotRequested();
        }
        if (!driver.equals(ride.getDriver())) {
            throw new NoPermission();
        }
    }

    public void acceptRequest(User driver, User request) throws NoPermission, NotRequested, SeatsTaken{
        validateRequest(driver, request);
        if(freeSeats() == 0) {
            throw new SeatsTaken();
        }
        passengers.add(request);
        requests.remove(request);
        request.addRide(this);
        /* Por lo explicado en la clase vehicle, la siguiente línea fue agregada porque de lo contrario,
        * no se cargan ni actualizan los asientos disponibles de cada viaje por un problema con
        * javafx que no sabemos resolver.
       */
        this.getRide().getVehicle().updateFreeSeats(freeSeats());
    }
    
    private int freeSeats(){
        return getRide().getVehicle().getSeats() - passengers.size();
    }

    public void declineRequest(User driver, User request)throws NoPermission, NotRequested{
    	validateRequest(driver, request);
        requests.remove(request);
    }

    public double compatibility(Preferences preferences){
        double passengerRating=0;
        for(User user: passengers){
            passengerRating += user.getRating().calculatePercentage();
        }
        passengerRating = passengerRating/passengers.size();
        double driverRating = ride.getDriver().getRating().calculatePercentage();
        double preferencesRating= ride.getPermissions().compatibility(preferences);

        return (passengerRating*0.3 + driverRating*0.3 + preferencesRating*0.5);
    }

}
