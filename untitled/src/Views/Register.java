package Views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.Exceptions.InvalidFields;
import root.State.State;
import root.User.Credential;
import root.User.Person;
import root.User.User;

import java.time.LocalDate;

public class Register {


    @FXML private TextField RgNametx;
    @FXML private TextField RgSurnametx;
    @FXML private TextField RgPhonetx;
    @FXML private TextField RgCareertx;
    @FXML private TextField RgUserNametx;
    @FXML private TextField RgPasswordtx;

   // @FXML private DatePicker RgBirthDate;

    @FXML private ChoiceBox RgGenrech;
    @FXML private ChoiceBox RgSmokech;
    @FXML private ChoiceBox RgEatch;

    public State estado= State.getInstance();

    public void newUser(){
        String name=null, surname=null, career=null, phone=null, username1=null, password1=null,genre=null;
        Boolean eat=null,smoke=null, emptyFields=true;
        //LocalDate bDate=null;

        name = RgNametx.getText();
        surname = RgSurnametx.getText();
        career = RgCareertx.getText();
        phone = RgPhonetx.getText();
        username1 = RgUserNametx.getText();
        password1 = RgPasswordtx.getText();
        genre = RgGenrech.getValue().toString();
        //bDate = RgBirthDate.getValue();

        String rta = new String(RgEatch.getValue().toString());
        if (rta.compareTo("Si") == 0)
            eat = true;
        else eat = false;
        rta = RgSmokech.getValue().toString();
        if (rta.compareTo("Si") == 0)
            smoke = true;
        else smoke = false;

        Credential creddential1 = new Credential(username1, password1);
        Person persona1 = new Person(name, surname, career, phone, smoke, eat, genre);
        User usuario1 = new User(creddential1, persona1);

            try {
                if(name.isEmpty() || surname.isEmpty()|| career.isEmpty()|| phone.isEmpty()|| username1.isEmpty()|| password1.isEmpty()||genre.isEmpty()){

                }
                estado.register(usuario1);
                //currentUser=new User(credential1,persona1);
                Parent root2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                Stage stage = new Stage();
                stage.setTitle("MainPage");
                stage.setScene(new Scene(root2, 1000, 600));
                stage.show();
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Usuario o contrasena no encontrados");
                alert.setContentText(null);
                alert.showAndWait()
            } catch (InvalidFields e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Usuario ya existente");
                alert.setContentText(null);
                alert.showAndWait();
            } catch (Exception e) {
                System.out.println("Cant load mainPage");
            }
        }

    public void IsEmpty() throws IsEmptyException{

        RgNametx.getText().isEmpty() || RgSurnametx.getText().isEmpty()|| RgCareertx.getText().isEmpty()|| RgPhonetx.getText().isEmpty()|| RgUserNametx.getText().isEmpty()|| RgPasswordtx.getText().isEmpty())

    }

    public void possibleErrors(int error) {
        if (error == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Introduzca nombre de usuario y contrasena o registrese si no tiene usuario");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else if(error==2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Usuario o contrasena no encontrados");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else if(error==3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Complete todos los campos por favor");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else if(error==4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Usuario ya existente");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else if(error==5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El viaje ya existe");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }
}
