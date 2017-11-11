 package Views;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.Exceptions.ExistingRideException;
import root.Exceptions.NoVehicleException;
import root.Ride.ActiveRideAdmin;
import root.Ride.Permissions;
import root.Ride.Ride;
import root.Ride.Route;
import root.User.User;
import root.User.Vehicle;

public class NewRide extends Controller {
	
	    @FXML
	    private TextField fromTx;
	    @FXML
	    private TextField toTx;
	    @FXML
	    private TextField routeTx;
	    @FXML
	    private ChoiceBox<String> hourCb;
	    @FXML
	    private ChoiceBox<String> minutesCb;
	    @FXML
	    private DatePicker dateDp;
	    @FXML
	    private ChoiceBox<String> eatCb;
	    @FXML
	    private ChoiceBox<String> smokeCb;
	    @FXML
	    private ChoiceBox<String> vehiclesCb;
	    private List<String> listVehicles = new ArrayList<>();
	    private ObservableList<String> observableListVehicles;

    public NewRide(ClientStage stage){
        super(stage);
    }
    public void btMainPage(ActionEvent event) {
        stage.MainPage();
    }

    public void btCreateRide(ActionEvent event) {
    	boolean emptyFields = checkRequestedFields();
    	if(!emptyFields) {
    		String from, to, route, eatAux, smokeAux,hour,minutes, vehicleString;
    		boolean eat, smoke;
    		LocalDate date=dateDp.getValue();
    		int year=date.getYear();
    		int month=date.getMonthValue();
    		int day=date.getDayOfMonth();
    		from = fromTx.getText();
    		to= toTx.getText();
    		route = routeTx.getText();
    		hour = hourCb.getValue();
    		minutes=minutesCb.getValue();
    		vehicleString = vehiclesCb.getValue();
    		int hourInt=Integer.parseInt(hour);
    		int minutesInt = Integer.parseInt(minutes);
    		//System.out.println("anio"+year+"mes"+month+"dia"+day+"hora"+hourInt+"min"+minutesInt);
    		//LocalDateTime dateOf=LocalDateTime.of(17,8,12,14,30);
			LocalDateTime dateOf=LocalDateTime.of(year,month,day,hourInt,minutesInt);

			String rta = eatCb.getValue();
			eat = rta.compareTo("Si") == 0;
			rta = smokeCb.getValue();
			smoke = rta.compareTo("Si") == 0;
    		Route route1 = new Route(from, to, route);
    		Permissions permissions = new Permissions(smoke, eat);
    		Vehicle vehicle = findUserVehicle(vehicleString);
    		Ride ride = new Ride(route1, vehicle, stage.getUser(), permissions, dateOf);
			ActiveRideAdmin activeRide = new ActiveRideAdmin(ride);
    		System.out.println(ride);
    		try {
				stage.getState().addRideToList(activeRide);
			} catch (ExistingRideException e) {
				 Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("El viaje no pudo ser creado");
		            alert.setContentText(null);
		            alert.showAndWait();
			}
    		
    		stage.MainPage();
    	}
    	
    }
    
    private Vehicle findUserVehicle(String vehicleString) {
    	Vehicle vehicleChoice = null;
    	boolean notfound =true;
    	for(int i=0; i<stage.getUser().getVehicles().size() && notfound ;i++) {
    		if(vehicleString.compareTo(stage.getUser().getVehicles().get(i).toString()) == 0) {
    			vehicleChoice = stage.getUser().getVehicles().get(i);
    			notfound= false;
    		}
    	}
    return vehicleChoice;
    }
    
   //PREGUNTAR: COMO hariamos esto con excepciones
    private boolean checkRequestedFields(){
        if( fromTx.getText().isEmpty() || toTx.getText().isEmpty() || routeTx.getText().isEmpty() ||
        		hourCb.getSelectionModel().isEmpty() || minutesCb.getSelectionModel().isEmpty() ||
        		smokeCb.getSelectionModel().isEmpty() ||eatCb.getSelectionModel().isEmpty() || dateDp.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            String content = "Por Favor llene todos los campos";
            alert.setContentText(content);
            alert.showAndWait();
            return true;
        }
        else if( dateDp.getValue().isBefore(LocalDate.now())) {
        	 Alert alert = new Alert(Alert.AlertType.ERROR);
             String content = "La fecha seleccionada es incorrecta";
             alert.setContentText(content);
             alert.showAndWait();
        	return true;
        }
        return false;
    }

    public void init() {
    	for (Vehicle vehicle : stage.getUser().getVehicles())
		listVehicles.add("Vehicle:"+'\n'+"Brand:"+vehicle.getBrand()+"Model:"+vehicle.getModel()+" Color:"+vehicle.getColor()+" Plate:"+vehicle.getPlate()+"Seats:"+vehicle.getSeats());
		observableListVehicles = FXCollections.observableArrayList(listVehicles);
		vehiclesCb.setItems(observableListVehicles);
    }
}
