package Pre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SASystemController {
    @FXML
    public void SwitchToAdminSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddAdmin");
    }
    @FXML
    public void SwitchToCrewMemberSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddCrewMember");
    }
    @FXML
    public void SwitchToProductionSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }
    @FXML
    public void SwitchToProducerSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddProducer");
    }
    @FXML
    public void goBack(ActionEvent actionEvent) {
    }
}
