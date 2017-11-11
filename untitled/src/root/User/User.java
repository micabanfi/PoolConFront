package root.User;

import root.Exceptions.DeniedDriverException;
import root.Exceptions.NoVehicleException;
import root.Ride.ActiveRideAdmin;
import root.Ride.ExpiredRideAdmin;
import root.Ride.Ride;

import java.util.ArrayList;
import java.util.List;

public class User extends Person{
    private Credential credential;
    private ArrayList<Vehicle> vehicles;//cambiar a LIST
    private ArrayList<ExpiredRideAdmin> expiredRides;
    private ArrayList<ActiveRideAdmin> activeRides;
    private Rating rating;
   
    public User(Credential credential, String name, String surname, String career, String phone,boolean smoke,boolean food,Gender gender){
        super(name, surname, career, phone, smoke, food, gender);
        this.vehicles = new ArrayList<Vehicle>();
        this.expiredRides = new ArrayList<ExpiredRideAdmin>();
        this.activeRides = new ArrayList<ActiveRideAdmin>();
        this.credential=credential;
        this.rating = new Rating();
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return  super.toString() + credential.toString() + rating.toString();
    }


    public Rating getRating() {
        return rating;
    }

    public ArrayList<ActiveRideAdmin> getActiveRides(){
        return activeRides;
    }

    public ArrayList<ExpiredRideAdmin> getExpiredRides(){
        return expiredRides;
    }

    public Credential getCredential(){
        return credential;
    }

    public User getUser(){
        return new User(this.credential, this.getName(), this.getSurname(), this.getPreferences().getCareer(), this.getPhone(), this.getPreferences().isSmoke(), this.getPreferences().ifFood(), this.getGender());
    }


    public void addRide(ActiveRideAdmin ride){
        activeRides.add(ride);
    }

    public void removeRide(Ride ride){
        activeRides.remove(ride);
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
    
    
    public ArrayList<String> getVehicleNames(){
    	
    	ArrayList<String> vehicleNames = new ArrayList<String>();
    	for(int i =0; i < vehicles.size() ; i++) {
    		vehicleNames.add(vehicles.get(i).getVehicleInfo());
    	}	    	
    return vehicleNames;
    }
    	
    
    public ArrayList<Vehicle> getVehicles(){
    	return vehicles;
    }
    
}