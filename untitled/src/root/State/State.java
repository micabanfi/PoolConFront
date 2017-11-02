
package root.State;

import root.Exceptions.AlreadyRated;
import root.Exceptions.ExistingRideException;
import root.Exceptions.InvalidCredentials;
import root.Exceptions.InvalidFields;
import root.User.Credential;
import root.Ride.Ride;
import root.Ride.RideAdmin;
import root.User.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;


public class State {
    private ArrayList<User> users;
    private LinkedList<RideAdmin> currentRides;
    private ArrayList<RideAdmin> expiredRides;

    public State() {
        users = new ArrayList<User>();
        currentRides = new LinkedList<RideAdmin>();
        expiredRides = new ArrayList<RideAdmin>();
    }
    //BORRAR DESPUES
    @Override
    public String toString() {
        String rta=new String();
        for(User usuario:users)
            rta+=usuario.toString();
        return rta;
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
        if(!(users.contains(user))) {
            users.add(user);
            return user;
        }
        throw new InvalidFields("User already Exists");
    }

    public void sendRideRequest(RideAdmin ride, Credential cred) throws InvalidCredentials{
        User user = authorize(cred);

    }

    //Me parece que ordenarlo no es tan necesario y va a ser poco claro
    public void AddRideToListByDate(Ride ride) throws ExistingRideException{//No me tiran los 50 errores que deberia tirar... arreglarlo dsps
        boolean flag = true;
        for(int i = 0; flag || i < currentRides.size(); i++){// Feo feo, dsps me lo cambio bien
            if (ride.equals(currentRides.get(i).getRide()))
                throw new ExistingRideException();
            if (!(currentRides.get(i).getRide().getDate().before(ride.getDate()))) {
                currentRides.add(i, new RideAdmin(ride));
                flag = true;
            }
        }
    }

    public void AddRideToList(Ride ride) throws ExistingRideException{
        for(RideAdmin rideAdmin : currentRides) {
            if(rideAdmin.getRide().equals(ride))
                throw new ExistingRideException();
        }
        RideAdmin aux = new RideAdmin(ride);
        currentRides.add(aux);
    }

    public void rateRide(Credential cred, Ride ride, boolean goodRating) throws InvalidCredentials, AlreadyRated{

    }
    /*
       public RideAdmin saveNewRide(Ride ride) throws InvalidFields{
           if (AddToList(ride))
               return ride;
           throw new InvalidFields("Invalid Ride");
       }
    */
// tenemos que settear una/entender la timezone para/de todo el proyecto. No entendi nada
    public void refreshRides(){
        boolean aux = true;
        Date currentDate = new Date();
        for (int i = 0; aux ; i++) {
            RideAdmin ride = currentRides.get(i);
            if (ride.getRide().getDate().before(currentDate)){
                currentRides.remove(i);
                expiredRides.add(ride);
            }
            else{
                aux = false;
            }
        }
    }

    /* Si es que usamos este metodo, cambiarlo para que sea compatible a RideAdmin
        public Ride modifyRide(Ride ride) throws InvalidFields{
            for(int i = 0; i < users.size(); i++){
                Ride aux = currentRides.get(i);
                if (aux.getRide().equals(ride)){
                    currentRides.remove(i);
                    currentRides.add(ride);
                    return ride;
                }
            }
            throw new InvalidFields("No Ride With The Same Characteristics.");
       }
    */
    public LinkedList<RideAdmin> getCurrentRides() { return currentRides; }

    //Creo que no lo necesitamos
    public ArrayList<RideAdmin> getExpiredRides() { return expiredRides; }

   /*
   public User modifyUser(User user) throw ExistingUserException{
       for(User us : Users) {
           if (us.equals(user)) {
               //User.remove(us); no se si funca
               int i = Users.indexOf(us);
               User.remove(i);
               User.add(user);
               return user;
           }
       }
       throw new ExistingUserException();
   }
   */
}