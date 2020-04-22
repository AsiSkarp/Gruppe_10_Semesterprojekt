package Presentation;

import javafx.event.ActionEvent;

import java.io.IOException;

public class SystemSAController {
    public void SwitchToAdminSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddAdmin");
    }

    public void SwitchToCrewMemberSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddCrewMember");
    }

    public void SwitchToProductionSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }

    public void SwitchToProducerSystem(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddProducer");
    }
}
