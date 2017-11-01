package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class Controller implements Initializable{

    @FXML  private PasswordField txPassword;

    @FXML  private Button btLogIn;

    @FXML private Button btRegister;

    @FXML private TextField txUserName;
    @FXML private TextField nametx;
    @FXML private TextField surnametx;
    @FXML private TextField phonetx;
    @FXML private TextField careertx;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }
}
