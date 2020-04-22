package Pre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PSystemController {
    @FXML
    public void switchToProductionSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }
    @FXML
    public void SswitchToCrewMembersSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddCrewMember");
    }

    public void goBack(ActionEvent actionEvent) {
    }
}
