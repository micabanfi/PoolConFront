package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.Exceptions.InvalidCredentials;
import root.State.State;
import root.User.Credential;
import root.User.Person;

public class LogIn extends Controller{

    @FXML
    private TextField LogInUserNameTx;

    @FXML
    private TextField LogInPassTx;

    public LogIn(ClientStage stage){
        super(stage);
    }


    public void btLogIn(ActionEvent event) {
        String userNameTx, passwordTx;
        userNameTx = LogInUserNameTx.getText();
        passwordTx = LogInPassTx.getText();
        Credential credential = new Credential(userNameTx, passwordTx);

        try {
            stage.login(credential);
           stage.MainPage();
        } catch (InvalidCredentials e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Usuario o contrasena no encontrados");
            alert.setContentText(null);
            alert.showAndWait();
        } catch (Exception e) {
            System.out.println("Cant load window btLogIn");
        }
    }

    public void btRegister(ActionEvent event) {
        stage.Register();
    }

    public void init(){}
}

