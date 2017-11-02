package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.InvalidCredentials;
import root.InvalidFields;
import root.State.State;
import root.User.Credential;
import root.User.Person;
import root.User.User;
import root.User.Vehicle;

import sample.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class Main extends Application implements Initializable{


    public State estado=new State();

    //declaro todos los lugares donde se pueden completar informacion
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

    public void Inicio() throws InvalidFields {
        //creo Users para inicializar el programa

        //creo vehiculo.
        //hay que chequar patente?
        Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 4, false, true);

        //creo persona agregar BIRTHDATE
        Person person1 = new Person("Micaela", "Banfi", "Informatica", "1234567890", false, true, "Femenino");
        Person person2=new Person("Maite","Herran","Infor","11112222",false,false,"" + "Femenino");

        //creo la credential
        Credential credential1 = new Credential("mica", "1234");
        Credential credential2=new Credential("maimai","maite1234");

        //le agrego vehicle1 a person1
        //person1.addVehicle(vehicle1);

        //creo el user
        User user1 = new User(credential1, person1);
        User user2=new User(credential2,person2);

        try {
            //agrego user a estado
            estado.register(user1);
            estado.register(user2);
            //imprimo los usuarios que cree
            System.out.println(estado.toString());
        } catch (InvalidFields e) {
            //este no deberia ir en possibleErrors porque lohacemos nosotros,no deberiamos ser tan tontos ;)
            out.println("Usuario ya existente");
        }
    }

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

    //hacer una fn que tire ventanas de errores si falta algun dato
    public void possibleErrors(int error) {
        if (error == 1) {
            //abrir una ventana que diga
            System.out.println("Introduzca nombre de usuario y contrasena o registrese si no tiene usuario");
        }
        else if(error==2){
            System.out.println("Usuario o contrasena no encontrados");
        }
        else if(error==3)
            System.out.println("Complete todos sus datos por favor!");
        else if(error==4)
            System.out.println("El usuario ya existe,cambie su nombre o contrasena");

    }

    public void newUser(){
        //faltaria un while para que siga tomando los campos hasta que todos esten completos
        //while(eat==null || genre==null||smoke==null...)nose que son si estan vacios, hay que ver.Los textFields son null si estan vacios
        //possibleErrors(3);
        String name, surname, career, phone, username1, password1,genre;
        Boolean eat,smoke;
        name = RgNametx.getText();
        surname = RgSurnametx.getText();
        career = RgCareertx.getText();
        phone = RgPhonetx.getText();
        username1 = RgUserNametx.getText();
        password1 = RgPasswordtx.getText();
        genre=RgGenrech.getValue().toString();
        //falta la birthdate que nose como se pasa a Date

        //no es muy lindo pero nose de que otra forma hacerlo
        String rta=new String(RgEatch.getValue().toString());
        if(rta.compareTo("Si")==0)
            eat=true;
        else eat=false;
        rta=RgSmokech.getValue().toString();
        if(rta.compareTo("Si")==0)
            smoke=true;
        else smoke=false;

        Credential creddential1 = new Credential(username1, password1);
        Person persona1 = new Person(name, surname, career, phone, smoke, eat, genre);
        User usuario1 = new User(creddential1, persona1);
        try{
            estado.register(usuario1);
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
            //si ambos estan vacios y se apreta iniciar secion tira error de completar campos

            try{
                estado.login(credential);
                Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                Stage stage=new Stage();
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
            Stage stage=new Stage();
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
             Stage stage = new Stage();
             stage.setTitle("MainPage");
             stage.setScene(new Scene(root2, 1000, 600));
             stage.show();
         } catch (Exception e) {
             System.out.println("Cant load mainPage");
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
            out.println("Cant load window myProfile");
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
