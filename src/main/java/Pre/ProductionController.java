package Pre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProductionController {

    //for Table Column and add Class like: TableView<Class, type>;
    @FXML public TableView tableviewProduction;
    @FXML public TableColumn titleColumn;
    @FXML public TableColumn crewMemberColumn;
    @FXML public TableColumn productionIdColumn;
    @FXML public TableColumn producerIdColumn;

    //TextFields:
    @FXML
    public TextField titleField;
    @FXML
    public TextField crewmemberField;
    @FXML
    public TextField productionIdField;
    @FXML
    public TextField producerIdField;


    public void DeleteButtonOnAction(ActionEvent actionEvent) {
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
    }

    public void openButtonOnAction(ActionEvent actionEvent) {
    }

    public void editButtonOnAction(ActionEvent actionEvent) {
    }
    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }
}
