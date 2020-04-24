package Pre;

import Domain.CreditSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddAdminController {

    @FXML
    public TextField name;
    @FXML
    public PasswordField password;
    @FXML
    public TextField email;
    @FXML
    public TextField searchField;

    public void addBtnHandler(ActionEvent actionEvent) {
        CreditSystem.getCreditSystem().addAdminToSystem(name.getText(), email.getText(), password.getText());
        System.out.println(name.getText() + " er en guttermand!");
    }

    public void SearchBtnhandler(ActionEvent actionEvent) {
    }

    public void backBtnHanlder(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

    public void deleteBtnHandler(ActionEvent actionEvent) {
    }

    public void editBtnHandler(ActionEvent actionEvent) {
    }
}
