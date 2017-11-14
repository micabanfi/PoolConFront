package Views;

/*BORRADOR MAITE
rating.setCellValueFactory(new PropertyValueFactory<>(value -> {
	for(int i=0; stage.getState().getUsers().size(); i++) {
		if()
	}
});*/

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

    @FXML private TableView<User> requestUsers;
    @FXML private TableColumn persona;
    @FXML private TableColumn apellido;
    @FXML private TableColumn telefono;
    @FXML private TableColumn rating;

    private ActiveRideAdmin rideAdmin;

    public AcceptRequest(ClientStage stage, ActiveRideAdmin rideAdmin){
        super(stage);
        this.rideAdmin = rideAdmin;
    }

    public void mainPage(ActionEvent event){
        stage.MainPage();
    }

    public ObservableList<User> getUser(){
        ObservableList<User> users= FXCollections.observableArrayList();
        List<User> requests = rideAdmin.getRequests();
        for (int i = 0; i < rideAdmin.getRequests().size(); i++) {
        	users.add(requests.get(i));
        }
        return users;
    }

    @Override
    public void init(){
        persona.setCellValueFactory(new PropertyValueFactory<>("name"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("surname"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
        requestUsers.setItems(getUser());
        /*No pudimos hacer que en la columna rating aparezca el rating del usurio.
         *  La idea era que, desde acá, se carguen en ObservableList los resultados para 
         *  luego ser subidos en a la tabla de request Users. La invocación sería la siguiente:
         	ObservableList<Double> rating =FXCollections.observableArrayList();
        	for(int i=0;i<stage.getActiveRideAdmins().size();i++)
            rating.add(stage.getUser().getRating());
         * */
        
    }

    public void btAcceptUser() throws NoPermission, RideDoesNotExist, NotRequested, InvalidCredentials {
        User user=requestUsers.getSelectionModel().getSelectedItem();
        if(user==null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
	           alert.setTitle("Error");
	           alert.setHeaderText("Debe seleccionar a un pasajero");
	           alert.setContentText(null);
	           alert.showAndWait();
        }
        else {
	        
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
        User user=requestUsers.getSelectionModel().getSelectedItem();
        if(user==null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
	           alert.setTitle("Error");
	           alert.setHeaderText("Debe seleccionar a un pasajero");
	           alert.setContentText(null);
	           alert.showAndWait();
        }
        else {
	       //en este punto estas excepciones  que nunca van a saltar. No deberiamos
        	//de sacarlas?
				try {
					rideAdmin.declineRequest(stage.getUser(), user);
				} catch (NoPermission e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotRequested e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			requestUsers.setItems(getUser());
        }
 
}

