package root.Ride;

import root.User.Preferences;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Permissions implements Serializable{

    private static final long serialVersionUID = 1L;

    private boolean smoke;
    private boolean eat;

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeBoolean(smoke);
        out.writeBoolean(eat);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        smoke = ois.readBoolean();
        eat = ois.readBoolean();
    }

    public Permissions(boolean smoke, boolean eat){
        this.smoke = smoke;
        this.eat = eat;
    }

    public double compatibility(Preferences preferences){
        double aux = 0;
        if(preferences.isSmoke()){
            aux += 1;
        };
        if(preferences.isSmoke()){
            aux += 1;
        }
        aux = aux/2;
        return aux;
    }

    @Override
    public String toString() {
        return "Smoke: "+smoke+"\nComer: "+eat;
    }
}
