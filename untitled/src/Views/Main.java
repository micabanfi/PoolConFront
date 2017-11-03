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

    //Declaro todos los lugares donde se pueden completar informacion





    //Funci�n que crea Users para que cuando se inicialize el programa, ya hallan Users cargados.
    public void Inicio() throws InvalidFields {
        //Creo Users para que cuando inicialize el programa, ya hallan Users cargados.
        //Hay que chequar patente?
        Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 4, false, true);
        LocalDate bDayMica = LocalDate.of(2000, 1, 2);
        LocalDate bDayMaite = LocalDate.of(2000, 6, 30);
        Person person1 = new Person("Micaela", "Banfi", "Informatica", "1234567890", false, true, "Femenino", bDayMica);
        Person person2=new Person("Maite","Herran","Infor","11112222",false,false,"" + "Femenino", bDayMaite);
        Credential credential1 = new Credential("mica", "1234");
        Credential credential2=new Credential("maimai","maite1234");
        //Le agrego vehicle1 a person1
        //person1.addVehicle(vehicle1);
        User user1 = new User(credential1, person1);
        User user2=new User(credential2,person2);

        //Creo rides
        Ride ride1=new Ride(new Route("Victoria","Itba","Libertador"),vehicle1,person1,new Permissions(false,true,true),new Date(2017,10,11));

        //Agregamos los users al objeto estado que maneja el carPooling
        try {
            estado.register(user1);
            estado.register(user2);
            estado.AddRideToList(ride1);
            //Imprimo los usuarios que cree
            System.out.println(estado.toString());
        } catch (InvalidFields e) {
            //Este no deberia ir en possibleErrors porque lohacemos nosotros,no deberiamos ser tan tontos ;)
            System.out.println("Usuario ya existente");
        } catch (ExistingRideException e) {
            System.out.println("No se pudo crear el ride");//este tampoco en error
        }

    }

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

        try {
            Inicio();
        } catch (InvalidFields invalidFields) {
            invalidFields.printStackTrace();
        }
        //TODO
    }



}
