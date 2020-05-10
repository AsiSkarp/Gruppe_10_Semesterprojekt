package Pre;

import Domain.CreditSystem;
import Domain.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.security.auth.login.CredentialException;
import java.io.IOException;

import static Pre.App.loadFXML;


public class loginController {

    @FXML
    public TextField emailText;
    @FXML
    public Button btnLogin;
    @FXML
    public Button btnGuest;
    @FXML
    public Button btnReset;
    @FXML
    public PasswordField passwordText;

    private ActionEvent actionEvent;
    public boolean isHelpOpen;

    public void login() throws IOException {
        if (emailText.getText().equals("") || passwordText.getText().equals("")) {

            System.out.println("You need to enter email and password");


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You need to enter email and password!");
            alert.showAndWait();

        } else {
            Login.getLogin().login(emailText.getText(), passwordText.getText());
            if (CreditSystem.getCreditSystem().getCurrentUser() != null) {
                if (CreditSystem.getCreditSystem().getCurrentUser().getIsSuperAdmin()) {
                    App.setCurrentRoom("SASystem");
                    App.setRoot("SASystem");
                } else if (CreditSystem.getCreditSystem().getCurrentUser().getIsAdmin()) {
                    App.setCurrentRoom("ASystem");
                    App.setRoot("ASystem");
                } else if (CreditSystem.getCreditSystem().getCurrentUser().getIsProducer()) {
                    App.setCurrentRoom("PSystem");
                    App.setRoot("PSystem");
                }
            } else {
                System.out.println("Email and password doesn't match");


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Email and password doesn't match");
                alert.showAndWait();

            }
        }
    }

    @FXML
    public void btnLoginonAction(ActionEvent actionEvent) throws IOException {
        login();
    }

    @FXML
    public void btnGuestonAction(ActionEvent actionEvent) throws IOException {
        App.setRoot("GuestAndRD");
    }


    public void btnResetAction(ActionEvent actionEvent) {
        emailText.clear();
        passwordText.clear();
    }

    public void helpButtonAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help Center");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");
        alert.showAndWait();
    }


    public void smartLoginPassword(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            login();
        }
    }

    public void smartLoginEmail(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            login();
        }
    }

        public void aboutus (ActionEvent actionEvent){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Group 10");
            alert.setHeaderText("How are we? ");
            alert.setContentText("Software engineering students at SDU university.\n " +
                    "Ahmad Hammami\n Kevin Matin\n Casper Fischer\n Hamid Qayoumi\n " +
                    "Ásbjörn Skarphéðinsson\n Nurettin Kaan Koc ");
            alert.showAndWait();
        }
    }


