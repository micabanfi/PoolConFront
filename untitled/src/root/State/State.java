package root.State;

import root.AlreadyRated;
import root.InvalidCredentials;
import root.InvalidFields;
import root.User.Credential;
import root.Ride.Ride;
import root.User.Person;
import root.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;


public class State {
    private ArrayList<User> users;
    private ArrayList<Ride> currentRides;
    private ArrayList<Ride> expiredRides;

    public State() {

    }

    public User login(Credential cred) throws InvalidCredentials{
        return authorize(cred);
    }

    public User authorize(Credential cred) throws InvalidCredentials{
        for (User user : users){
            if (user.equalCredentials(cred)) {
                return user;
            }

        }
        throw new InvalidCredentials();
    }

    public User register(User user) throws InvalidFields{
        if (AddToList(user)) {
            //Faltan mas validaciones
            users.add(user);
            return user;
        }
        throw new InvalidFields("Invalid User");
    }

    // Metodo de Prueba
    private <T> boolean AddToList(T ent){
        boolean flag = false;
        if (ent instanceof  User){
            if (!(users.contains(ent))) {
                users.add((User)ent);
                flag = true;
            }
        }
        if (ent instanceof  Ride){
            if (!(currentRides.contains(ent))) {
                currentRides.add((Ride)ent);
                flag = true;
            }
        }
        return flag;
    }

    public void rateRide(Credential cred, Ride ride, boolean goodRating) throws InvalidCredentials, AlreadyRated{

    }

    public Ride saveNewRide(Ride ride) throws InvalidFields{
        if (AddToList(ride))
            return ride;
        throw new InvalidFields("Invalid Ride");
    }

    // tenemos que settear una/entender la timezone para/de todo el proyecto. No entendi nada
    public void refreshRides(){
        boolean aux = true;
        Date currentDate = new Date();
        for (int i = 0; aux ; i++) {
            Ride ride = currentRides.get(i);
            if (ride.getDate().before(currentDate)){
                currentRides.remove(i);
                expiredRides.add(ride);
            }
            else{
                aux = false;
            }
        }
    }

    public Ride modifyRide(Ride ride) throws InvalidFields{
        for(int i = 0; i < users.size(); i++){
            Ride aux = currentRides.get(i);
            if (aux.equals(ride)){
                currentRides.remove(i);
                currentRides.add(ride);
                return ride;
            }
        }
        throw new InvalidFields("No Ride With The Same Characteristics.");
    }

    public ArrayList<Ride> getCurrentRides() { return currentRides; }

    //Creo que no lo necesitamos
    public ArrayList<Ride> getExpiredRides() { return expiredRides; }
}
