package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import root.State.State;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private static final State state = new State();
    private static final File file = new File("../PoolConFront/untitled/src/root/Data");

    @Override
    public void start(Stage primaryStage) throws Exception{
        multipleNewPool(state, "Pool", 2);
    }

    private void multipleNewPool(State state, String title, int quantity){
        for(int i =0; i<quantity; i++)
            newPool(state, title + (i+1));
    }

    public void newPool(State state, String title){
        ClientStage stage = new ClientStage(state, title);
        stage.setOnCloseRequest(e-> {
            try {
                state.saveData(file);
            } catch (IOException ee){
                System.out.println("Not able to save changes");// Hacer un warning que diga eso
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
