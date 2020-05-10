package Pre;

import Domain.CreditSystem;
import Domain.CrewMember;
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

public class AddCrewMemberController implements Initializable {

    @FXML public TableView<CrewMember> tableView;
    @FXML public TableColumn<CrewMember, String> firstNameColumn;
    @FXML public TableColumn<CrewMember, String> emailColumn;
    @FXML public TableColumn<CrewMember, Integer> IdColumn;

    @FXML
    public TextField searchCrewM;
    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField IdField;

    ArrayList<CrewMember> fetchList = CreditSystem.getCreditSystem().getCrewMemberList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        IdColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, Integer>("castCrewId"));
        tableView.setItems(getCrewMember());
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void add() {
        boolean add = CreditSystem.getCreditSystem().addCrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText()));
        if(add) {
            tableView.getItems().add(new CrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText())));
        }
    }

    public void addbtnhandler(ActionEvent actionEvent) {
        add();
    }

    public ObservableList<CrewMember> getCrewMember() {

        ObservableList<CrewMember> crewMembers = FXCollections.observableArrayList();
        ArrayList<CrewMember> fetchCrew = fetchList;
        for (CrewMember c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            int id = c.getCastCrewId();
            crewMembers.add(new CrewMember(name,email,id));
        }
        return crewMembers;
    }

    public void deletebtnHandler(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure that you wanna delete this person?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            ObservableList<CrewMember> selectedCrew = tableView.getSelectionModel().getSelectedItems();
            ObservableList<CrewMember> allCrewMembers = tableView.getItems();
            CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();

            if (tempCrew != null) {
                CreditSystem.getCreditSystem().removeCrewMember(tempCrew.getCastCrewId());
            } else {
                System.out.println("List is empty.");
            }
            if (selectedCrew != null) {
                ArrayList<CrewMember> rows = new ArrayList<>(selectedCrew);
                rows.forEach(row -> tableView.getItems().remove(row));
            } } else {
            // ... user chose CANCEL or closed the dialog
            System.out.println("user chose CANCEL or closed the dialog");
        }
    }



    public void backbtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

    public void updateName(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newName = crewMemberStringCellEditEvent.getNewValue();

        if(tempCrew != null){
            CreditSystem.getCreditSystem().updateCrewMember(newName, tempCrew.getEmail(), tempCrew.getCastCrewId());
            tableView.setItems(getCrewMember());
        } else {
            System.out.println("Element not found");
        }
    }

    public void updateEmail(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newEmail = crewMemberStringCellEditEvent.getNewValue();

        if(tempCrew != null){
            CreditSystem.getCreditSystem().updateCrewMember(tempCrew.getName(), newEmail, tempCrew.getCastCrewId());
            tableView.setItems(getCrewMember());
        } else {
            System.out.println("Element not found");
        }
    }


    //SMART METHODS
    public void smartName(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            add();
        }
    }

    public void smartEmail(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            add();
        }
    }

    public void smartId(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            add();
        }
    }
}
