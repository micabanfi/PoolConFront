package Views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.Exceptions.ExistingRideException;
import root.Exceptions.InvalidCredentials;
import root.Exceptions.InvalidFields;
import root.Ride.Permissions;
import root.Ride.Ride;
import root.Ride.Route;
import root.State.State;
import root.User.Credential;
import root.User.Person;
import root.User.User;
import root.User.Vehicle;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class Main extends Application implements Initializable{

    public State estado= State.getInstance();
    //public User currentUser;
    public int userNumber;

    //Funci�n que crea la ventana principal
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            primaryStage.setTitle("Carpool");
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();

        } catch (Exception e) {
            out.println("Cant load window Start");
        }

    }

    public void mainPage(ActionEvent event) {
        try{

            Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("MainPage");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load mainPage");
        }
    }


    //verdadera main
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //TODO
    }



}
