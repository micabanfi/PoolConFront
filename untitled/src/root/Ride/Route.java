package root.Ride;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Route implements Serializable{

    private static final long serialVersionUID = 1L;

    private String from;
    private String to;
    private String through;

    public Route(String from,String to,String through){
        this.from=from;
        this.to=to;
        this.through=through;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(from);
        out.writeUTF(to);
        out.writeUTF(through);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        from = ois.readUTF();
        to = ois.readUTF();
        through = ois.readUTF();
    }


    public String getFrom() {
        return from;
    }

    public String getThrough() {
        return through;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to){
        this.to=to;
    }

    public void setThrough(String through) {
        this.through = through;
    }

    @Override
    public String toString(){
        return "Desde:"+from+"\nHasta:"+to+"\nRuta:"+through;
    }

}