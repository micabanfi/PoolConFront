package root.User;

public class Vehicle {
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

    @Override
    public String toString(){
        //return "Vehicle:"+'\n'+"Brand:"+brand+"Model:"+model+" Color:"+color+" Plate:"+plate+"Seats:"+seats;
        System.out.println(seats);
        return Integer.toString(seats);
    }

    public int getSeats(){
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}