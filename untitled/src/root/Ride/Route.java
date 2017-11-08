package root.Ride;

public class Route {
    private String from;
    private String to;
    private String through;

    public Route(String from,String to,String through){
        this.from=from;
        this.to=to;
        this.through=through;
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
