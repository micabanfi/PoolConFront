package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import root.Exceptions.InvalidCredentials;
import root.Exceptions.InvalidFields;
import root.User.Credential;
import root.User.User;
import root.State.State;

import javax.security.auth.login.LoginContext;
import java.io.IOException;

public class ClientStage extends Stage {
    private User user;
    private State state;

    public ClientStage(State state, String title){
        super();
        this.state = state;
        this.setTitle(title);
        LogIn();
        this.show();
    }

    //para probar
    public User getUser(){
        return user;
    }

    public void register(User user) throws InvalidFields{
        state.register(user);
        this.user = user;
    }

    public void login(Credential cred) throws InvalidCredentials{
        User aux=state.login(cred);
        this.user = aux;
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

}
