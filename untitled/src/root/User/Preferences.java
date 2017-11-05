
package root.User;

public class Preferences {
    private String career;
    private boolean smoke;
    private boolean food;

    public Preferences( String career,boolean smoke, boolean food){
        this.career=career;
        this.smoke=smoke;
        this.food=food;
    }

    @Override
    public String toString() {
        String smokeString ="";
        String foodString = "";
        if(smoke) {
            smokeString = "SI";
        }
        else {
            smokeString = "NO";
        }
        if(food) {
            foodString = "SI";
        }
        else {
            foodString = "NO";
        }
        String s = "Preferencias del usuario:\nCARRERA: "+career+"\nFUMAR: "+smokeString+"\nCOMIDA: "+ foodString+"\n";
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Preferences))
            return false;
        Preferences aux = (Preferences) obj;
        return smoke == aux.smoke && food == aux.food && career.equals(aux.career);
    }
}