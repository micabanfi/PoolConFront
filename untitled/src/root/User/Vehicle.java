package root.User;

public class Vehicle {
    private String brand;
    private String model;
    private String color;
    private int year;
    private String plate;
    private int seats;

    //  private Permissions permission;
    public Vehicle(String brand,String model,String color,int year,String plate,int seat,boolean smoke,boolean food ){
        this.brand=brand;
        this.model=model;
        this.color=color;
        this.year=year;
        this.plate=plate;
        this.seats=seats;
//		this.permission = new Permissions(smoke, food, drink);
    }

    @Override
    public String toString(){
        return "Vehicle:"+'\n'+"Brand:"+brand+"Model:"+model+" Color:"+color+" Plate:"+plate;
    }

    public int getSeats(){
        return seats;
    }
}