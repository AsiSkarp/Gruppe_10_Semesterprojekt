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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCrewMemberController implements Initializable {

    @FXML
    public TableView<CrewMember> tableView;
    @FXML
    public TableColumn<CrewMember, String> firstNameColumn;
    @FXML
    public TableColumn<CrewMember, String> emailColumn;
    @FXML
    public TableColumn<CrewMember, String> roleColumn;
    @FXML
    public TableColumn<CrewMember, Integer> IdColumn;

    @FXML
    public TextField searchCrewM;
    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField roleField;
    @FXML
    public TextField IdField;
    @FXML
    public Label resultLabel;
    @FXML
    public Button AddCrewButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button backButton;
    @FXML
    public Button searchButton;



    ArrayList<CrewMember> fileList = CreditSystem.getCreditSystem().getCrewMemberList();
    ArrayList<CrewMember> dataList = CreditSystem.getCreditSystem().getCrewMemberDatabase();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String >("role"));
        IdColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, Integer>("castCrewId"));
        updateTableView();

        //Edit the table data:
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    //Maybe if statementt use in addbutton tomorrow:
    public void addbtnhandler(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int crewMemId = CreditSystem.getCreditSystem().getCrewMemIdFromDatabase();
        CreditSystem.getCreditSystem().addCrewMember(nameField.getText(), emailField.getText(), roleField.getText(), crewMemId);
        resultLabel.setText("The information has been added to the Database");
        updateTableView();
        nameField.clear();
        emailField.clear();
        roleField.clear();
        IdField.clear();
        }

    public ObservableList<CrewMember> getCrewMember(ArrayList<CrewMember> fetch) {
        ObservableList<CrewMember> crewMembers = FXCollections.observableArrayList();
        ArrayList<CrewMember> fetchCrew = fetch;
        for (CrewMember c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            String role = c.getRole();
            int id = c.getCastCrewId();
            crewMembers.add(new CrewMember(name, email, role, id));
        }
        return crewMembers;
    }

    public void deleteButton(ActionEvent actionEvent) {
        ObservableList<CrewMember> selectedCrew = tableView.getSelectionModel().getSelectedItems();
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure, that you wanna remove this Crew member?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            //Delete crew member from list:
            if (tempCrew != null) {
                CreditSystem.getCreditSystem().removeCrewMember(tempCrew.getCastCrewId());
            } else {
                System.out.println("List is empty.");
            }

            if (selectedCrew != null) {
                ArrayList<CrewMember> rows = new ArrayList<>(selectedCrew);
                rows.forEach(row -> tableView.getItems().remove(row));
            }
        } else {
            // ... user chose CANCEL or closed the dialog
            System.out.println("user chose CANCEL or closed the dialog");
        }

    }

    public void updateName(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newName = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(newName, tempCrew.getEmail(), tempCrew.getRole(), tempCrew.getCastCrewId());
            resultLabel.setText("The data is updated in Database");
            updateTableView();
        } else {
            System.out.println("Element not found");
        }

    }

    public void updateEmail(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newEmail = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(tempCrew.getName(), newEmail, tempCrew.getRole(), tempCrew.getCastCrewId());
            resultLabel.setText("The data is updated in Database");
            updateTableView();
        } else {
            System.out.println("Element not found");
        }
    }

    public void updateRole(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newRole = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(tempCrew.getName(), tempCrew.getEmail(), newRole, tempCrew.getCastCrewId());
            resultLabel.setText("The data is updated in Database");
            updateTableView();
        } else {
            System.out.println("Element not found");
        }

    }

    public void backBtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }

    public void search() {
        if (searchCrewM.textProperty().get().isEmpty()) {
            updateTableView();
        }
        ObservableList<CrewMember> tableData = FXCollections.observableArrayList();
        ObservableList<TableColumn<CrewMember, ?>> tableColumns = tableView.getColumns();
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = 0; j < tableColumns.size(); j++) {
                TableColumn tableColumn = tableColumns.get(j);
                String cellValue = tableColumn.getCellData(dataList.get(i)).toString();
                cellValue = cellValue.toLowerCase();
                if (cellValue.contains(searchCrewM.textProperty().get().toLowerCase())) {
                    tableData.add(dataList.get(i));
                    break;
                }
            }
        }
        tableView.setItems(tableData);
    }

    public void searchEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }

    public void searchBtn(ActionEvent actionEvent) {
        if(actionEvent.equals(KeyCode.ENTER)){
            search();
        }
    }

    public void updateTableView() {
        ArrayList<CrewMember> dataList = CreditSystem.getCreditSystem().getCrewMemberDatabase();
        tableView.setItems(getCrewMember(dataList));
    }
}
