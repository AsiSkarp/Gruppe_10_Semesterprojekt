package Pre;

import Domain.CreditSystem;
import Domain.CrewMember;
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

    ArrayList<ArrayList> proList = CreditSystem.getCreditSystem().readFromPersistance();
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


    public void addPro(ActionEvent actionEvent) {
        User newPro = new Producer(proName.getText(), proEmail.getText(), proPassword.getText());
        proTable.getItems().add(newPro);
        CreditSystem.getCreditSystem().addProducerToSystem(proName.getText(), proEmail.getText(), proPassword.getText());
        CreditSystem.getCreditSystem().writeToPersistance();
    }

    public ObservableList<User> getPro() {
        ObservableList<User> proObser = FXCollections.observableArrayList();
        proObser.add(new Producer("awd", "SDU", "12"));
//        ObservableList<CrewMember> crew = FXCollections.observableArrayList();
        ArrayList<User> fetchCrew = proList.get(0);
        for (User c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            String password = c.getPassword();
            proObser.add(new Producer(name,email,password));
        }
        return proObser;
    }

    public void deletePro(ActionEvent actionEvent) {
    }


    public void goBackBtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }




}
