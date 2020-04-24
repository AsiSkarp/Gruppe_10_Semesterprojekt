package Pre;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import java.io.IOException;




public class GuestAndRDController {

    @FXML
    public void switchToProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("AllProductions");
    }
    @FXML
    public void switchToCrewMember(ActionEvent actionEvent) throws IOException {
        App.setRoot("CrewMembers");
    }

    public void backButtonguest(ActionEvent actionEvent) throws IOException {
        App.setRoot("login");
    }
}
