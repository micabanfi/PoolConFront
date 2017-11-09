package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.User.Credential;
import root.User.Gender;

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
        MyProfileNametx.setText(stage.getUser().getName());
        MyProfileSurnametx.setText(stage.getUser().getSurname());
        MyProfileCareertx.setText(stage.getUser().getPreferences().getCareer());
        MyProfilePhonetx.setText(stage.getUser().getPhone());
        String genderaux;
        switch(stage.getUser().getGender()){
            case MALE:
                genderaux = "Masculino";
                break;
            case FEMALE:
                genderaux = "Femenino";
                break;
            default:
                genderaux = "Prefiero no aclarar";
        }
        MyProfileGenretx.setText(genderaux);
        MyProfileUsernametx.setText(stage.getUser().getCredential().getUserName());
        MyProfilePasswordtx.setText(stage.getUser().getCredential().getPassword());

    }

    public void mainPage(ActionEvent event) {
        stage.MainPage();
    }

    public void editMyProfile(){

        MyProfileNametx.setEditable(true);
        MyProfileSurnametx.setEditable(true);
        MyProfileCareertx.setEditable(true);
        MyProfilePhonetx.setEditable(true);
        MyProfileGenretx.setEditable(true);
        MyProfileUsernametx.setEditable(true);
        MyProfilePasswordtx.setEditable(true);

    }

    public void saveChanges(){
        stage.getUser().setName(MyProfileNametx.getText());
        stage.getUser().setSurname(MyProfileSurnametx.getText());
        //stage.getUser().setPreferences().
        stage.getUser().setPhone(MyProfilePhonetx.getText());
        stage.getUser().setCredential(new Credential(MyProfileUsernametx.getText(),MyProfilePasswordtx.getText()));


    }
}
