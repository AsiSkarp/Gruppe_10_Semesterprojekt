package Pre;

import Domain.CreditSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddAdminController {

@FXML
public TextField name;
@FXML
public PasswordField password;
@FXML
public TextField email;

    public void addBtnHandler(ActionEvent actionEvent) {
        CreditSystem.getCreditSystem().addAdminToSystem(name.getText(),email.getText(),password.getText());
        System.out.println(name.getText() + " er en guttermand!");
    }
}
