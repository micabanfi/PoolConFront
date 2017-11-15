package Views;

import Views.Table.Main.MainTable;
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
import root.User.Vehicle;


import java.util.List;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

public class MainPage extends Controller{

    @FXML private TableView<MainTable> ridesTable;
    @FXML private TableColumn cRoute;
    @FXML private TableColumn cDay;
    @FXML private TableColumn cDriver;
    @FXML private TableColumn cPermissions;
    @FXML private TableColumn cSeats;
    @FXML private TableColumn cCompatibility;


    private List<ActiveRideAdmin> rides=stage.getActiveRideAdmins();

    private static final File file = new File("../PoolConFront/untitled/src/root/Data");

    public MainPage(ClientStage stage){
        super(stage);
    }

    public void myProfile(ActionEvent event) {

        stage.MyProfile();

    }
    
    public void newRide(ActionEvent event) {
    	    if(!stage.getUser().getVehicles().isEmpty()) {
                stage.NewRide();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No cumple con las condiciones para ser Conductor");
                alert.setContentText(null);
                alert.showAndWait();
            }
    }

    public ObservableList<MainTable> getRides(){
        ObservableList<MainTable> rides=FXCollections.observableArrayList();
        for(ActiveRideAdmin rideAdmin : stage.getActiveRideAdmins()){
            rides.add(new MainTable(rideAdmin, stage.getUser()));
        }

        return rides;
    }
 
    

    public void init(){

            cRoute.setCellValueFactory(new PropertyValueFactory<>("ruta"));
            cDay.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cCompatibility.setCellValueFactory(new PropertyValueFactory<>("compatibilidad"));
            cPermissions.setCellValueFactory(new PropertyValueFactory<>("sePermite"));
           cDriver.setCellValueFactory(new PropertyValueFactory<>("conductor"));
           cSeats.setCellValueFactory(new PropertyValueFactory<>("asientos"));



            ridesTable.setItems(getRides());

        
        /*No pudimos hacer que en la columna compatibility aparezca la compatibilidad del
         * usuario loggeado con cada viaje. La idea era que, desde ac�, se carguen en un
         * ObservableList los resultados para luego ser subidos a ridesTable. La invocaci�n ser�a la siguiente:
         	ObservableList<Double> compat=FXCollections.observableArrayList();
        	for(int i=0;i<stage.getActiveRideAdmins().size();i++)
            compat.add(stage.getActiveRideAdmins().get(i).compatibility(stage.getUser().getPreferences()));
         * */
        
    }

    public void removeRide(){
        ActiveRideAdmin removeRide=ridesTable.getSelectionModel().getSelectedItem().getActiveRideAdmin();
        if(removeRide==null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
	           alert.setTitle("Error");
	           alert.setHeaderText("Debe seleccionar el viaje que quiere eliminar");
	           alert.setContentText(null);
	           alert.showAndWait();
        }
        else {
	        try {
	            stage.removeRide(removeRide);
	
	        } catch(NoPermission e) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Solamente el conductor puede eliminar el viaje");
	            alert.setContentText(null);
	            alert.showAndWait();
	        }
	        ridesTable.setItems(getRides());
	    }
    }

    private ActiveRideAdmin getActiveRideAdmin(Ride ride){
        for(ActiveRideAdmin rideAdmin:rides){
            if(rideAdmin.getRide().equals(ride))
                return rideAdmin;
        }
        return null;
    }


    public void joinRide() throws SeatsTaken {
        ActiveRideAdmin rideAdmin = ridesTable.getSelectionModel().getSelectedItem().getActiveRideAdmin();
       if (rideAdmin == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Debe seleccionar un viaje al cual unirse");
            alert.setContentText(null);
            alert.showAndWait();
        } else {

            try {
                stage.addRequest(rideAdmin);
            } catch (AlreadyRequested e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Solicitud env�ada");
                alert.setContentText(null);
                alert.showAndWait();
            } catch (AlreadyInRide e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ya se encuentra anotado en este viaje");
                alert.setContentText(null);
                alert.showAndWait();
            } catch (SeatsTaken e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No hay asientos disponibles");
                alert.setContentText(null);
                alert.showAndWait();
            }
        }
    }
}