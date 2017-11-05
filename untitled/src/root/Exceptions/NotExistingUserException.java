package root.Exceptions;

public class NotExistingUserException extends Exception{
    private static final long serialVersionUID = 2L;
    private static final String MESSAGE = "User Does Not Exist";

    public NotExistingUserException(){
        super(MESSAGE);
    }
}
