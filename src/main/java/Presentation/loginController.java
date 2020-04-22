package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class loginController {

    @FXML
    public TextField emailText;
    @FXML
    public PasswordField passText;
    @FXML
    public Button btnLogin;
    @FXML
    public Button btnGuest;


    public void emailTextonAction(ActionEvent actionEvent) {
    }

    public void passTextonAction(ActionEvent actionEvent) {
    }

    public void btnLoginonAction(ActionEvent actionEvent) {
    }


    public void btnGuestonAction(ActionEvent actionEvent) throws IOException {
        App.setRoot("GuestAndRD");
    }
}
