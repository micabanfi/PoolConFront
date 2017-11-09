package root.User;

import root.Exceptions.NoVehicleException;
import root.Ride.Ride;

import java.util.ArrayList;

public class User extends Person{
    private Credential credential;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Ride> expiredRides;
    private ArrayList<Ride> activeRides;
    private Rating rating;
    private boolean driver;
   
    public User(Credential credential, String name, String surname, String career, String phone,boolean smoke,boolean food,Gender gender){
        super(name, surname, career, phone, smoke, food, gender);
        this.vehicles = new ArrayList<Vehicle>();
        this.expiredRides = new ArrayList<Ride>();
        this.activeRides = new ArrayList<Ride>();
        this.credential=credential;
        this.rating = new Rating();
        this.driver= true;
    }
    
    public boolean getDriver() {
    	return driver;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                "credential=" + credential +
                ", rating=" + rating +
                '}';
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

    public Credential getCredential(){
        return credential;
    }

    public User getUser(){
        return new User(this.credential, this.getName(), this.getSurname(), this.getPreferences().getCareer(), this.getPhone(), this.getPreferences().isSmoke(), this.getPreferences().ifFood(), this.getGender());
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
        this.driver = true;
    }

    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
        if(vehicles.isEmpty()) {
        	this.driver = false;
        }
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
    		vehicleNames.add(vehicles.get(i).toString());
    		System.out.println(vehicleNames.get(i));
    	}	    	
    return vehicleNames;
    }
    	
    
    public ArrayList<Vehicle> getVehicles(){
    	return vehicles;
    }
    
}