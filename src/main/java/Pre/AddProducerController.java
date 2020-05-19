package Pre;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddProducerController implements Initializable {

    @FXML public TableView<User> proTable;
    @FXML public TableColumn<Producer, String> proTableName;
    @FXML public TableColumn<Producer, String> proTableEmail;
    @FXML public TableColumn<Producer, String> proTablePassword;
    @FXML public TextField proName;
    @FXML public TextField proEmail;
    @FXML public PasswordField proPassword;
    @FXML public Label resultProducer;
    @FXML public TextField searchField;

    ArrayList<User> proList = CreditSystem.getCreditSystem().getUserList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       if (CreditSystem.getCreditSystem().getCurrentUser().getIsSuperAdmin()) {
           proTableName.setCellValueFactory(new PropertyValueFactory<Producer, String>("name"));
           proTableEmail.setCellValueFactory(new PropertyValueFactory<Producer, String>("email"));
           proTablePassword.setCellValueFactory(new PropertyValueFactory<Producer, String>("password"));

       }else {
           proTableName.setCellValueFactory(new PropertyValueFactory<Producer, String>("name"));
           proTableEmail.setCellValueFactory(new PropertyValueFactory<Producer, String>("email"));
           proTablePassword.setVisible(false);
       }
        updateTableView();
        proTable.setEditable(true);
        proTableName.setCellFactory(TextFieldTableCell.forTableColumn());
        proTableEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        proTablePassword.setCellFactory(TextFieldTableCell.forTableColumn());
        proTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    public void addButtonAction(ActionEvent actionEvent) {
        if (!proName.getText().isEmpty() || !proEmail.getText().isEmpty() || !proPassword.getText().isEmpty()) {
            CreditSystem.getCreditSystem().addProducerToSystem(proName.getText(), proEmail.getText(), proPassword.getText());
            resultProducer.setText("The information has been added to the Database");
            proName.clear();
            proEmail.clear();
            proPassword.clear();
            updateTableView();
        } else {
            resultProducer.setText("you must enter values");
            updateTableView();
        }

    }

    public ObservableList<User> getProducer(ArrayList<User> fetch) {
        ObservableList<User> users = FXCollections.observableArrayList();
        ArrayList<User> fetchedUser = fetch;
        for (User c : fetchedUser) {
            if (c.getIsProducer() && !c.getIsSuperAdmin()){
                String name = c.getName();
                String email = c.getEmail();
                String password = c.getPassword();
                users.add(new Producer(name,email,password));
            }
        }
        System.out.println(users);
        return users;
    }

    public void DeleteButtonAction(ActionEvent actionEvent) {
        ObservableList<User> selectedUser = proTable.getSelectionModel().getSelectedItems();
        User tempUser = proTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure, that you want to remove this user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tempUser != null) {
                CreditSystem.getCreditSystem().removeProducerFromSystem(tempUser.getEmail());
            } else {
                resultProducer.setText("List is empty.");
            }

            if (selectedUser != null) {
                ArrayList<User> rows = new ArrayList<>(selectedUser);
                rows.forEach(row -> proTable.getItems().remove(row));
            }
        } else {
            resultProducer.setText("user chose CANCEL or closed the dialog");
        }

    }

    public void goBackBtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

    public void updateName(TableColumn.CellEditEvent<Producer, String> producerStringCellEditEvent) {
        User tempProducer = proTable.getSelectionModel().getSelectedItem();
        String newName = producerStringCellEditEvent.getNewValue();

        if (tempProducer != null) {
            CreditSystem.getCreditSystem().updateProducer(newName, tempProducer.getEmail(), tempProducer.getPassword());
            resultProducer.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultProducer.setText("Element not found");
        }
    }

    public void updatePassword(TableColumn.CellEditEvent<Producer, String> producerStringCellEditEvent) {
        User tempProducer = proTable.getSelectionModel().getSelectedItem();
        String newPassword = producerStringCellEditEvent.getNewValue();

        if(tempProducer != null){
            CreditSystem.getCreditSystem().updateProducer(tempProducer.getName(), tempProducer.getEmail(), newPassword);
            resultProducer.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultProducer.setText("Element not found");
        }
    }

    public void updateEmail(TableColumn.CellEditEvent<Producer, String> producerStringCellEditEvent) {
        User tempProducer = proTable.getSelectionModel().getSelectedItem();
        String newEmail = producerStringCellEditEvent.getNewValue();

        if(tempProducer != null){
            CreditSystem.getCreditSystem().updateProducer(tempProducer.getName(), newEmail, tempProducer.getPassword());
            resultProducer.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultProducer.setText("Element not found");
        }
    }

    public void updateTableView() {
        ArrayList<User> userList = CreditSystem.getCreditSystem().getUserDatabase();
        proTable.setItems(getProducer(userList));
    }


    public void searchButton(ActionEvent actionEvent) {
        search();
    }
    public void search() {
        if (searchField.textProperty().get().isEmpty()) {
            updateTableView();
        } else {
            ObservableList<User> tableData = FXCollections.observableArrayList();
            ObservableList<TableColumn<User, ?>> tableColumns = proTable.getColumns();
            for (int i = 0; i < proList.size(); i++) {
                for (int j = 0; j < tableColumns.size(); j++) {
                    TableColumn tableColumn = tableColumns.get(j);
                    String cellValue = tableColumn.getCellData(proList.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(searchField.textProperty().get().toLowerCase())) {
                        tableData.add(proList.get(i));
                        break;
                    }
                }
            }
            proTable.setItems(tableData);
        }
    }
    public void searchEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }
}

