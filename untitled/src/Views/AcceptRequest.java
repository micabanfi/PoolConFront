package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import root.Exceptions.InvalidCredentials;
import root.Exceptions.NoPermission;
import root.Exceptions.NotRequested;
import root.Exceptions.RideDoesNotExist;
import root.Ride.Ride;
import root.User.Credential;
import root.User.Gender;
import root.User.Person;
import root.User.User;

import javax.jws.soap.SOAPBinding;

public class AcceptRequest extends Controller {

    @FXML private TableView<User> requestUsers;
    @FXML private TableColumn persona;
    @FXML private TableColumn apellido;
    @FXML private TableColumn telefono;
    @FXML private TableColumn raiting;

    public AcceptRequest(ClientStage stage){
        super(stage);
    }


    public ObservableList<User> getUser(){
        ObservableList<User> users= FXCollections.observableArrayList();

        Credential credential1 = new Credential("mica", "1234");
        User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);

        //for(int i=0;i<stage.getState().getRequest();i++)
        users.add(user1);
        return users;

    }

    @Override
    public void init(){
        persona.setCellValueFactory(new PropertyValueFactory<>("name"));
        //apellido.setCellValueFactory(new PropertyValueFactory<>("surname"));
        //telefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
        requestUsers.setItems(getUser());
    }

    public void acceptUser() throws NoPermission, RideDoesNotExist, NotRequested, InvalidCredentials {
        ObservableList<User> allUsers,acceptUser;
        allUsers=requestUsers.getItems();
        acceptUser=requestUsers.getSelectionModel().getSelectedItems();

        User user=requestUsers.getSelectionModel().getSelectedItem();

        //ActiveRideAdmin aux = new ActiveRideAdmin(removeRide);
        Credential credential1 = new Credential("mica", "1234");
        User user1 = new User(credential1, "Micaela", "Banfi", "Informatica", "1234567890", false, true, Gender.OTHER);

        //allUsers.removeAll(acceptUser);

        stage.getState().acceptRideRequest(user.getCredential(),stage.getState().getCurrentRides().get(0).getRide(),stage.getUser(),user1);

    }
}
