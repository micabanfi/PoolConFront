
package root.User;

public class Preferences {
    private String career;//WTF para q carajo
    private boolean smoke;
    private boolean food;

    public Preferences(String career, boolean smoke, boolean food){
        this.career = career;
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
        String s = "Preferencias del usuario:\nFUMAR: "+smokeString+"\nCOMIDA: "+ foodString;
        return s;
    }
}