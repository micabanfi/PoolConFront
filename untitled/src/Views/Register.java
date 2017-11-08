package Views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import root.Exceptions.InvalidFields;
import root.User.Credential;
import root.User.User;
import root.User.Gender;


public class Register extends Controller {


    @FXML
    private TextField RgNametx;
    @FXML
    private TextField RgSurnametx;
    @FXML
    private TextField RgPhonetx;
    @FXML
    private TextField RgCareertx;
    @FXML
    private TextField RgUserNametx;
    @FXML
    private TextField RgPasswordtx;

    // @FXML private DatePicker RgBirthDate;

    @FXML
    private ChoiceBox RgGenrech;
    @FXML
    private ChoiceBox RgSmokech;
    @FXML
    private ChoiceBox RgEatch;

    public Register(ClientStage stage){
        super(stage);
    }

    public void newUser() {
        boolean emptyFields= checkRequestedFields();
        if (!emptyFields) {
            String name, surname, career, phone, username1, password1, genreaux;
            Gender genre;
            Boolean eat = false, smoke = false;
            name = RgNametx.getText();
            surname = RgSurnametx.getText();
            career = RgCareertx.getText();
            phone = RgPhonetx.getText();
            username1 = RgUserNametx.getText();
            password1 = RgPasswordtx.getText();
            genreaux = RgGenrech.getValue().toString();
            switch (genreaux){
                case "Masculino":
                    genre = Gender.MALE;
                    break;
                case "Femenino":
                    genre = Gender.FEMALE;
                    break;
                default:
                    genre = Gender.OTHER;
            }

            String rta = new String(RgEatch.getValue().toString());
            eat = rta.compareTo("Si") == 0;
            rta = RgSmokech.getValue().toString();
            smoke = rta.compareTo("Si") == 0;

            Credential creddential1 = new Credential(username1, password1);
            User usuario1 = new User(creddential1, name, surname, career, phone, smoke, eat, genre);

            try {
                stage.register(usuario1);
                User aux=stage.getUser();
                stage.MainPage();
                System.out.println("entro:\n"+aux.toString());

           } catch (InvalidFields e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Usuario ya existente");
                alert.setContentText(null);
                alert.showAndWait();
            } catch (Exception e) {
                System.out.println("Cant load mainPage");
            }

        }
    }

    public boolean checkRequestedFields(){
        if(RgNametx.getText().isEmpty() || RgSurnametx.getText().isEmpty() ||
                RgPhonetx.getText().isEmpty() ||
                RgCareertx.getText().isEmpty() ||
                RgUserNametx.getText().isEmpty() ||
                RgPasswordtx.getText().isEmpty() ||
                RgGenrech.getSelectionModel().isEmpty()||
                RgSmokech.getSelectionModel().isEmpty()||
                RgEatch.getSelectionModel().isEmpty() ){
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