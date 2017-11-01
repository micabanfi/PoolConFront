package root;

public class ExistingRideException extends Exception{
    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "Ride Already Exists";

    public ExistingRideException(){
        super(MESSAGE);
    }
}