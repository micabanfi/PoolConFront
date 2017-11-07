package Views;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import root.Ride.Ride;

import java.awt.*;

import static java.lang.System.out;

public class MainPage extends Controller{

    @FXML private TableView<Ride> tableRides;
    @FXML private TableColumn desde;
    @FXML private TableColumn hasta;
    @FXML private TableColumn ruta;
    //@FXML private TableColumn dia;
    @FXML private TableColumn fumar;
    @FXML private TableColumn comer;
    ObservableList<Ride> viajes;

    private int ridePositionTable;

    public MainPage(ClientStage stage){
        super(stage);
    }

    public void myProfile(ActionEvent event) {

        stage.MyProfile();
    }

    public void newRide(ActionEvent event) {
        stage.NewRide();
    }

    public void init(){}

}