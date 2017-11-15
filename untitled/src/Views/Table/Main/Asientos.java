package Views.Table.Main;

import root.User.User;
import root.User.Vehicle;

import java.util.List;

public class Asientos {
    private List<User> passengers;
    private Vehicle vehicle;

    public Asientos(List<User> passengers, Vehicle vehicle){
        this.passengers = passengers;
        this.vehicle = vehicle;
    }

    @Override
    public String toString(){
        return Integer.toString(vehicle.getSeats() - passengers.size());
    }
}
