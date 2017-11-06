
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

    //Malisimo. Estoy muuuy de acuerdo. Tenemos que modificarles el front asi no hacemos esta forrada.
    private User userLogged;

    private State() {//Porque habias sacado el costructor?? Por lo que dijo franco de que cree una unica instancia de State. Creo que vos habias dicho de hacer esto tmb y yo te dije que era mala palabra jaja
        users = new ArrayList<>();
        currentRides = new LinkedList<>();
        expiredRides = new ArrayList<>();
    }

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

    //Authorize hace lo mismo. No lo borro para no cagar el front si lo estan usando. y porque queda fachero
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

    private User authorize(Credential cred) throws InvalidCredentials{
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
        User user = authorize(cred);//y eso??
        ActiveRideAdmin rideAdmin = getActiveRideAdminOfRide(ride);
        rideAdmin.acceptRequest(driver, request);
    }

    public void rateRide(Credential cred, Ride ride, boolean goodRate) throws InvalidCredentials, AlreadyRated, RideDoesNotExist, NotInRide{
        User user = authorize(cred);
        ExpiredRideAdmin rideAdmin = getExpiredRideAdminOfRide(ride);
        rideAdmin.rate(user.getPerson(), goodRate);

        /* Arregle lo que deje arriba
        User user = authorize(cred);
        ExpiredRideAdmin era;
        for (ExpiredRideAdmin expRide : expiredRides) {
            if (expRide.getRide().equals(ride)){
                era = expRide;
                era.rate(user.getPerson(), goodRate);
                return;
                }
            }
        throw new RideDoesNotExist();
        */
    }

    public void AddRideToList(Ride ride) throws ExistingRideException{
        int i;
        for(i = 0; ride.compareTo(currentRides.get(i).getRide()) >= 0; i++){
            if(currentRides.get(i).getRide().equals(ride))
                throw new ExistingRideException();
        }
        ActiveRideAdmin aux = new ActiveRideAdmin(ride);
        currentRides.add(i, aux);
    }

    // Arreglar lo de Date en todo el programa
    public void refreshRides(){
        boolean aux = true;
        Date currentDate = new Date();
        for (int i = 0; aux ; i++) {
            RideAdmin ride = currentRides.get(i);
            if (ride.getRide().getDate().compareTo(currentDate) < 0){
                expiredRides.add(new ExpiredRideAdmin(ride.getRide(), currentRides.get(i).getRequests()));
                currentRides.remove(i);
            }
            else{
                aux = false;
            }
        }
    }

    public List<ActiveRideAdmin> getCurrentRides() { return currentRides; }

    public List<ExpiredRideAdmin> getExpiredRides() { return expiredRides; }

    //Deberia recibir credentials para que sea consistente con los otros metodos y ademas verificar que tenga permiso para hacerlo.
    public User modifyUser(User user) throws NotExistingUserException{
       for(User us : users) {
           if (us.equals(user)) {
               users.remove(us);
               users.add(user);
               return user;
           }
       }
       throw new NotExistingUserException();
   }

    //QUE QUILOMBO DE COMENTARIOS, VOY A BORRAR TODS

    /*
       public RideAdmin saveNewRide(Ride ride) throws InvalidFields{
           if (AddToList(ride))
               return ride;
           throw new InvalidFields("Invalid Ride");
       }
    */

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

}