package root.User;

import root.Exceptions.DeniedDriverException;
import root.Ride.ActiveRideAdmin;
import root.Ride.ExpiredRideAdmin;
import root.Ride.Ride;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class User extends Person implements Serializable{

    private static final long serialVersionUID = 1L;

    private Credential credential;
    private List<Vehicle> vehicles;
    private List<ExpiredRideAdmin> expiredRides;
    private List<ActiveRideAdmin> activeRides;
    private Rating rating;
   
 
   
    public User(Credential credential, String name, String surname, String career, String phone,boolean smoke,boolean food,Gender gender){
        super(name, surname, career, phone, smoke, food, gender);
        this.vehicles = new ArrayList<>();
        this.expiredRides = new ArrayList<>();
        this.activeRides = new ArrayList<>();
        this.credential=credential;
        this.rating = new Rating();
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(credential);
        out.writeObject(vehicles);
        out.writeObject(activeRides);
        out.writeObject(expiredRides);
        out.writeObject(rating);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        credential = (Credential) ois.readObject();
        vehicles = (List<Vehicle>) ois.readObject();
        activeRides = (List<ActiveRideAdmin>) ois.readObject();
        expiredRides = (List<ExpiredRideAdmin>) ois.readObject();
        rating = (Rating) ois.readObject();
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

    public List<ActiveRideAdmin> getActiveRides(){
        return activeRides;
    }

    public List<ExpiredRideAdmin> getExpiredRides(){
        return expiredRides;
    }

    public Credential getCredential(){
        return credential;
    }

    public User getUser(){
        return new User(this.credential, this.getName(), this.getSurname(), this.getPreferences().getCareer(), this.getPhone(), this.getPreferences().isSmoke(), this.getPreferences().ifFood(), this.getGender());
    }


    public void addRide(ActiveRideAdmin ride){
    	if(!activeRides.contains(ride)) {
    		activeRides.add(ride);
    	}
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

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + credential.hashCode();
        result = 31 * result + vehicles.hashCode();
        result = 31 * result + expiredRides.hashCode();
        result = 31 * result + activeRides.hashCode();
        result = 31 * result + rating.hashCode();
        return result;
    }
    
    	
    
    public List<Vehicle> getVehicles(){
    	return vehicles;
    }
    
}