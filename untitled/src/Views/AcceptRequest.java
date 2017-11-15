package Views;

/*BORRADOR MAITE
rating.setCellValueFactory(new PropertyValueFactory<>(value -> {
	for(int i=0; stage.getState().getUsers().size(); i++) {
		if()
	}
});*/

import Views.Table.Solicitudes.Solicitudes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import root.Exceptions.InvalidCredentials;
import root.Exceptions.NoPermission;
import root.Exceptions.NotRequested;
import root.Exceptions.RideDoesNotExist;
import root.Exceptions.SeatsTaken;
import root.Ride.ActiveRideAdmin;
import root.Ride.Ride;
import root.User.Credential;
import root.User.Gender;
import root.User.Person;
import root.User.User;
import javafx.event.ActionEvent;

import java.util.List;

import javax.jws.soap.SOAPBinding;


public class AcceptRequest extends Controller {

    @FXML private TableView<Solicitudes> requestUsers;
    @FXML private TableColumn persona;
    @FXML private TableColumn apellido;
    @FXML private TableColumn telefono;
    @FXML private TableColumn puntaje;

    private ActiveRideAdmin rideAdmin;

    public AcceptRequest(ClientStage stage, ActiveRideAdmin rideAdmin){
        super(stage);
        this.rideAdmin = rideAdmin;
    }

    public void mainPage(ActionEvent event){
        stage.MainPage();
    }

    public ObservableList<Solicitudes> getUser(){
        ObservableList<Solicitudes> users= FXCollections.observableArrayList();
        List<User> requests = rideAdmin.getRequests();
        for (User user: requests) {
        	users.add(new Solicitudes(user));
        }
        return users;
    }

    @Override
    public void init(){
        persona.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        //puntaje.setCellValueFactory(new PropertyValueFactory<>("puntaje"));
        requestUsers.setItems(getUser());
        /*No pudimos hacer que en la columna rating aparezca el rating del usurio.
         *  La idea era que, desde ac�, se carguen en ObservableList los resultados para 
         *  luego ser subidos en a la tabla de request Users. La invocaci�n ser�a la siguiente:
         	ObservableList<Double> rating =FXCollections.observableArrayList();
        	for(int i=0;i<stage.getActiveRideAdmins().size();i++)
            rating.add(stage.getUser().getRating());
         * */
        
    }

    public void btAcceptUser() throws NoPermission, RideDoesNotExist, NotRequested, InvalidCredentials {
        Solicitudes solicitudes=requestUsers.getSelectionModel().getSelectedItem();
        if(solicitudes==null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
	           alert.setTitle("Error");
	           alert.setHeaderText("Debe seleccionar a un pasajero");
	           alert.setContentText(null);
	           alert.showAndWait();
        }
        else {
	        User user = solicitudes.getUser();
	        try {
				rideAdmin.acceptRequest(stage.getUser(), user);
				requestUsers.setItems(getUser());				
			} catch (SeatsTaken e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
		           alert.setTitle("Error");
		           alert.setHeaderText("No hay lugar disponible");
		           alert.setContentText(null);
		           alert.showAndWait();
			}
        }
    

    }
    
    public void btDenyUser() {
        Solicitudes solicitudes = requestUsers.getSelectionModel().getSelectedItem();
        if (solicitudes == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Debe seleccionar a un pasajero");
            alert.setContentText(null);
            alert.showAndWait();
        } else {
            User user = solicitudes.getUser();
            requestUsers.setItems(getUser());
        }
    }
 
}

