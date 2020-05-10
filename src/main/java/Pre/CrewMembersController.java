package Pre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class CrewMembersController {
    //tableview Colunms:
    @FXML public TableView tableviewCrewmembers;
    @FXML public TableColumn nameColumn;
    @FXML public TableColumn roleColumn;
    @FXML public TableColumn emailColunm;


    public void exportdataButtonAction(ActionEvent actionEvent) {
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        App.setRoot("GuestAndRD");
    }
}
