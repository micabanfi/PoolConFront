package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MyProfile extends Controller {

    @FXML private TextField MyProfileNametx;
    @FXML private TextField MyProfileSurnametx;
    @FXML private TextField MyProfileCareertx;
    @FXML private TextField MyProfilePhonetx;
    @FXML private TextField MyProfileGenretx;
    @FXML private TextField MyProfileUsernametx;
    @FXML private TextField MyProfilePasswordtx;

    public MyProfile(ClientStage stage){
        super(stage);
    }

    public void setProfileInfo(){
        System.out.println("Nombre:"+stage.getUser().getPerson().getName());

        MyProfileNametx.setText(stage.getUser().getPerson().getName());

    }

    public void mainPage(ActionEvent event) {
        stage.MainPage();
    }
}
