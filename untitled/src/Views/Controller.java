package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class Controller {
    protected ClientStage stage;

    public Controller(ClientStage stage){
        this.stage = stage;
    }

}
