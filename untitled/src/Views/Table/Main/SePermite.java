package Views.Table.Main;

import root.Ride.Permissions;

public class SePermite {
    private Permissions permissions;

    public SePermite(Permissions permissions){
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Smoke: "+permissions.getSmoke()+"\nComer: "+permissions.getEat();
    }
}
