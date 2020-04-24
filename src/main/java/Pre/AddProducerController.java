package Pre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddProducerController {
    //For table column and defin Classe in TableView<class name, type>
    @FXML public TableView tableviewPoducer;
    @FXML public TableColumn nameColumn;
    @FXML public TableColumn emailColumn;
    @FXML public TableColumn passwordColumn;

    //for texts fields
    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;


    public void addButtonAction(ActionEvent actionEvent) {
    }

    public void DeleteButtonAction(ActionEvent actionEvent) {
    }

    public void editButtonAction(ActionEvent actionEvent) {
    }
    public void goBackBtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

}
