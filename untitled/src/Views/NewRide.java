package Views;

import java.time.LocalDate;
import java.util.Date;

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
import root.Ride.Permissions;
import root.Ride.Ride;
import root.Ride.Route;
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

    public NewRide(ClientStage stage){
        super(stage);
    }
    public void btMainPage(ActionEvent event) {
        stage.MainPage();
    }

    public void btCreateRide(ActionEvent event) {
    	boolean emptyFields = checkRequestedFields();
    	if(!emptyFields) {
    		String from, to, route, eatAux, smokeAux, hour, minutes;
    		boolean eat, smoke;
    		LocalDate date;
    		date = dateDp.getValue();
    		from = fromTx.getText();
    		to= toTx.getText();
    		route = routeTx.getText();
    		hour = hourCb.getValue();
    		minutes = minutesCb.getValue();

			String rta = eatCb.getValue();
			eat = rta.compareTo("Si") == 0;
			rta = smokeCb.getValue();
			smoke = rta.compareTo("Si") == 0;

    		Route route1 = new Route(from, to, route);
    		Permissions permissions = new Permissions(smoke, eat, eat);
    		
    		/*VEHICLE DE LA PERSONA Y PERSONA sera modificado cuando Pedro cambie user y person.
    		Ride ride = new Ride(route1, VEHICLE DE LA PERSONA, PERSONA, permissions, date);
    		
    		stage.getState().AddRideToList(ride);
    		*/
    		stage.MainPage();
    	}
    	
    }
    
    //PREGUNTAR PQ ME TIRA NPE cuando no se llena un choice box y se hace minutesCb.getSelect...isempty
    public boolean checkRequestedFields(){
        if( fromTx.getText().isEmpty() || toTx.getText().isEmpty() || routeTx.getText().isEmpty() ||
        		hourCb.getSelectionModel().isEmpty() || minutesCb.getSelectionModel().isEmpty() ||
        		smokeCb.getSelectionModel().isEmpty() ||eatCb.getSelectionModel().isEmpty() || dateDp.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            String content = "Por Favor llene todos los campos";
            alert.setContentText(content);
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public void init(){}
}
