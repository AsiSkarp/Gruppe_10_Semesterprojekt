package Pre;

import Domain.CreditSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SASystemController {
    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        CreditSystem.getCreditSystem().logout();
        App.setRoot("login");
    }

    @FXML
    public void SwitchToAdminSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddAdmin");
    }
    @FXML
    public void SwitchToProducerSystem(ActionEvent actionEvent)  {
        try {
            App.setRoot("AddProducer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void SwitchToCrewMemberSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddCrewMember");
    }
    @FXML
    public void SwitchToProductionSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }

}
