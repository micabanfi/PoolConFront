package Tests;

import org.junit.jupiter.api.Test;
import root.Exceptions.ExistingRideException;
import root.Exceptions.InvalidCredentials;
import root.Exceptions.InvalidFields;
import root.Ride.ActiveRideAdmin;
import root.Ride.Permissions;
import root.Ride.Ride;
import root.Ride.Route;
import root.State.State;
import root.User.Credential;
import root.User.Gender;
import root.User.User;
import root.User.Vehicle;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    private static final State state = new State();

    private Credential credential1 = new Credential("mica", "1234");
    private Credential credential2=new Credential("maimai","maite1234");

    private User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);
    private User user2=new User(credential2,"Maite","Herran","Infor","11112222",false,false, Gender.FEMALE);

    private Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 0);
    private LocalDateTime date=LocalDateTime.of(2018,12,20,14,30);
    private Ride ride1 = new Ride(new Route("Victoria","Itba","Libertador"),vehicle1,user1,new Permissions(false,true), date);
    private ActiveRideAdmin activerideadmin = new ActiveRideAdmin(ride1);



    @Test
    void loginTest() {
        registerTest();
        User aux = null;
        try {
            aux = state.login(credential1);
        }catch(InvalidCredentials e){
            e.fillInStackTrace();
        }
        assertEquals(user1, aux);

    }

    @Test
    void registerTest() {
        try {
            state.register(user1);
            state.register(user2);
        }catch (InvalidFields e){
            e.printStackTrace();
        }

        assertTrue(state.getUsers().contains(user1));
        assertTrue(state.getUsers().contains(user2));
        assertTrue(state.getUsers().size() == 2);
    }

    @Test
    void addRideToListTest() {
        try {
            state.addRideToList(activerideadmin);
        } catch (ExistingRideException e) {
            e.printStackTrace();
        }
        assertFalse(state.getCurrentRides().isEmpty());
        assertTrue(state.getCurrentRides().contains(activerideadmin));
    }

    @Test
    void deleteRideTest() {
        addRideToListTest();
        state.deleteRide(activerideadmin);

        assertTrue(!state.getCurrentRides().contains(activerideadmin));
    }

}