package sample;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.PasswordField;
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

import sample.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class Main extends Application implements Initializable{


    public State estado=new State();
    public User currentUser=null;

    //Declaro todos los lugares donde se pueden completar informacion
    @FXML private TextField LogInUserNameTx;
    @FXML private TextField LogInPassTx;

    @FXML private TextField RgNametx;
    @FXML private TextField RgSurnametx;
    @FXML private TextField RgPhonetx;
    @FXML private TextField RgCareertx;
    @FXML private TextField RgUserNametx;
    @FXML private TextField RgPasswordtx;

    @FXML private DatePicker RgBirthDate;

    @FXML private ChoiceBox RgGenrech;
    @FXML private ChoiceBox RgSmokech;
    @FXML private ChoiceBox RgEatch;

    @FXML private TextField MyProfileNametx;
    @FXML private TextField MyProfileSurnametx;
    @FXML private TextField MyProfileCareertx;
    @FXML private TextField MyProfilePhonetx;
    @FXML private TextField MyProfileGenretx;
    @FXML private TextField MyProfileUsernametx;
    @FXML private TextField MyProfilePasswordtx;

  //Función que crea Users para que cuando se inicialize el programa, ya hallan Users cargados.
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
    
    //Función que crea la ventana principal
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Carpool");
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();

        } catch (Exception e) {
            out.println("Cant load window Start");
        }

    }

    public void possibleErrors(int error) {
        if (error == 1) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Introduzca nombre de usuario y contrasena o registrese si no tiene usuario");
        	alert.setContentText(null);
        	alert.showAndWait();
        }
        else if(error==2){
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Usuario o contrasena no encontrados");
        	alert.setContentText(null);
        	alert.showAndWait();
        }
        else if(error==3) {
        	Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Complete todos los campos por favor");
    		alert.setContentText(null);
    		alert.showAndWait();
        }
        else if(error==4) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Usuario ya existente");
        	alert.setContentText(null);
        	alert.showAndWait();
        }
        else if(error==5) {
        	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Error");
	    	alert.setHeaderText("El viaje ya existe");
	    	alert.setContentText(null);
	    	alert.showAndWait();
        }
    }

    public void newUser(){
        //MAL! ESTE WHILE PASARLO AL BOTON Iniciar Sesion DENTRO DE REGISTER.
    	String name="---", surname="---", career="---", phone="---", username1="---", password1="---",genre="---";
        //eat y smoke se inicializan en false por si no ponen nada.
    	Boolean eat=false,smoke=false;
        LocalDate bDate =LocalDate.of(0, 0, 0);
        while(name=="---" || surname=="---"|| career=="---" || phone=="---"|| username1=="---"|| password1=="---"||genre=="---") {
	        name = RgNametx.getText();
	        surname = RgSurnametx.getText();
	        career = RgCareertx.getText();
	        phone = RgPhonetx.getText();
	        username1 = RgUserNametx.getText();
	        password1 = RgPasswordtx.getText();
	        genre=RgGenrech.getValue().toString();
	        bDate = RgBirthDate.getValue();
	        		
	        String rta=new String(RgEatch.getValue().toString());
	        if(rta.compareTo("Si")==0)
	            eat=true;
	        else eat=false;
	        rta=RgSmokech.getValue().toString();
	        if(rta.compareTo("Si")==0)
	            smoke=true;
	        else smoke=false;
         }

        Credential creddential1 = new Credential(username1, password1);
        Person persona1 = new Person(name, surname, career, phone, smoke, eat, genre, bDate);
        User usuario1 = new User(creddential1, persona1);
        try{
            estado.register(usuario1);
            currentUser=estado.getUser(creddential1);
            Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("MainPage");
            stage.setScene(new Scene(root2, 1000, 600));
            stage.show();
        } catch (InvalidFields e) {
            possibleErrors(4);
        } catch (Exception e) {
            System.out.println("Cant load mainPage");
        }
    }

    public void btLogIn(ActionEvent event) {
            String userNameTx,passwordTx;
            userNameTx=LogInUserNameTx.getText();
            passwordTx=LogInPassTx.getText();
            Credential credential=new Credential(userNameTx,passwordTx);

            //si usuario o contrasenia estan vacios tirar error de completar campos.
            //si ambos estan vacios y se aprieta iniciar secion tira error de completar campos
            //si alguno esta vacio(null)me tira la excepcion,ver como que eso no pase

            try{
                estado.login(credential);
                currentUser=estado.getUser(credential);
                Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                Stage stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
                stage.setTitle("MainPage");
                stage.setScene(new Scene(root2,1000,600));
                stage.show();
            }catch (InvalidCredentials e){
                possibleErrors(2);
            }
            catch (Exception e){
            System.out.println("Cant load window btLogIn");
            }
    }

    public void btRegister(ActionEvent event) {
        try{
            Parent root2 = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Register");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();

        }catch (Exception e){
            System.out.println("Cant load window Register");
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

    public void myProfile(ActionEvent event) {
        try{
            /*MyProfileNametx.setDisable(true);
            MyProfileSurnametx.setDisable(true);
            MyProfileCareertx.setDisable(true);
            MyProfileGenretx.setDisable(true);
            MyProfilePhonetx.setDisable(true);
            MyProfilePasswordtx.setDisable(true);
            MyProfileUsernametx.setDisable(true);*/

            MyProfileNametx.setText(currentUser.getPerson().getName());
            MyProfileSurnametx.setText(currentUser.getPerson().getSurname());

            Parent root2 = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
            Stage stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Mi Perfil");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();
            
        }catch (Exception e){
            out.println("Cant load window myProfile");
        }
    }

     public void newRide(ActionEvent event) {
        try{
            Parent root3 = FXMLLoader.load(getClass().getResource("NewRide.fxml"));
            Stage stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Nuevo Viaje");
            stage.setScene(new Scene(root3,1000,600));
            stage.show();
            
        }catch (Exception e){
            out.println("Cant load window newRide");
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
