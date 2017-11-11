package root.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Vehicle implements Serializable{

    private static final long serialVersionUID = 1L;

    private String brand;
    private String model;
    private String color;
    private int year;
    private String plate;
    private int seats;

    public Vehicle(String brand,String model,String color,int year,String plate,int seats ){
        this.brand=brand;
        this.model=model;
        this.color=color;
        this.year=year;
        this.plate=plate;
        this.seats=seats;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(brand);
        out.writeUTF(model);
        out.writeUTF(color);
        out.writeInt(year);
        out.writeUTF(plate);
        out.writeInt(seats);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        brand = ois.readUTF();
        model = ois.readUTF();
        color = ois.readUTF();
        year = ois.readInt();
        plate = ois.readUTF();
        seats = ois.readInt();
    }
    //sabemos q el tostring este es feo, pero si no lo hacemos asi, no nos funciona el front bien.
    @Override
    public String toString(){
        //return "Vehicle:"+'\n'+"Brand:"+brand+"Model:"+model+" Color:"+color+" Plate:"+plate+"Seats:"+seats;
        System.out.println(seats);
        return Integer.toString(seats);
    }

    public String getVehicleInfo() {
        return "Vehicle:"+'\n'+"Brand:"+brand+"Model:"+model+" Color:"+color+" Plate:"+plate+"Seats:"+seats;
    }//pasar to string al front



    public int getSeats(){
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}