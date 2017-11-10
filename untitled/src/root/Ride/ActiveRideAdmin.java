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

    public Ride getRide(){
        return ride;
    }
    public ArrayList<User> getRequests() {
        return requests;
    }

    public void addPassenger(User passenger)throws SeatsTaken {
        System.out.println("Asientos:"+ride.getVehicle().getSeats()+" pasajeros size:"+passengers.size());
        if(ride.getVehicle().getSeats() > passengers.size()){
            passengers.add(passenger);
            ride.getVehicle().setSeats((ride.getVehicle().getSeats())-1);
            System.out.println("Asientos:"+ride.getVehicle().getSeats());
            System.out.println("pasajeros size despues:"+passengers.size());
            //System.out.println(passengers.toString());
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

    public void addRequest(User person) throws AlreadyRequested, AlreadyInRide {
        System.out.println("Asientos:"+ride.getVehicle().getSeats()+" pasajeros size:"+passengers.size());

        if(requests.contains(person)){
            throw new AlreadyRequested();
        }
        if(passengers.contains(person) || person.equals(ride.getDriver())){
            throw new AlreadyInRide();
        }
        requests.add(person);
        System.out.println(requests.get(0).toString());
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

    public void acceptRequest(User driver, User request) throws NoPermission, NotRequested{
        validateRequest(driver, request);
        passengers.add(request);
        requests.remove(request);
    }

    public void declineRequest(User driver, User request)throws NoPermission, NotRequested{
        validateRequest(driver, request);
        requests.remove(request);
    }


}
