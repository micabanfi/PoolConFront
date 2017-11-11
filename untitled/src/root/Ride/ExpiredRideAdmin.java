package root.Ride;

import root.Exceptions.AlreadyRated;
import root.Exceptions.NotInRide;
import root.User.Person;
import root.User.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpiredRideAdmin extends RideAdmin implements Serializable{

    private static final long serialVersionUID = 1L;

    private Map<User, Boolean> ratings;

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(ride);
        out.writeObject(passengers);
        out.writeObject(ratings);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        ride = (Ride) ois.readObject();
        passengers = (List<User>) ois.readObject();
        ratings = (Map<User, Boolean>) ois.readObject();
    }

    public ExpiredRideAdmin(Ride ride, List<User> passengers){
        super(ride, passengers);
        ratings = new HashMap<>();
    }

    public Map<User, Boolean> getRatings(){
        return ratings;
    }

    public void rate(User person, Boolean goodRate) throws AlreadyRated, NotInRide, NullPointerException{
        if(goodRate == null) throw new NullPointerException();
        if(ratings.containsKey(person)){
            if(ratings.get(person) == null){
                ratings.put(person, goodRate);
            }else{
                throw new AlreadyRated();
            }
        }else{
            throw new NotInRide();
        }
    }
}
