package root.User;

import root.Client.Client;
import root.Ride.Ride;

import java.util.ArrayList;

public class User {
    private Credential credential;
    private Person person;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Ride> expiredRides;
    private ArrayList<Ride> activeRides;
    private Rating rating;

    public User(Credential credential, Person person){
        this.credential=credential;
        this.person=person;
        this.rating = new Rating();
    }

    @Override
    public String toString() {
        return person.toString()+credential.toString();
    }

    public void setUser(User user){
        this.credential=user.getCredential();
        this.person=user.getPerson();
        //this.vehicles=user.getVehicles;
    }

    public Rating getRating() {
        return rating;
    }
    public ArrayList<Ride> getActiveRides(){
        return activeRides;
    }

    public ArrayList<Ride> getExpiredRides(){
        return expiredRides;
    }

    public Person getPerson(){
        return person;
    }

    public Credential getCredential(){
        return credential;
    }

    public User getUser(){
        return new User(this.credential,this.person);
    }


    public void addRide(Ride ride){
        activeRides.add(ride);
    }

    public void removeRide(Ride ride){
        activeRides.remove(ride);
    }

    public void sendToHistory(Ride ride){
        activeRides.remove(ride);
        expiredRides.add(ride);
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    public boolean equalCredentials(Credential credential){
        return credential.equals(this.credential);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        User aux = (User) obj;
        return aux.getCredential().equals(credential);
    }
    /*public double compatibility(User user) {

    }
    */

}