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
    /*La variable freeSeats fue agregada para que en el cuadro del menú principal puedan
     * verse la cantidad de asientos disponibles. No pudimos encontrar otra forma de hacerlo.
     *  Sabemos que no es correcto que esté esta variable en esta clase, pero la pusimos para que 
     *  pueda verse y entenderse el funcionamiento del carPool desde el front.   
   */
    private int freeSeats;

    public Vehicle(String brand,String model,String color,int year,String plate,int seats ){
        this.brand=brand;
        this.model=model;
        this.color=color;
        this.year=year;
        this.plate=plate;
        this.seats=seats;
        this.freeSeats=seats;
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

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getPlate() {
        return plate;
    }

    public int getSeats(){
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
    /*La función updateFreeSeats fue agregada para que en el cuadro del menú principal puedan
     * verse la cantidad de asientos disponibles. No pudimos encontrar otra forma de hacerlo. 
   */
    public void updateFreeSeats(int freeSeats) {
    	this.freeSeats = freeSeats;
    }
    
    
    
    @Override
    public String toString(){
        /* La implementación original del presente toString() era la siguiente:
         return "Vehicle:"+'\n'+"Brand:"+brand+"Model:"+model+" Color:"+color+" Plate:"+plate+"Seats:"+seats;
        Sin embargo, hicimos que devuelva un string con la cantidad de asientos disponibles del vehículo 
        para que pueda verse pantalla.
        */
        return Integer.toString(freeSeats);
    }

}