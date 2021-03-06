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
    public Button addCrewButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button backButton;
    @FXML
    public Button searchButton;
    @FXML
    public Button btnOpenSelected;
    @FXML
    public Button deleteBtn;
    @FXML
    public Label infoLabel;


    //ArrayList<CrewMember> fileList = CreditSystem.getCreditSystem().getCrewMemberList();
    ArrayList<CrewMember> dataList = CreditSystem.getCreditSystem().getCrewMembers();
    public static CrewMember selectedCrewMember;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        //roleColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String >("role"));
        IdColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, Integer>("id"));
        if (CreditSystem.getCreditSystem().getCurrentUser() == null) {
            addCrewButton.setVisible(false);
            deleteBtn.setVisible(false);
            nameField.setVisible(false);
            emailField.setVisible(false);
            infoLabel.setVisible(false);
        }
        updateTableView();

        //Edit the table data:
        tableView.setEditable(false);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void openBtnHandler(ActionEvent actionEvent) throws IOException{
        selectedCrewMember = tableView.getSelectionModel().getSelectedItem();
        App.setRoot("CrewMembers");
    }

    //Maybe if statementt use in addbutton tomorrow:
    public void addbtnhandler(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!nameField.getText().isEmpty() || !emailField.getText().isEmpty()){
            CreditSystem.getCreditSystem().addCrewMember(nameField.getText(), emailField.getText());
            resultLabel.setText("The information has been added to the Database");
        } else {
            resultLabel.setText("you must enter values");
        }
        updateTableView();
        nameField.clear();
        emailField.clear();
        }


    public ObservableList<CrewMember> getCrewMember(ArrayList<CrewMember> fetch) {
        ObservableList<CrewMember> crewMembers = FXCollections.observableArrayList();
        ArrayList<CrewMember> fetchCrew = fetch;
        for (CrewMember c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            int id = c.getId();
            CrewMember returnCrew = new CrewMember(name, email);
            returnCrew.setId(id);
            crewMembers.add(returnCrew);
        }
        return crewMembers;
    }

    public void deleteButton(ActionEvent actionEvent) {
        ObservableList<CrewMember> selectedCrew = tableView.getSelectionModel().getSelectedItems();
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure, that you want to remove this Crew member?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            //Delete crew member from list:
            if (tempCrew != null) {
                CreditSystem.getCreditSystem().removeCrewMember(tempCrew.getEmail());
            } else {
                resultLabel.setText("List is empty.");
            }

            if (selectedCrew != null) {
                ArrayList<CrewMember> rows = new ArrayList<>(selectedCrew);
                rows.forEach(row -> tableView.getItems().remove(row));
            }
        } else {
            // ... user chose CANCEL or closed the dialog
            resultLabel.setText("user chose CANCEL or closed the dialog");
        }

    }

    public void updateName(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newName = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(newName, tempCrew.getEmail());
            resultLabel.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultLabel.setText("Element not found");
        }

    }

    public void updateEmail(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newEmail = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(tempCrew.getName(), newEmail);
            resultLabel.setText("The data is updated in Database");
            updateTableView();
        } else {
            resultLabel.setText("Element not found");
        }
    }

    public void backBtnHandler(ActionEvent actionEvent) throws IOException {
        if (CreditSystem.getCreditSystem().getCurrentUser() != null) {
            App.setRoot(App.getCurrentRoom());
        } else {
            App.setRoot("GuestAndRD");
        }
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
            search();
    }

    public void updateTableView() {
        ArrayList<CrewMember> dataList = CreditSystem.getCreditSystem().getCrewMembers();
        tableView.setItems(getCrewMember(dataList));
    }

    public static CrewMember getSelectedCrewMember() {
        return selectedCrewMember;
    }
}
