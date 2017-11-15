package Views.Table.Main;

import root.User.User;

public class Conductor{

    private User driver;

    public Conductor(User driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return driver.getName();
    }
}
