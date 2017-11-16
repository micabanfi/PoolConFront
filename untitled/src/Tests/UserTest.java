package Tests;

import org.junit.jupiter.api.Test;
import root.Ride.ActiveRideAdmin;
import root.Ride.Permissions;
import root.Ride.Ride;
import root.Ride.Route;
import root.User.Credential;
import root.User.Gender;
import root.User.User;
import root.User.Vehicle;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Credential credential1 = new Credential("mica", "1234");
    private Credential credential2=new Credential("maimai","maite1234");

    private final User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);
    private final User user2=new User(credential2,"Maite","Herran","Infor","11112222",false,false, Gender.FEMALE);
    private final User user3= new User(credential1,"a","a","a","a", true, true, Gender.MALE);

    private Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 0);
    private LocalDateTime date=LocalDateTime.of(2018,12,20,14,30);
    private Ride ride1 = new Ride(new Route("Victoria","Itba","Libertador"),vehicle1,user1,new Permissions(false,true), date);
    private ActiveRideAdmin activerideadmin = new ActiveRideAdmin(ride1);

    @Test
    void TestUser(){
        assertTrue(user1.equals(user3));
        assertFalse(user1.equals(user2));
    }

    @Test
    void addRideTest() {
        user1.addRide(activerideadmin);
        user2.addRide(activerideadmin);

        assertTrue(user1.getActiveRides().contains(activerideadmin));
        assertTrue(user2.getActiveRides().contains(activerideadmin));

    }

    @Test
    void removeRideTest() {
        addRideTest();
        user1.removeRide(ride1);

        assertFalse(user1.getActiveRides().contains(activerideadmin));
    }

}