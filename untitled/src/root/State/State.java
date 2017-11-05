
package root.State;

import root.Exceptions.*;
import root.Ride.Permissions;
import root.Ride.Route;
import root.User.Credential;
import root.Ride.Ride;
import root.Ride.RideAdmin;
import root.User.Person;
import root.User.User;
import root.User.Vehicle;
import root.Ride.ActiveRideAdmin;
import root.Ride.ExpiredRideAdmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import java.util.List;


public class State {

    private static final State mainState = new State();

    public static State getInstance() {
        return mainState;
    }

    private List<User> users;
    private List<ActiveRideAdmin> currentRides;
    private List<ExpiredRideAdmin> expiredRides;

    //Malisimo
    private User userLogged;

    private State() {}

    private ActiveRideAdmin getActiveRideAdminOfRide(Ride ride) throws RideDoesNotExist{
        for( ActiveRideAdmin rideAdmin: currentRides){
            if(ride.equals(rideAdmin.getRide())){
                return rideAdmin;
            }
        }
        throw new RideDoesNotExist();
    }

    private ExpiredRideAdmin getExpiredRideAdminOfRide(Ride ride) throws RideDoesNotExist{
        for( ExpiredRideAdmin rideAdmin: expiredRides){
            if(ride.equals(rideAdmin.getRide())){
                return rideAdmin;
            }
        }
        throw new RideDoesNotExist();
    }

    //Feisimo
    public void initState()   throws InvalidFields {
            //Creo Users para que cuando inicialize el programa, ya hallan Users cargados.
            //Hay que chequar patente?
            Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 4, false, true);
            LocalDate bDayMica = LocalDate.of(2000, 1, 2);
            LocalDate bDayMaite = LocalDate.of(2000, 6, 30);
            Person person1 = new Person("Micaela", "Banfi", "Informatica", "1234567890", false, true, "Femenino", bDayMica);
            Person person2=new Person("Maite","Herran","Infor","11112222",false,false,"" + "Femenino", bDayMaite);
            Credential credential1 = new Credential("mica", "1234");
            Credential credential2=new Credential("maimai","maite1234");
            //Le agrego vehicle1 a person1
            //person1.addVehicle(vehicle1);
            User user1 = new User(credential1, person1);
            User user2=new User(credential2,person2);

            //Creo rides
            Ride ride1=new Ride(new Route("Victoria","Itba","Libertador"),vehicle1,person1,new Permissions(false,true,true),new Date(2017,10,11));

            //Agregamos los users al objeto estado que maneja el carPooling
            try {
                register(user1);
                register(user2);
                AddRideToList(ride1);
                //Imprimo los usuarios que cree
                System.out.println(toString());
            } catch (InvalidFields e) {
                //Este no deberia ir en possibleErrors porque lohacemos nosotros,no deberiamos ser tan tontos ;)
                System.out.println("Usuario ya existente");
            } catch (ExistingRideException e) {
                System.out.println("No se pudo crear el ride");//este tampoco en error
            }

    }

    //Authorize hace lo mismo. No lo borro para no cagar el front si lo estan usando
    @Deprecated
    public User getUser(Credential credential) throws InvalidCredentials{
            for (User user : users) {
                if (credential.equals(user.getCredential()))
                    return user;
            }
            throw new InvalidCredentials();
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

    public void sendRideRequest(Credential cred, Ride ride) throws InvalidCredentials, RideDoesNotExist, AlreadyRequested, AlreadyInRide{
        User user = authorize(cred);
        ActiveRideAdmin rideAdmin = getActiveRideAdminOfRide(ride);
        rideAdmin.addRequest(user.getPerson());
    }

    public void acceptRideRequest(Credential cred, Ride ride, Person driver, Person request) throws InvalidCredentials, RideDoesNotExist, NoPermission, NotRequested{
        User user = authorize(cred);
        ActiveRideAdmin rideAdmin = getActiveRideAdminOfRide(ride);
        rideAdmin.acceptRequest(driver, request);
    }

    public void rateRide(Credential cred, Ride ride, boolean goodRate) throws InvalidCredentials, AlreadyRated, RideDoesNotExist, NotInRide{
        User user = authorize(cred);
        RideAdmin rideAdmin = getRideAdminOfRide(ride, expiredRides);
        rideAdmin.rate(user.getPerson(), goodRate);
    }

    //TE ROMPI ESTOS DOS METODOS. Ahora existen dos subclases de RideAdmin. Son ExpiredRideAdmin y ActiveRideAdmin.
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



    //QUE QUILOMBO DE COMENTARIOS, VOY A BORRAR TODS

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
    public List<ActiveRideAdmin> getCurrentRides() { return currentRides; }

    //Creo que no lo necesitamos
    public List<ExpiredRideAdmin> getExpiredRides() { return expiredRides; }

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