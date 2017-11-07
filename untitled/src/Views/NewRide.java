package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewRide extends Controller {

    public NewRide(ClientStage stage){
        super(stage);
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

    public void init(){}
}
