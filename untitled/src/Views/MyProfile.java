package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import root.Exceptions.NoPermission;
import root.Exceptions.NotInRide;
import root.Ride.Ride;
import root.User.Credential;
import root.User.Gender;
import root.User.Preferences;

import javax.annotation.Resources;
import javax.print.DocFlavor;

public class MyProfile extends Controller {

    @FXML
    private TextField MyProfileNametx;
    @FXML
    private TextField MyProfileSurnametx;
    @FXML
    private TextField MyProfileCareertx;
    @FXML
    private TextField MyProfilePhonetx;
    @FXML
    private TextField MyProfileGenretx;
    @FXML
    private TextField MyProfileUsernametx;
    @FXML
    private TextField MyProfilePasswordtx;

    @FXML
    private TableView<Ride> ridesTable;
    @FXML
    private TableColumn ruta;
    @FXML
    private TableColumn fecha;


    public MyProfile(ClientStage stage) {

        super(stage);

    }

    public void init() {
        setProfileInfo();
        ruta.setCellValueFactory(new PropertyValueFactory<>("route"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("date"));
        ridesTable.setItems(getRides());

    }

    public void setProfileInfo() {
        MyProfileNametx.setText(stage.getUser().getName());
        MyProfileSurnametx.setText(stage.getUser().getSurname());
        MyProfileCareertx.setText(stage.getUser().getPreferences().getCareer());
        MyProfilePhonetx.setText(stage.getUser().getPhone());
        String genderaux;
        switch (stage.getUser().getGender()) {
            case MALE:
                genderaux = "Masculino";
                break;
            case FEMALE:
                genderaux = "Femenino";
                break;
            default:
                genderaux = "Prefiero no aclarar";
        }
        MyProfileGenretx.setText(genderaux);
        MyProfileUsernametx.setText(stage.getUser().getCredential().getUsername());
        MyProfilePasswordtx.setText(stage.getUser().getCredential().getPassword());
    }

    public void mainPage(ActionEvent event) {
        stage.MainPage();
    }

    public void acceptRequest(ActionEvent event) {
        stage.AcceptRequest();
    }

    public void editMyProfile() {

        MyProfileNametx.setEditable(true);
        MyProfileSurnametx.setEditable(true);
        MyProfileCareertx.setEditable(true);
        MyProfilePhonetx.setEditable(true);
        MyProfileUsernametx.setEditable(true);
        MyProfilePasswordtx.setEditable(true);
    }

    public void saveChanges() {
        stage.getUser().setName(MyProfileNametx.getText());
        stage.getUser().setSurname(MyProfileSurnametx.getText());
        stage.getUser().setPreferences(new Preferences(MyProfileCareertx.getText(), stage.getUser().getPreferences().isSmoke(), stage.getUser().getPreferences().ifFood()));
        stage.getUser().setPhone(MyProfilePhonetx.getText());
        stage.getUser().setCredential(new Credential(MyProfileUsernametx.getText(), MyProfilePasswordtx.getText()));
    }

    public void btAcceptRequest(ActionEvent event) {
        stage.AcceptRequest();
    }


    public void btRemovePassenger() {
        ObservableList<Ride> rideSelected, allRides;
        allRides = ridesTable.getItems();
        rideSelected = ridesTable.getSelectionModel().getSelectedItems();
        Ride removeRide = ridesTable.getSelectionModel().getSelectedItem();
        
        
        try {
			stage.getState().removePassenger(stage.getUser(), removeRide);
		} catch (NotInRide e1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Usted no es pasajero de este viaje");
            alert.setContentText(null);
            alert.showAndWait();
		}
        
    }


    public ObservableList<Ride> getRides() {
        ObservableList<Ride> rides = FXCollections.observableArrayList();

        for (int i = 0; i < stage.getUser().getActiveRides().size(); i++) {
        	rides.add(stage.getUser().getActiveRides().get(i).getRide());
        }
        return rides;
    }

}




