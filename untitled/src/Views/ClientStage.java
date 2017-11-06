package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import root.User.User;
import root.State.State;

import java.io.IOException;

public class ClientStage extends Stage {
    private User user;
    private State state;

    public ClientStage(State state){
        super();
        this.state = state;
        this.setTitle("Pool");
        setMainView();
        this.show();
    }

    public void setMainView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Controller mainView = new MainPage(this);
        loader.setController(mainView);
        try {
            this.setScene(new Scene(loader.load(), 500, 500));
        } catch (IOException e) {
            //No encontro el FXML
        }
        //this.show();
    }

   /* public void setOtherView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller otherView = new OtherView(this);
        loader.setController(otherView);
        try {
            this.setScene(new Scene(loader.load(), 200, 200));
        } catch (IOException e) {
            //No encontro el FXML
        }
    }*/
}
