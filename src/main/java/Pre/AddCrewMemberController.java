package Pre;

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


//    ObservableList<CrewMember> columnList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        IdColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, Integer>("castCrewId"));
        tableView.setItems(getCrewMember());

        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        IdColumn.setCellValueFactory(Integer.parseInt());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addbtnhandler(ActionEvent actionEvent) {
        CrewMember newCrewMember = new CrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText()));
        tableView.getItems().add(newCrewMember);
    }

    public ObservableList<CrewMember> getCrewMember() {
        ObservableList<CrewMember> crewMembers = FXCollections.observableArrayList();
        crewMembers.add(new CrewMember("Hamid", "SDU", 12));
        return crewMembers;
    }

    public void deletebtnHandler(ActionEvent actionEvent) {
        ObservableList<CrewMember> selectCrew, AllCrew;
        AllCrew = tableView.getItems();
        selectCrew = tableView.getSelectionModel().getSelectedItems();
        for (CrewMember crew : selectCrew) {
            AllCrew.remove(crew);
        }
    }

    public void backbtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }
}
