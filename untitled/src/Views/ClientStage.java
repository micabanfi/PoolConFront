package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import root.Exceptions.*;
import root.Ride.ActiveRideAdmin;
import root.Ride.Ride;
import root.User.Credential;
import root.User.User;
import root.State.State;

import javax.security.auth.login.LoginContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ClientStage extends Stage {
    private User user;
    private State state;
	private File file;

    public ClientStage(State state, String title){
        super();
        this.state = state;
        this.setTitle(title);
        LogIn();
        this.show();
    }

    public void register(User user) throws InvalidFields{
        this.user = state.register(user);
    }

    public void login(Credential cred) throws InvalidCredentials{
        this.user = state.login(cred);;
    }

    public void newRide(ActiveRideAdmin ride) throws ExistingRideException{
        state.addRideToList(ride);
    }

    public void removeRide(ActiveRideAdmin ride) throws NoPermission{

        if(!ride.getRide().getDriver().equals(user))
            throw new NoPermission();
        state.deleteRide(ride);
    }

    public void addRequest(ActiveRideAdmin rideAdmin) throws AlreadyRequested, AlreadyInRide, SeatsTaken {
    	rideAdmin.addRequest(user);
    }
    
    public void leaveRide(ActiveRideAdmin ride){
    	ride.leaveRide(user);
    }

    public void loadData(File file) throws IOException, ClassNotFoundException {
		state.loadData(file);
    }
    
    public void modifyUser(User user) throws InvalidFields{
        register(user);
        state.removeUser(this.user);
        this.user = user;
    }

    //Volver a llamar para hacer refresh
    public List<ActiveRideAdmin> getActiveRideAdmins(){
        return state.getCurrentRides();
    }

    private void setView(String fxml, Controller controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        try {
            this.setScene(new Scene(loader.load(), StageDimensions.WIDTH.size(), StageDimensions.HEIGHT.size()));
        } catch (IOException e) {
            System.out.println("Error al cargar el fxml: " + fxml);
        }
        controller.init();
    }

    public void LogIn(){
        setView("LogIn.fxml", new LogIn(this));
    }

    public void MainPage() {
        setView("MainPage.fxml", new MainPage(this));
        
    }

    public void MyProfile() {
        setView("MyProfile.fxml", new MyProfile(this));
    }

    public void NewRide(){
        setView("NewRide.fxml", new NewRide(this));
    }

    public void Register(){
        setView("Register.fxml", new Register(this));
    }

    public void AcceptRequest(ActiveRideAdmin rideAdmin){
        setView("AcceptRequest.fxml",new AcceptRequest(this, rideAdmin));
    }

    //Usar los metodos de ClientStage en lugar
    @Deprecated
    public State getState() {
    	return state;
    }

    public User getUser() {
    	return user;
    }

}
