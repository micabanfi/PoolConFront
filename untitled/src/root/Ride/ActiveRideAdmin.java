package root.Ride;

import root.Exceptions.*;
import root.User.Person;
import root.User.User;

import java.util.ArrayList;

public class ActiveRideAdmin extends RideAdmin {

    private ArrayList<User> requests;

    public ActiveRideAdmin(Ride ride){
        super(ride);
        requests = new ArrayList<>();
    }

    private int freeSeats(){
        return getRide().getVehicle().getSeats() - passengers.size();
    }

    public Ride getRide(){
        return ride;
    }

    public ArrayList<User> getRequests() {
        return requests;
    }

    public void leaveRide(User passenger) throws NotInRide {
        if(passengers.contains(passenger)){
            passengers.remove(passenger);
        }else{
            throw new NotInRide();
        }
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
        person.addRide(ride);
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
    }

    public void declineRequest(User driver, User request)throws NoPermission, NotRequested{
        validateRequest(driver, request);
        requests.remove(request);
    }

}
