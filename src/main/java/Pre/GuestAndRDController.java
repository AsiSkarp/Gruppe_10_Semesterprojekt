package Pre;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import java.io.IOException;




public class GuestAndRDController {

    @FXML
    public void switchToProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }
    @FXML
    public void switchToCrewMember(ActionEvent actionEvent) throws IOException {
        App.setRoot("AddCrewMember");
    }

    public void backButtonguest(ActionEvent actionEvent) throws IOException {
        App.setRoot("login");
    }
}
