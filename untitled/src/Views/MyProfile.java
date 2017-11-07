package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.annotation.Resources;
import javax.print.DocFlavor;

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

    public void init(){
        setProfileInfo();
    }

    public void setProfileInfo(){
        MyProfileNametx.setText(stage.getUser().getPerson().getName());
        MyProfileSurnametx.setText(stage.getUser().getPerson().getSurname());
        MyProfileCareertx.setText(stage.getUser().getPerson().getPreferences().getCareer());
        MyProfilePhonetx.setText(stage.getUser().getPerson().getPhone());
        MyProfileGenretx.setText(stage.getUser().getPerson().getGender());
        MyProfileUsernametx.setText(stage.getUser().getCredential().getUserName());
        MyProfilePasswordtx.setText(stage.getUser().getCredential().getPassword());

    }

    public void mainPage(ActionEvent event) {
        stage.MainPage();
    }
}
