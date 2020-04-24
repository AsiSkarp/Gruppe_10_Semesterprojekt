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

    ArrayList<ArrayList> fetchList = CreditSystem.getCreditSystem().readFromPersistance();


//    ObservableList<CrewMember> columnList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TableView.TableViewSelectionModel<CrewMember> viewSelectionModel = tableView.getSelectionModel();
//        viewSelectionModel.setSelectionMode(SelectionMode.SINGLE);


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
        CreditSystem.getCreditSystem().addCrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText()));
//      CreditSystem.getCreditSystem().writeToPersistance();
    }

    public ObservableList<CrewMember> getCrewMember() {

        ObservableList<CrewMember> crewMembers = FXCollections.observableArrayList();
        crewMembers.add(new CrewMember("Hamid", "SDU", 12));
//        ObservableList<CrewMember> crew = FXCollections.observableArrayList();
        ArrayList<CrewMember> fetchCrew = fetchList.get(2);
        for (CrewMember c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            int id = c.getCastCrewId();
            crewMembers.add(new CrewMember(name,email,id));
        }
        return crewMembers;
    }

//    public ObservableList<CrewMember> getCrewMembers() {
//        ObservableList<CrewMember> crew = FXCollections.observableArrayList();
//        ArrayList<CrewMember> fetchCrew = fetchList.get(2);
////        //System.out.println(fetchCrew);
//        for (CrewMember c : fetchCrew) {
//            String name = c.getName();
//            String email = c.getEmail();
//            int id = c.getCastCrewId();
//            //crew.add(new CrewMember(name,email,id));
//            System.out.println(name + "  " + email + "  " + id);
//        }
//        return crew;
//    }

    public void deletebtnHandler(ActionEvent actionEvent) {
        ObservableList<CrewMember> selectCrew, AllCrew;
        AllCrew = tableView.getItems();
        selectCrew = tableView.getSelectionModel().getSelectedItems();
        for (CrewMember crew : selectCrew) {
            AllCrew.remove(crew);
        }
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        CreditSystem.getCreditSystem().removeCrewMember(tempCrew.getEmail());
    }

    public void backbtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }
}
