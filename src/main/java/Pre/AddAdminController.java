package Pre;

import Domain.*;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAdminController implements Initializable {



    @FXML public TableView<User> adminTable;
    @FXML public TableColumn<Admin, String> adminTableName;
    @FXML public TableColumn<Admin, String> adminTableEmail;
    @FXML public TableColumn<Admin, String> adminTablePassword;
    @FXML public TableColumn adminTableType;
    @FXML public TextField adminName;
    @FXML public TextField adminEmail;
    @FXML public PasswordField adminPassword;
    @FXML public Label resultField;
    @FXML public CheckBox chkBoxSuperAdmin;


    ArrayList<User> adminList = CreditSystem.getCreditSystem().getUserList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminTableName.setCellValueFactory(new PropertyValueFactory<Admin, String>("name"));
        adminTableEmail.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
        adminTablePassword.setCellValueFactory(new PropertyValueFactory<Admin, String>("password"));
        adminTableType.setCellValueFactory(new PropertyValueFactory<Admin, String>("usertype"));
        updateTableView();

        adminTable.setEditable(true);
        adminTableName.setCellFactory(TextFieldTableCell.forTableColumn());
        adminTableEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        adminTablePassword.setCellFactory(TextFieldTableCell.forTableColumn());
        adminTableType.setCellFactory(TextFieldTableCell.forTableColumn());
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addBtnHandler(ActionEvent actionEvent) {
        if (!chkBoxSuperAdmin.isSelected()) {
            CreditSystem.getCreditSystem().addAdminToSystem(adminName.getText(), adminEmail.getText(), adminPassword.getText());
        } else {
            CreditSystem.getCreditSystem().addSuperAdmin(adminName.getText(), adminEmail.getText(), adminPassword.getText());
        }
        resultField.setText("The information has been added to the Database");
        updateTableView();
//        CreditSystem.getCreditSystem().writeToPersistance();
        adminName.clear();
        adminEmail.clear();
        adminPassword.clear();
    }

    public ObservableList<User> getAdmin(ArrayList<User> fetch) {

        ObservableList<User> admins = FXCollections.observableArrayList();
        ArrayList<User> fetchedAdmins = fetch;
        for (User c : fetchedAdmins) {
            if (c.getIsAdmin() && !c.getIsSuperAdmin()) {
                String name = c.getName();
                String email = c.getEmail();
                String password = c.getPassword();
                admins.add(new Admin(name, email, password));
            } else if (c.getIsSuperAdmin()) {
                String name = c.getName();
                String email = c.getEmail();
                String password = c.getPassword();
                admins.add(new SuperAdmin(name, email, password));
            }
        }
        return admins;
    }

    public void backBtnHanlder(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

    public void deleteBtnHandler(ActionEvent actionEvent) {
        ObservableList<User> selectedUser = adminTable.getSelectionModel().getSelectedItems();
        User tempUser = adminTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure, that you want to remove this user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tempUser != null && CreditSystem.getCreditSystem().getCurrentUser().getIsSuperAdmin()) {
                CreditSystem.getCreditSystem().removeAdminFromSystem(tempUser.getEmail());
            } else {
                resultField.setText("You can not remove this Admin.");
            }

            if (selectedUser != null) {
                ArrayList<User> rows = new ArrayList<>(selectedUser);
                rows.forEach(row -> adminTable.getItems().remove(row));
            }
        } else {
            resultField.setText("user chose CANCEL or closed the dialog");
        }
    }

    public void SearchBtnhandler(ActionEvent actionEvent) {
    }

    public void updateName(TableColumn.CellEditEvent<Admin, String> adminStringCellEditEvent) {
        User tempAdmin = adminTable.getSelectionModel().getSelectedItem();
        String newName = adminStringCellEditEvent.getNewValue();
        resultField.setText(adminStringCellEditEvent.getTableColumn().toString());
        if (tempAdmin != null) {
            CreditSystem.getCreditSystem().updateAdmin(newName, tempAdmin.getEmail(), tempAdmin.getPassword());
            resultField.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultField.setText("Element not found");
        }
    }

    public void updatePassword(TableColumn.CellEditEvent<Admin, String> adminStringCellEditEvent) {
        User tempAdmin = adminTable.getSelectionModel().getSelectedItem();
        String newPassword = adminStringCellEditEvent.getNewValue();
        resultField.setText(adminStringCellEditEvent.getTableColumn().toString());
        if (tempAdmin != null) {
            CreditSystem.getCreditSystem().updateAdmin(tempAdmin.getName(), tempAdmin.getEmail(), newPassword);
            resultField.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultField.setText("Element not found");
        }
    }

    public void updateEmail(TableColumn.CellEditEvent<Admin, String> adminStringCellEditEvent) {
        User tempAdmin = adminTable.getSelectionModel().getSelectedItem();
        String newEmail = adminStringCellEditEvent.getNewValue();
        resultField.setText(adminStringCellEditEvent.getTableColumn().toString());
        if (tempAdmin != null) {
            CreditSystem.getCreditSystem().updateAdmin(tempAdmin.getName(), newEmail, tempAdmin.getPassword());
            resultField.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultField.setText("Element not found");
        }
    }

    public void updateTableView() {
        ArrayList<User> userList = CreditSystem.getCreditSystem().getUserDatabase();
        adminTable.setItems(getAdmin(userList));
    }
}
