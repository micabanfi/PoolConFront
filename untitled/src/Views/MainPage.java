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
import root.Exceptions.AlreadyInRide;
import root.Exceptions.AlreadyRequested;
import root.Exceptions.DeniedDriverException;
import root.Exceptions.NoPermission;
import root.Exceptions.SeatsTaken;
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
    @FXML private TableColumn asientos;

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

        for(int i=0;i<stage.getState().getCurrentRides().size();i++)
            rides.add(stage.getState().getCurrentRides().get(i).getRide());
        return rides;

    }

    public void init(){
        ruta.setCellValueFactory(new PropertyValueFactory<>("route"));
        dia.setCellValueFactory(new PropertyValueFactory<>("date"));
        permissions.setCellValueFactory(new PropertyValueFactory<>("permissions"));
        conductor.setCellValueFactory(new PropertyValueFactory<>("driver"));
        asientos.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        ridesTable.setItems(getRides());

    }

    public void removeRide(){
        ObservableList<Ride> rideSelected,allRides;
        allRides=ridesTable.getItems();
        rideSelected=ridesTable.getSelectionModel().getSelectedItems();

        Ride removeRide=ridesTable.getSelectionModel().getSelectedItem();
        
        try {
        	stage.getState().deleteRide(removeRide,stage.getUser());
        	allRides.removeAll(rideSelected);
        } catch(NoPermission e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Solamente el conductor puede eliminar el viaje");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    public void joinRide() throws SeatsTaken {
        ObservableList<Ride> rideSelected,allRides;
        allRides=ridesTable.getItems();
        rideSelected=ridesTable.getSelectionModel().getSelectedItems();

        Ride ride=ridesTable.getSelectionModel().getSelectedItem();
        ActiveRideAdmin aux=new ActiveRideAdmin(ride);

       try {
           aux.addRequest(stage.getUser());

       } catch (AlreadyRequested alreadyRequested) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Ya pidio estar en este viaje");
           alert.setContentText(null);
           alert.showAndWait();
       } catch (AlreadyInRide alreadyInRide) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Ya se encuentra anotado en este viaje");
           alert.setContentText(null);
           alert.showAndWait();
       }


    }



}