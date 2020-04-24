package Pre;

import javafx.event.ActionEvent;
import Domain.CreditSystem;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ASystemController {
    public void switchToProducerSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddProducer");
    }

    public void switchToProductionSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }

    public void SwitchToCrewMemberSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddCrewMember");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        CreditSystem.getCreditSystem().logout();
        App.setRoot("login");
    }
}
