package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.InvalidCredentials;
import root.State.State;
import root.User.Credential;
import root.User.Person;
import root.User.User;
import root.User.Vehicle;

import sample.*;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }

    State estado=new State();
    //declaro todos los TextFields
    @FXML private TextField LogInUserNameTx;
    @FXML private TextField LogInPassTx;
    @FXML private TextField nametx;
    @FXML private TextField surnametx;
    @FXML private TextField phonetx;
    @FXML private TextField careertx;
    //@FXML private TextField

    public static void main(String[] args) {
        launch(args);
    }
    //private PasswordField txPassword;

   /* @FXML  private Button btLogIn;

    @FXML private Button btRegister;

    @FXML private TextField txUserName;*/


    /*
    //creo vehiculo.
    //hay que chequar patente?
    Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 4, false, true);

    //creo persona
    Person person1 = new Person("Micaela", "Banfi", "Informatica", "1234567890", false, true, "Femenino");

    //creo la credential
    Credential credential1 = new Credential("mica", "1234");

    //le agrego vehicle1 a person1
    //person1.addVehicle(vehicle1);

    //creo el user
    User user1 = new User(credential1, person1);*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Credential creddential1=new Credential("mica","1234");
            Person persona1=new Person("mica","surname","career","phone",true,false,"fem");
            User user1=new User(creddential1,persona1);
            estado.register(user1);
            System.out.println("estado"+estado.toString());

            String userName,pass;
            userName=LogInUserNameTx.getText();
            pass=LogInPassTx.getText();
            Credential creddential2=new Credential(userName,pass);
            // try {
            estado.login(creddential2);
            System.out.println(estado.toString());
            //}catch (InvalidCredentials e) {
            //  System.out.println("Usuario no encontrado");
            //}
            //Platform.exit();
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Carpool");
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Cant load window Start");
        }

    }

    public void btLogIn(ActionEvent event) {
        try{


            Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage=new Stage();
            stage.setTitle("MainPage");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();


        }catch (Exception e){
            System.out.println("Cant load window btLogIn");
        }
    }

    public void btRegister(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root1=(Parent) fxmlLoader.load();
            Stage stage=new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root1,1000,600));
            stage.show();



        }catch (Exception e){
            System.out.println("Cant load window btRegister");
        }
    }


     public void mainPage(ActionEvent event) {
        try{
            /*String name,surname,career,phone;
            name=nametx.getText();
            surname=surnametx.getText();
            career=careertx.getText();
            phone=phonetx.getText();

            Credential creddential1=new Credential("mica","1234");
            Person persona1=new Person(name,surname,career,phone,true,false,"fem");
            System.out.println("pantalla"+persona1.toString());
            User usuario1=new User(creddential1,persona1);
            estado.register(usuario1);

            System.out.println("estado"+estado.toString());
            */
            Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage=new Stage();
            stage.setTitle("MainPage");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();


        }catch (Exception e){
            System.out.println("Cant load window mainPage");
        }
    }

    public void myProfile(ActionEvent event) {
        try{
            Parent root2 = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Mi Perfil");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();
        }catch (Exception e){
            System.out.println("Cant load window myProfile");
        }
    }

     public void newRide(ActionEvent event) {
        try{
            Parent root3 = FXMLLoader.load(getClass().getResource("NewRide.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Nuevo Viaje");
            stage.setScene(new Scene(root3,1000,600));
            stage.show();
        }catch (Exception e){
            System.out.println("Cant load window newRide");
        }
    }



}
