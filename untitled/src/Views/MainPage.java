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
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Mi Perfil");
            stage.setScene(new Scene(root2, 1000, 600));
            stage.show();

        } catch (Exception e) {
            out.println("Cant load window myProfile");
        }
    }

    public void newRide(ActionEvent event) {
        try {
            Parent root3 = FXMLLoader.load(getClass().getResource("NewRide.fxml"));
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Nuevo Viaje");
            stage.setScene(new Scene(root3, 1000, 600));
            stage.show();

        } catch (Exception e) {
            out.println("Cant load window newRide");
        }
    }

}