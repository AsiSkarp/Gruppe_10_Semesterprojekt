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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddProducerController implements Initializable {

    @FXML public TableView<User> proTable;
    @FXML public TableColumn<Producer, String> proTableName;
    @FXML public TableColumn<Producer, String> proTableEmail;
    @FXML public TableColumn<Producer, String> proTablePassword;
    @FXML public TextField proName;
    @FXML public TextField proEmail;
    @FXML public PasswordField proPassword;

    ArrayList<User> proList = CreditSystem.getCreditSystem().getUserList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        proTableName.setCellValueFactory(new PropertyValueFactory<Producer, String>("name"));
        proTableEmail.setCellValueFactory(new PropertyValueFactory<Producer, String>("email"));
        proTablePassword.setCellValueFactory(new PropertyValueFactory<Producer, String>("password"));
        proTable.setItems(getPro());

        proTable.setEditable(true);
        proTableName.setCellFactory(TextFieldTableCell.forTableColumn());
        proTableEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        proTablePassword.setCellFactory(TextFieldTableCell.forTableColumn());
//        IdColumn.setCellValueFactory(Integer.parseInt());
        proTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    public void addButtonAction(ActionEvent actionEvent) {
        User newPro = new Producer(proName.getText(), proEmail.getText(), proPassword.getText());
        proTable.getItems().add(newPro);
        CreditSystem.getCreditSystem().addProducerToSystem(proName.getText(), proEmail.getText(), proPassword.getText());
//        CreditSystem.getCreditSystem().writeToPersistance();
    }

    public ObservableList<User> getPro() {
        ObservableList<User> proObser = FXCollections.observableArrayList();
//        ObservableList<CrewMember> crew = FXCollections.observableArrayList();
        ArrayList<User> fetchedUser = proList;
        for (User c : fetchedUser) {
            String name = c.getName();
            String email = c.getEmail();
            String password = c.getPassword();
            proObser.add(new Producer(name,email,password));
        }
        return proObser;
    }

    public void DeleteButtonAction(ActionEvent actionEvent) {
        ObservableList<User> selectedUser = proTable.getSelectionModel().getSelectedItems();
        User tempUser = proTable.getSelectionModel().getSelectedItem();

        if (tempUser != null) {
            CreditSystem.getCreditSystem().removeAdminFromSystem(tempUser.getEmail());
        } else {
            System.out.println("List is empty.");
        }

        if (selectedUser != null) {
            ArrayList<User> rows = new ArrayList<>(selectedUser);
            rows.forEach(row -> proTable.getItems().remove(row));

        }
    }


    public void goBackBtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

    public void updateName(TableColumn.CellEditEvent<Producer, String> producerStringCellEditEvent) {
        User tempProducer = proTable.getSelectionModel().getSelectedItem();
        String newName = producerStringCellEditEvent.getNewValue();

        if(tempProducer != null){
            CreditSystem.getCreditSystem().updateProducer(newName, tempProducer.getEmail(), tempProducer.getPassword());
            proTable.setItems(getPro());
        } else {
            System.out.println("Element not found");
        }
    }

    public void updatePassword(TableColumn.CellEditEvent<Producer, String> producerStringCellEditEvent) {
        User tempProducer = proTable.getSelectionModel().getSelectedItem();
        String newPassword = producerStringCellEditEvent.getNewValue();

        if(tempProducer != null){
            CreditSystem.getCreditSystem().updateProducer(tempProducer.getName(), tempProducer.getEmail(), newPassword);
            proTable.setItems(getPro());
        } else {
            System.out.println("Element not found");
        }
    }
}
