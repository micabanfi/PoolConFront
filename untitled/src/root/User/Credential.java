package root.User;

import root.Ride.Ride;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Credential implements Serializable{

    private static final long serialVersionUID = 1L;

    private String password;
    private String username;

    public Credential(String username,String password){
        this.password=password;
        this.username=username;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(password);
        out.writeUTF(username);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        password = ois.readUTF();
        username = ois.readUTF();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Credential))
            return false;
        Credential aux = (Credential) obj;

        return this.username.equals(aux.username) && this.password.equals(aux.password);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + password.hashCode();
        result = 31*result + username.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "Nombre de Usuario="+username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
