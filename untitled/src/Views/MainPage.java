package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import static java.lang.System.out;

public class MainPage {

    public void myProfile(ActionEvent event) {
        try{


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
