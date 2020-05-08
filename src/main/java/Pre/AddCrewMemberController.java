package Pre;

import Domain.CreditSystem;
import Domain.CrewMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    public void addbtnhandler(ActionEvent actionEvent) {
        CrewMember newCrewMember = new CrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText()));
        tableView.getItems().add(newCrewMember);
        CreditSystem.getCreditSystem().addCrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText()));
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
        ObservableList<CrewMember> selectedCrew = tableView.getSelectionModel().getSelectedItems();
        ObservableList<CrewMember> allCrewMembers = tableView.getItems();

        if (selectedCrew != null) {
            ArrayList<CrewMember> rows = new ArrayList<>(selectedCrew);
            rows.forEach(row -> tableView.getItems().remove(row));
        }

        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();

        //Null Pointer Exception NEEDS If statement.
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().removeCrewMember(tempCrew.getEmail());
        } else {
            System.out.println("List is empty.");
        }
    }

    public void backbtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }
}
