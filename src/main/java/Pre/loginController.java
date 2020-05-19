package Pre;

import Domain.CreditSystem;
import Domain.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class loginController {

    @FXML
    public TextField emailText;
    @FXML
    public Button btnLogin;
    @FXML
    public Button btnGuest;
    @FXML
    public TextField passwordText;

    private ActionEvent actionEvent;
    public boolean isHelpOpen;

    public void login() throws IOException {
        CreditSystem.getCreditSystem().logout();
        if(emailText.getText().equals("") || passwordText.getText().equals("")){
            System.out.println("You need to enter email and password");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You need to enter email and password");
            alert.setContentText("If you don`t have an email, you can login as a guest!");
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

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Email and password doesn't match!");
                alert.setContentText("Careful with the next try!");
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
        CreditSystem.getCreditSystem().logout();
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

    public void aboutus(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About us");
        alert.setHeaderText("How are we? ");
        alert.setContentText("We are Software Engineering students at SDU." +
                        "\nAhmad Hammami\nKevin Matin\nCasper Fischer\nHamid Qayoumi\nÁsbjörn Skarphéðinsson\nNurettin Kaan Koc");

        alert.showAndWait();
    }
}
