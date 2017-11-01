package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import root.User.Credential;
import root.User.Person;
import root.User.User;
import root.User.Vehicle;

import java.awt.*;

public class Main extends Application {

        public static void main(String[] args){
            //creo vehiculo.
            //hay que chequar patente?
            Vehicle vehicle1=new Vehicle("Fiat","500","Blanco",2015,"ABC123",4,false,true);

            //creo persona
            Person person1=new Person("Micaela","Banfi","Informatica","1234567890",false,true,"Femenino");

            //creo la credential
            Credential credential1=new Credential("mica","1234");

            //le agrego vehicle1 a person1
            //person1.addVehicle(vehicle1);

            //creo el user
            User user1=new User(credential1,person1);

            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Carpool");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

    }
    public void btLogIn(ActionEvent event) {
        try{
            Platform.exit();
            Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage=new Stage();
            stage.setTitle("MainPage");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();

        }catch (Exception e){
            System.out.println("Cant load window");
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
            System.out.println("Cant load window");
        }
    }


    public void mainPage(ActionEvent event) {
        try{
            Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage=new Stage();
            stage.setTitle("MainPage");
            stage.setScene(new Scene(root2,1000,600));
            stage.show();
        }catch (Exception e){
            System.out.println("Cant load window");
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
            System.out.println("Cant load window");
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
            System.out.println("Cant load window");
        }
    }

}
