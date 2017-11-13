package Views;

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
    @FXML private TableColumn raiting;

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
        //stage.getState().getActiveRideAdminOfRide().getRequests();
        //stage.getState().get
        //for(int i=0;i<stage.getState().getRequest();i++)
      
        return users;
    }

    @Override
    public void init(){
        persona.setCellValueFactory(new PropertyValueFactory<>("name"));
        //apellido.setCellValueFactory(new PropertyValueFactory<>("surname"));
        //telefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
        requestUsers.setItems(getUser());
    }

    public void btAcceptUser() throws NoPermission, RideDoesNotExist, NotRequested, InvalidCredentials {
        /*ObservableList<User> allUsers,acceptUser;
        allUsers=requestUsers.getItems();
        acceptUser=requestUsers.getSelectionModel().getSelectedItems();*/

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
	        	//System.out.println("Passengers before");
	        	//System.out.println(rideAdmin.getPassengers());
	        	//System.out.println("passengers despes de q fueron agregados");
				rideAdmin.acceptRequest(stage.getUser(), user);
				requestUsers.setItems(getUser());
				//System.out.println(rideAdmin.getPassengers());
			} catch (SeatsTaken e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
		           alert.setTitle("Error");
		           alert.setHeaderText("No hay lugar disponible");
		           alert.setContentText(null);
		           alert.showAndWait();
			}
        }
        //ActiveRideAdmin aux = new ActiveRideAdmin(removeRide);
        //        Credential credential1 = new Credential("mica", "1234");
   //      User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);

        //allUsers.removeAll(acceptUser);
        //stage.getState().acceptRideRequest(user.getCredential(),stage.getState().getCurrentRides().get(0).getRide(),stage.getUser(),user1);

    }
    
    public void btDenyUser() {
    	/*ObservableList<User> allUsers,acceptUser;
        allUsers=requestUsers.getItems();
        acceptUser=requestUsers.getSelectionModel().getSelectedItems();*/
        User user=requestUsers.getSelectionModel().getSelectedItem();
        if(user==null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
	           alert.setTitle("Error");
	           alert.setHeaderText("Debe seleccionar a un pasajero");
	           alert.setContentText(null);
	           alert.showAndWait();
        }
        else {
	       //en este punto estas excepciones creo que nunca van a saltar. No deberiamos
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

