package root.Ride;

import root.AlreadyRated;
import root.User.Person;
import root.SeatsTaken;
import root.NotInRide;

import java.util.ArrayList;
import java.util.HashMap;

//Podemos crear las clases ActiveRideAdmin y ExpiredRideAdmin
public class RideAdmin {
    Ride ride;
    private ArrayList<Person> passengers;

    //Expired
    private HashMap<Person, Boolean> ratings;

    public RideAdmin(Ride ride){

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
}
