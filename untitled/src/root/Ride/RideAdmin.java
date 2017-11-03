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


public class RideAdmin {
    Ride ride;
    private ArrayList<Person> passengers;

    //Expired
    private HashMap<Person, Boolean> ratings;

    //Active
    private ArrayList<Person> requests;

    public RideAdmin(Ride ride){

    }

    public String toString(){
        return "Viaje\nRuta"+ride.getRoute()+"\nChofer"+ride.getDriver();
    }

    public Ride getRide() {
        return ride;
    }

    public ArrayList<Person> getPassengers(){
        return passengers;
    }

    //Expired
    public HashMap<Person, Boolean> getRatings(){
        return ratings;
    }

    //Expired
    public void rate(Person person, Boolean goodRate) throws AlreadyRated, NotInRide, NullPointerException{
        if(goodRate == null) throw new NullPointerException();
        if(ratings.containsKey(person)){
            if(ratings.get(person) == null){
                ratings.put(person, goodRate);
            }else{
                throw new AlreadyRated();
            }
        }else{
            throw new NotInRide();
        }
    }

    //Active
    public void addPassenger(Person passenger)throws SeatsTaken{
        if(ride.getVehicle().getSeats() > passengers.size()){
            passengers.add(passenger);
        }else{
            throw new SeatsTaken();
        }
    }

    //Active
    public void removePassenger(Person passenger) throws NotInRide {
        if(passengers.contains(passenger)){
            passengers.remove(passenger);
        }else{
            throw new NotInRide();
        }
    }

    //Active
    public void addRequest(Person person) throws AlreadyRequested, AlreadyInRide{
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
        if (driver.equals(ride.getDriver())) {
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
}
