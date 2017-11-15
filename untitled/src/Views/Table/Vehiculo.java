package Views.Table;

import root.User.Vehicle;

public class Vehiculo {
    private Vehicle vehicle;

    public Vehiculo(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString(){
        return "Vehicle:"+'\n'+"Brand:"+vehicle.getBrand()+"Model:"+vehicle.getModel()+" Color:"+vehicle.getColor()+" Plate:"+vehicle.getPlate()+"Seats:"+vehicle.getSeats();
    }
}
