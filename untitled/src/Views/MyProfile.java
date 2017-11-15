package Views;

import Views.Table.ViajesAnotados.ViajesAnotadosTable;
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
import root.User.*;

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
    private TableView<ViajesAnotadosTable> ridesTable;
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
        ruta.setCellValueFactory(new PropertyValueFactory<>("ruta"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
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


    public void editMyProfile() {

        MyProfileNametx.setEditable(true);
        MyProfileSurnametx.setEditable(true);
        MyProfileCareertx.setEditable(true);
        MyProfilePhonetx.setEditable(true);
        MyProfilePasswordtx.setEditable(true);
    }

    public void saveChanges(ActionEvent event) {
        stage.modifyUser(MyProfileNametx.getText(), MyProfileSurnametx.getText(), MyProfilePhonetx.getText(), new Preferences(MyProfileCareertx.getText(), stage.getUser().getPreferences().isSmoke(), stage.getUser().getPreferences().isFood()), MyProfilePasswordtx.getText());
    }

    public void btAcceptRequest(ActionEvent event) {
    	ViajesAnotadosTable viajes=ridesTable.getSelectionModel().getSelectedItem();
        if(viajes == null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Debe seleccionar un viaje");
            alert.setContentText(null);
            alert.showAndWait();
        }else if(!stage.getUser().equals(viajes.getActiveRideAdmin().getRide().getDriver())) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No tiene permisos para aceptar solicitudes");
            alert.setContentText("No es conductor del viaje");
            alert.showAndWait();
        }else{
            stage.AcceptRequest(viajes.getActiveRideAdmin());
        }
    }
    
    /*Este bot�n a�ade 2 autos default al usuario y nos exime de hacer un formulario 
     * para ingresar autos. */
    public void btDefaultVehicle() {
    	 Vehicle vehicle1 = new Vehicle("Fiat", "500", "Blanco", 2015, "ABC123", 0);
    	 Vehicle vehicle2=new Vehicle("Ford","K","Celeste",2010,"ARX420",3);
    	 stage.getUser().addVehicle(vehicle1);
    	 stage.getUser().addVehicle(vehicle2);
    	
    }
    
    
   
    public void btRemovePassenger() {
   
        ActiveRideAdmin removeRide = ridesTable.getSelectionModel().getSelectedItem().getActiveRideAdmin();
        System.out.println("agarro el ride");
        if(removeRide ==null) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Debe seleccionar un viaje");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else if(removeRide.getRide().getDriver().equalCredentials(stage.getUser().getCredential())) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Usted es el conductor del viaje");
            alert.setContentText("Si quiere darse de baja, debe eliminar el viaje en la p�gina principal");
            alert.showAndWait();
        }
        else{
        stage.leaveRide(removeRide);
        ridesTable.setItems(getRides());
        }
    }


    public ObservableList<ViajesAnotadosTable> getRides() {
        ObservableList<ViajesAnotadosTable> rides = FXCollections.observableArrayList();
        for(ActiveRideAdmin rideAdmin : stage.getUser().getActiveRides()){
        	rides.add(new ViajesAnotadosTable(rideAdmin));
        }
        return rides;
    }

}




