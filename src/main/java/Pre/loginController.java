package Pre;

import Domain.CreditSystem;
import Domain.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public TextField passwordText;

    private ActionEvent actionEvent;
    public boolean isHelpOpen;

    public void login() throws IOException {
        if(emailText.getText().equals("") || passwordText.getText().equals("")){
            System.out.println("You need to enter email and password");
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

    public void emailTextonAction(ActionEvent actionEvent) {

    }

    public void passTextonAction(ActionEvent actionEvent) {

    }

    public void btnResetAction(ActionEvent actionEvent) {
        emailText.clear();
        passwordText.clear();
    }

    public void helpButtonAction(ActionEvent actionEvent) throws IOException {
        if (!isHelpOpen) {
            Stage stageHelp = new Stage();
            Scene sceneHelp = new Scene(loadFXML("Help"));
            stageHelp.show();
            stageHelp.setScene(sceneHelp);
            this.isHelpOpen = true;
            stageHelp.setOnCloseRequest(helpEventClose);
        }
    }
    EventHandler<WindowEvent> helpEventClose = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            isHelpOpen = false;
        }
    };

    public void smartLoginPassword(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            login();
        }
    }

    public void smartLoginEmail(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            login();
        }
    }
}
