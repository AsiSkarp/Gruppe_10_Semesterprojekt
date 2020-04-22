package Presentation;

import javafx.event.ActionEvent;

import java.io.IOException;

public class GuestAndRDController {
    public void switchToProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("AllProductions");
    }

    public void switchToCrewMember(ActionEvent actionEvent) throws IOException {
        App.setRoot("CrewMembers");
    }
}
