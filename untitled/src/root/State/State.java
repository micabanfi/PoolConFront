
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

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import java.util.List;


public class State implements Serializable{

    //private static final long serialVersionUID = 1L;
    //private static final File file = new File("../PoolConFront/untitled/src/root/Data");


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

    public void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeObject(users);
        out.writeObject(currentRides);
        out.writeObject(expiredRides);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        users = (List<User>) ois.readObject();
        currentRides = (List<ActiveRideAdmin>) ois.readObject();
        expiredRides = (List<ExpiredRideAdmin>) ois.readObject();
    }

    public void initState() throws InvalidFields, IOException, ClassNotFoundException {

        //loadData(file);

        Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 4);
        Vehicle vehicle2=new Vehicle("Ford","K","Celeste",2010,"ARX420",3);

        Credential credential1 = new Credential("mica", "1234");
        Credential credential2=new Credential("maimai","maite1234");

        User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);
        User user2=new User(credential2,"Maite","Herran","Infor","11112222",false,false, Gender.FEMALE);
        User user3= new User(new Credential("a","a"),"a","a","a","a", true, true, Gender.MALE);

        user1.addVehicle(vehicle1);
        user1.addVehicle(vehicle2);
        user2.addVehicle(vehicle1);

        LocalDateTime date=LocalDateTime.of(2018,12,20,14,30);
        Ride ride1=new Ride(new Route("Victoria","Itba","Libertador"),vehicle1,user1,new Permissions(false,true), date);
        ActiveRideAdmin rideAdmin1 = new ActiveRideAdmin(ride1);

        LocalDateTime date2=LocalDateTime.of(2018,8,3,9,15);
        Ride ride2=new Ride(new Route("Mi Casa","Tu casa","Paranamerica"),vehicle2,user2,new Permissions(false,true),date2);

        LocalDateTime date3 = LocalDateTime.of(2017, 11,11,15,15);
        Ride oldRide = new Ride(new Route ("Nordelta", "ITBA", "Panamericana"), vehicle1, user1, new Permissions(false, false), date3);

        List<User> listOldRide = new ArrayList<>();
        listOldRide.add(user2);
        listOldRide.add(user3);

        ExpiredRideAdmin oldRideExp = new ExpiredRideAdmin(oldRide, listOldRide);
        ActiveRideAdmin rideAdmin2 = new ActiveRideAdmin(ride2);


        //System.out.println(1);

        try {

            register(user1);
            register(user2);
            register(user3);
            addRideToList(rideAdmin1);
            addRideToList(rideAdmin2);
            user1.addRide(rideAdmin1);
            user2.addRide(rideAdmin2);
            user1.getExpiredRides().add(oldRideExp);

           // System.out.println(user1.getActiveRides().get(0).toString());

        } catch (InvalidFields e) {
            //Este no deberia ir en possibleErrors porque lohacemos nosotros,no deberiamos ser tan tontos ;)
            System.out.println("Usuario ya existente");
        } catch (ExistingRideException e) {
            System.out.println("No se pudo crear el ride");//este tampoco en error
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        for (User auss : users)
            System.out.println(auss.toString());


    }

    public User login(Credential cred) throws InvalidCredentials{
        for (User user : users){
            if (user.equalCredentials(cred)){
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

    public void removeUser(User user){
        users.remove(user);
    }

    public void addRideToList(ActiveRideAdmin ride) throws ExistingRideException{
        if(currentRides.contains(ride)){
            throw new ExistingRideException();
        }
        currentRides.add(ride);
        ride.getRide().getDriver().addRide(ride);
    }

    public void deleteRide(ActiveRideAdmin ride){
            for(User user: ride.getPassengers()){
                user.getActiveRides().remove(ride);
            }
            ride.getRide().getDriver().getActiveRides().remove(ride);
            currentRides.remove(ride);
    }

    // Arreglar lo de Date en todo el programa
    public void refreshRides(){
        boolean aux = true;
        LocalDateTime currentDate = LocalDateTime.now();
        for (int i = 0; aux ; i++) {
            ActiveRideAdmin ride = currentRides.get(i);
            if (ride.getRide().getDate().compareTo(currentDate) < 0){
                ExpiredRideAdmin expired = new ExpiredRideAdmin(ride.getRide(), currentRides.get(i).getPassengers());
                expiredRides.add(expired);
                for(User user: ride.getPassengers()){
                    user.getActiveRides().remove(currentRides.get(i));
                    user.getExpiredRides().add(expired);
                }
                currentRides.remove(i);
            }
            else{
                aux = false;
            }
        }
    }

    public List<ActiveRideAdmin> getCurrentRides() { return currentRides; }

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

    // metodos para llamar la serializacion/ guardar/loadear datos

    public void loadData(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        users = (List<User>) objectInputStream.readObject();
        currentRides = (List<ActiveRideAdmin>) objectInputStream.readObject();
        expiredRides = (List<ExpiredRideAdmin>) objectInputStream.readObject();
        objectInputStream.close();
    }

    public void saveData(File file) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(users);
        objectOutputStream.writeObject(currentRides);
        objectOutputStream.writeObject(expiredRides);
        objectOutputStream.close();
    }


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