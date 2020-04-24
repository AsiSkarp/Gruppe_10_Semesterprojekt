package Pre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class AllProductionsController {

    //tableview Colunms: define the Class
    @FXML public TableView tableviewAllProduction;
    @FXML public TableColumn tiltleCoulnmPro;
    public TableColumn crewMemberCoulmnPro;
    @FXML public TableColumn productionIdColunmPro;
    @FXML public TableColumn producerIdColumnPro;

    public void exportButtonData(ActionEvent actionEvent) {
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        App.setRoot("GuestAndRD");
    }
}
