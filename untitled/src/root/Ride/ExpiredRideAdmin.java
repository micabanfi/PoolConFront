package root.Ride;

import root.Exceptions.AlreadyRated;
import root.Exceptions.NotInRide;
import root.User.Person;

import java.util.List;
import java.util.Map;

public class ExpiredRideAdmin extends RideAdmin{

    private Map<Person, Boolean> ratings;

    public ExpiredRideAdmin(Ride ride, List<Person> passengers){
        super(ride, passengers);
    }

    public Map<Person, Boolean> getRatings(){
        return ratings;
    }

    public void rate(Person person, Boolean goodRate) throws AlreadyRated, NotInRide, NullPointerException{
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
