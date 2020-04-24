package Pre;

import Domain.CreditSystem;
import Domain.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javax.security.auth.login.CredentialException;
import java.io.IOException;


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


    @FXML
    public void btnLoginonAction(ActionEvent actionEvent) throws IOException {
        if(emailText.getText() == null || passwordText.getText() == null){
            System.out.println("You need to enter email and password");
        } else {
            Login.getLogin().login(emailText.getText(), passwordText.getText());
            if(CreditSystem.getCreditSystem().getCurrentUser().getIsSuperAdmin()) {
                App.setCurrentRoom("SASystem");
                App.setRoot("SASystem");
            } else if (CreditSystem.getCreditSystem().getCurrentUser().getIsAdmin()) {
                App.setCurrentRoom("ASystem");
                App.setRoot("ASystem");
            } else if (CreditSystem.getCreditSystem().getCurrentUser().getIsProducer()) {
                App.setCurrentRoom("PSystem");
                App.setRoot("PSystem");
            }
        }

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
}
