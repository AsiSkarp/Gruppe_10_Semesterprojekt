package Pre;

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
import java.io.IOException;


public class loginController {

    @FXML
    public TextField emailText;
    @FXML
    public PasswordField passwordText;
    @FXML
    public Button btnLogin;
    @FXML
    public Button btnGuest;


    @FXML
    public void btnLoginonAction(ActionEvent actionEvent) throws IOException {
//        //try {
//            if (emailText.getText().trim().matches("superAdmin") && passwordText.getText().trim().equals("superAdminpassword")) {
//                Parent root = null;
//                try {
//                    root = FXMLLoader.load(getClass().getResource("SASystem"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.setTitle("SuperAdmin system");
//        } else if (emailText.getText().trim().matches("admin") && passwordText.getText().trim().equals("123")) {
//            Parent root = FXMLLoader.load(getClass().getResource("ASystem"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.setTitle("Admin system");
//            } else if (emailText.getText().trim().matches("producer1") || emailText.getText().trim().matches("producent2")&& passwordText.getText().trim().equals("producentpassword")) {
//                Parent root = FXMLLoader.load(getClass().getResource("PSystem"));
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.setTitle("Producent system");
//
//
////            }
////            else {
////                JOptionPane.showMessageDialog(null, "Wrong e-mail or Password!!");
////
////            }
//        //} catch (IOException ex) {
//        }
        App.setRoot("ASystem");
    }

    @FXML
    public void btnGuestonAction(ActionEvent actionEvent) throws IOException {
        App.setRoot("GuestAndRD");
    }

    public void emailTextonAction(ActionEvent actionEvent) {
    }

    public void passTextonAction(ActionEvent actionEvent) {
    }
}
