package root.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Rating implements Serializable{

    private static final long serialVersionUID = 1L;

    private int positive;
    private int negative;

    public Rating(){
        this.positive=0;
        this.negative=0;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(positive);
        out.writeInt(negative);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        positive = ois.readInt();
        negative = ois.readInt();
    }

    public void modifyRating(boolean isGood){
        if(isGood)
            positive++;
        else
            negative++;
    }

    public double calculatePercentage(){
        if(positive+negative == 0) return 50;
        return positive/(positive+negative)*100;
    }
    
   
    /*  No pudimos encontrar qu� parte del c�digo realizado en javafx
     * causa que la siguiente implementaci�n del toString() no permita que se carguen los
     * pasajeros en la ventana acceptRequests. Por eso, decidimios comentarlo para que el
     *  programa pueda ser probado. Aclararmos, entonces, que en la columna de rating no 
     *  aparecer� ning�n valor. 
    @Override
    public String toString() {
    	String s = String.valueOf(calculatePercentage()) + "%";
    	return s;
    }
    */
 
    

}
