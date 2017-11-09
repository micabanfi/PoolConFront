
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
import root.User.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import java.util.List;


public class State {


    private List<User> users;
    private List<ActiveRideAdmin> currentRides;
    private List<ExpiredRideAdmin> expiredRides;



    public State() {//Porque habias sacado el costructor?? Por lo que dijo franco de que cree una unica instancia de State. Creo que vos habias dicho de hacer esto tmb y yo te dije que era mala palabra jaja
        users = new ArrayList<>();
        currentRides = new LinkedList<>();
        expiredRides = new ArrayList<>();
        try {
            initState();
        } catch (Exception e) {
            System.out.println("Problema initState");
        }
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

    public void initState()  throws InvalidFields {
            //Creo Users para que cuando inicialize el programa, ya hallan Users cargados.
            //Hay que chequar patente?
            Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 4);
            Vehicle vehicle2=new Vehicle("Ford","K","Celeste",2010,"ARX420",3);
            //LocalDate bDayMica = LocalDate.of(2000, 1, 2);
            //LocalDate bDayMaite = LocalDate.of(2000, 6, 30);
            Credential credential1 = new Credential("mica", "1234");
            Credential credential2=new Credential("maimai","maite1234");
            //Le agrego vehicle1 a person1
            //person1.addVehicle(vehicle1);
            User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);
            User user2=new User(credential2,"Maite","Herran","Infor","11112222",false,false, Gender.FEMALE);
            User user3= new User(new Credential("a","a"),"a","a","a","a", true, true, Gender.MALE);
           //ESTA LINEA ME GENERA ALTO ERROR user1.addVehicle(vehicle1);
            //Creo rides
            LocalDateTime date=LocalDateTime.of(2017,12,20,14,30);
            Ride ride1=new Ride(new Route("Victoria","Itba","Libertador"),vehicle1,user1,new Permissions(false,true,true), date);
            LocalDateTime date2=LocalDateTime.of(2017,8,3,9,15);
            Ride ride2=new Ride(new Route("Mi Casa","Tu casa","Paranamerica"),vehicle2,user2,new Permissions(false,true,true),date2);
            //Agregamos los users al objeto estado que maneja el carPooling
            try {

                register(user1);
                register(user2);
                register(user3);
                AddRideToList(ride1);
                AddRideToList(ride2);
                System.out.println("Size rides:"+currentRides.size());
                //Imprimo los usuarios que cree
            } catch (InvalidFields e) {
                //Este no deberia ir en possibleErrors porque lohacemos nosotros,no deberiamos ser tan tontos ;)
                System.out.println("Usuario ya existente");
            } catch (ExistingRideException e) {
                System.out.println("No se pudo crear el ride");//este tampoco en error
            } catch(Exception e){
                System.out.println(e.getMessage());
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
        rideAdmin.addRequest(user);
    }

    public void acceptRideRequest(Credential cred, Ride ride, User driver, User request) throws InvalidCredentials, RideDoesNotExist, NoPermission, NotRequested{
        User user = authorize(cred);//y eso??
        ActiveRideAdmin rideAdmin = getActiveRideAdminOfRide(ride);
        rideAdmin.acceptRequest(driver, request);
    }

    public void rateRide(Credential cred, Ride ride, boolean goodRate) throws InvalidCredentials, AlreadyRated, RideDoesNotExist, NotInRide{
        User user = authorize(cred);
        ExpiredRideAdmin rideAdmin = getExpiredRideAdminOfRide(ride);
        rideAdmin.rate(user, goodRate);
    }

    public void AddRideToList(Ride ride) throws ExistingRideException{
        int i;
        for(i = 0;  i<currentRides.size() && (ride.compareTo(currentRides.get(i).getRide()) <= 0); i++){
            if(currentRides.get(i).getRide().equals(ride))
                throw new ExistingRideException();

        }
        System.out.println("I de rides "+i);
        ActiveRideAdmin aux = new ActiveRideAdmin(ride);
        currentRides.add(i, aux);
    }

    public void deleteRide(Ride ride){
        ActiveRideAdmin aux=new ActiveRideAdmin(ride);
        currentRides.remove(aux);

    }

    // Arreglar lo de Date en todo el programa
    public void refreshRides(){
        boolean aux = true;
        LocalDateTime currentDate = LocalDateTime.now();
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