package Tests;

import org.junit.jupiter.api.Test;
import root.Exceptions.*;
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

class ActiveRideAdminTest {

    private Credential credential1 = new Credential("mica", "1234");
    private Credential credential2=new Credential("maimai","maite1234");

    private User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);
    private User user2=new User(credential2,"Maite","Herran","Infor","11112222",false,false, Gender.FEMALE);

    private Vehicle vehicle1=new Vehicle("Ford","K","Celeste",2010,"ARX420",3);

    private LocalDateTime date=LocalDateTime.of(2018,12,20,14,30);
    private Ride ride1=new Ride(new Route("Victoria","Itba","Libertador"),vehicle1,user2,new Permissions(false,true), date);
    private ActiveRideAdmin rideAdmin1 = new ActiveRideAdmin(ride1);

    @Test
    void leaveRideTest() {
        rideAdmin1.leaveRide(user2);

        assertFalse(rideAdmin1.getPassengers().contains(user2));
    }

    @Test
    void addRequestTest() {
        try {
            rideAdmin1.addRequest(user2);
        }catch(AlreadyRequested | SeatsTaken | AlreadyInRide e){
            e.fillInStackTrace();
        }

        assertTrue(rideAdmin1.getRequests().contains(user1));
    }

    @Test
    void acceptRequestTest() {
        addRequestTest();
        try {
            rideAdmin1.acceptRequest(user1, user2);
        } catch (NoPermission | NotRequested | SeatsTaken noPermission) {
            noPermission.printStackTrace();
        }
        assertTrue(rideAdmin1.getPassengers().contains(user2));
    }

    @Test
    void declineRequestTest() {
        addRequestTest();
        try {
            rideAdmin1.declineRequest(user1, user2);
        } catch (NoPermission | NotRequested noPermission) {
            noPermission.printStackTrace();
        }
        assertFalse(rideAdmin1.getPassengers().contains(user2));
        assertFalse(rideAdmin1.getRequests().contains(user2));
    }

}