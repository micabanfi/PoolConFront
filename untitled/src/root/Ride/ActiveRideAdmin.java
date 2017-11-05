package root.Ride;

import root.Exceptions.*;
import root.User.Person;

import java.util.ArrayList;

public class ActiveRideAdmin extends RideAdmin {

    private ArrayList<Person> requests;

    public ActiveRideAdmin(Ride ride){
        super(ride);
        requests = new ArrayList<>();
    }

    public void addPassenger(Person passenger)throws SeatsTaken {
        if(ride.getVehicle().getSeats() > passengers.size()){
            passengers.add(passenger);
        }else{
            throw new SeatsTaken();
        }
    }

    public void removePassenger(Person passenger) throws NotInRide {
        if(passengers.contains(passenger)){
            passengers.remove(passenger);
        }else{
            throw new NotInRide();
        }
    }

    public void addRequest(Person person) throws AlreadyRequested, AlreadyInRide {
        if(requests.contains(person)){
            throw new AlreadyRequested();
        }
        if(passengers.contains(person) || person.equals(ride.getDriver())){
            throw new AlreadyInRide();
        }
        requests.add(person);
    }

    private void validateRequest(Person driver, Person request) throws NoPermission, NotRequested{
        if(!requests.contains(request)) {
            throw new NotRequested();
        }
        if (!driver.equals(ride.getDriver())) {
            throw new NoPermission();
        }
    }

    public void acceptRequest(Person driver, Person request) throws NoPermission, NotRequested{
        validateRequest(driver, request);
        passengers.add(request);
        requests.remove(request);
    }

    public void declineRequest(Person driver, Person request)throws NoPermission, NotRequested{
        validateRequest(driver, request);
        requests.remove(request);
    }

    public ArrayList<Person> getRequests() {
        return requests;
    }
}
