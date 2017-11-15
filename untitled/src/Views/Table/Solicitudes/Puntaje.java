package Views.Table.Solicitudes;

import root.User.Rating;

public class Puntaje {
    private Rating rating;

    public Puntaje(Rating rating){
        this.rating = rating;
    }

    @Override
    public String toString(){
        return Double.toString(rating.calculatePercentage())+"%";
    }
}
