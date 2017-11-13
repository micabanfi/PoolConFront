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
import root.Ride.ActiveRideAdmin;
import root.Ride.Ride;
import root.User.Credential;
import root.User.Gender;
import root.User.Preferences;

import javax.annotation.Resources;
import javax.print.DocFlavor;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    private List<ActiveRideAdmin> rides;

 
    
    public MyProfile(ClientStage stage) {

        super(stage);

    }

    public void init() {
        setProfileInfo();
        ruta.setCellValueFactory(new PropertyValueFactory<>("route"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("date"));
        ridesTable.setItems(getRides());

    }

    private ActiveRideAdmin getActiveRideAdmin(Ride ride){
        for(ActiveRideAdmin rideAdmin:stage.getUser().getActiveRides()){
            if(rideAdmin.getRide().equals(ride))
                return rideAdmin;
        }
        return null;
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


    public void editMyProfile() {

        MyProfileNametx.setEditable(true);
        MyProfileSurnametx.setEditable(true);
        MyProfileCareertx.setEditable(true);
        MyProfilePhonetx.setEditable(true);
        MyProfileUsernametx.setEditable(true);
        MyProfilePasswordtx.setEditable(true);
    }

    public void saveChanges(ActionEvent event) {
        stage.getUser().setName(MyProfileNametx.getText());
        stage.getUser().setSurname(MyProfileSurnametx.getText());
        stage.getUser().setPreferences(new Preferences(MyProfileCareertx.getText(), stage.getUser().getPreferences().isSmoke(), stage.getUser().getPreferences().ifFood()));
        stage.getUser().setPhone(MyProfilePhonetx.getText());
        stage.getUser().setCredential(new Credential(MyProfileUsernametx.getText(), MyProfilePasswordtx.getText()));
    }

    public void btAcceptRequest(ActionEvent event) {
        //Hay que pasar como parametro el ActiveRideAdmine del ride seleccionado.
    	/*ObservableList<Ride> rideSelected,allRides;
        allRides=ridesTable.getItems();
        rideSelected=ridesTable.getSelectionModel().getSelectedItems();*/
    	Ride ride=ridesTable.getSelectionModel().getSelectedItem();
        if(ride == null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Debe seleccionar un viaje");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else if(!stage.getUser().equalCredentials(ride.getDriver().getCredential())) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No tiene permisos para aceptar solicitudes");
            alert.setContentText("No es conductor del viaje");
            alert.showAndWait();
        }
        else{
        	ActiveRideAdmin rideAdmin = this.getActiveRideAdmin(ride);
        	stage.AcceptRequest(rideAdmin);
        }
        
        
    }
    
   
    public void btRemovePassenger() {
   
        Ride removeRide = ridesTable.getSelectionModel().getSelectedItem();
        System.out.println("agarro el ride");
        if(removeRide ==null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Debe seleccionar un viaje");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else{
        //System.out.println("antes de eliminar al pasajero");
        //System.out.println(getActiveRideAdmin(removeRide).getPassengers());
        stage.leaveRide(getActiveRideAdmin(removeRide));
        System.out.println("entro a stage.leaveRIDE");
        //System.out.println("depses de elimaiminarlo");
        //System.out.println(getActiveRideAdmin(removeRide).getPassengers());
        ridesTable.setItems(getRides());
        //System.out.println("cargo los rides a la tabla");
        }
    }


    public ObservableList<Ride> getRides() {
        ObservableList<Ride> rides = FXCollections.observableArrayList();
        for(int i=0; i<stage.getUser().getActiveRides().size();i++) {
        	rides.add(stage.getUser().getActiveRides().get(i).getRide());
        }
        return rides;
    }

}




