package Pre;

import Domain.Admin;
import Domain.CreditSystem;
import Domain.Producer;
import Domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAdminController implements Initializable {



    @FXML public TableView<User> adminTable;
    @FXML public TableColumn<Admin, String> adminTableName;
    @FXML public TableColumn<Admin, String> adminTableEmail;
    @FXML public TableColumn<Admin, String> adminTablePassword;
    @FXML public TextField adminName;
    @FXML public TextField adminEmail;
    @FXML public PasswordField adminPassword;

    ArrayList<User> adminList = CreditSystem.getCreditSystem().getUserList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminTableName.setCellValueFactory(new PropertyValueFactory<Admin, String>("name"));
        adminTableEmail.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
        adminTablePassword.setCellValueFactory(new PropertyValueFactory<Admin, String>("password"));
        adminTable.setItems(getAdmin());

        adminTable.setEditable(true);
        adminTableName.setCellFactory(TextFieldTableCell.forTableColumn());
        adminTableEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        adminTablePassword.setCellFactory(TextFieldTableCell.forTableColumn());
//        IdColumn.setCellValueFactory(Integer.parseInt());
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addBtnHandler(ActionEvent actionEvent) {
        User newAdmin = new Admin(adminName.getText(), adminEmail.getText(), adminPassword.getText());
        adminTable.getItems().add(newAdmin);
        CreditSystem.getCreditSystem().addAdminToSystem(adminName.getText(), adminEmail.getText(), adminPassword.getText());
//        CreditSystem.getCreditSystem().writeToPersistance();

    }
    public ObservableList<User> getAdmin() {

        ObservableList<User> adminObser = FXCollections.observableArrayList();
//        ObservableList<CrewMember> crew = FXCollections.observableArrayList();
        ArrayList<User> fetchCrew = adminList;
        for (User c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            String password = c.getPassword();
            adminObser.add(new Admin(name,email,password));
        }
        return adminObser;
    }

    public void backBtnHanlder(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

    public void deleteBtnHandler(ActionEvent actionEvent) {

    }

    public void SearchBtnhandler(ActionEvent actionEvent) {
    }
}
