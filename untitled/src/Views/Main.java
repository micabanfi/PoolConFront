package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import root.State.State;

import java.io.IOException;

public class Main extends Application {

    private static final State state = new State();

    @Override
    public void start(Stage primaryStage) throws Exception{
        newPool(state, "Ventana1");
        newPool(state, "Ventana2");
    }

    public void newPool(State state, String title){
        ClientStage stage = new ClientStage(state);
        //try {
          //  Thread.sleep(5000);
        //}catch (Exception e){

//        }
        //stage.setOtherView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
