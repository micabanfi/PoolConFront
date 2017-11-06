package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MyProfile {

    @FXML private TextField MyProfileNametx;
    @FXML private TextField MyProfileSurnametx;
    @FXML private TextField MyProfileCareertx;
    @FXML private TextField MyProfilePhonetx;
    @FXML private TextField MyProfileGenretx;
    @FXML private TextField MyProfileUsernametx;
    @FXML private TextField MyProfilePasswordtx;


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
}
