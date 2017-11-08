package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import root.Exceptions.DeniedDriverException;
import root.Ride.ActiveRideAdmin;
import root.Ride.Ride;
import root.Ride.Route;


import static java.lang.System.out;

public class MainPage extends Controller{

    @FXML private TableView<Ride> ridesTable;
    @FXML private TableColumn ruta;
    @FXML private TableColumn dia;
    @FXML private TableColumn conductor;
    @FXML private TableColumn permissions;

    //@FXML private TableColumn dia;
    @FXML private TableColumn fumar;
    @FXML private TableColumn comer;



    public MainPage(ClientStage stage){
        super(stage);
    }

    public void myProfile(ActionEvent event) {

        stage.MyProfile();
    }

    public void newRide(ActionEvent event) {
    	if(stage.getUser().getDriver()) {
    		stage.NewRide();
    	}
    	
        else {
        	
        	 Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("No cumple con las condiciones para ser Conductor");
             alert.setContentText(null);
             alert.showAndWait();
        }
    }

    public ObservableList<Ride> getRides(){
        ObservableList<Ride> rides=FXCollections.observableArrayList();
        //System.out.println(stage.getState().getCurrentRides().get(0).getRide());
        System.out.println(stage.getState().getCurrentRides().get(0).getRide());

        //for(int i=0;i<stage.getState().getCurrentRides().size();i++)
            rides.add(stage.getState().getCurrentRides().get(0).getRide());
        return rides;

    }

    public void init(){
        //conductor.setCellValueFactory(new PropertyValueFactory<>("driver"));

        ruta.setCellValueFactory(new PropertyValueFactory<>("route"));
        dia.setCellValueFactory(new PropertyValueFactory<>("date"));
        permissions.setCellValueFactory(new PropertyValueFactory<>("permissions"));
        conductor.setCellValueFactory(new PropertyValueFactory<>("driver"));




        //fumar.setCellValueFactory(new PropertyValueFactory<>("permissions"));
        ridesTable.setItems(getRides());

    }

}