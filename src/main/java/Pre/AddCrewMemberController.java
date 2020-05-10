package Pre;

import Data.CrewMemberData;
import Data.DatabaseConn;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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



    private Connection connection = DatabaseConn.getConnection();
    ArrayList<CrewMember> fetchList = CreditSystem.getCreditSystem().getCrewMemberList();
    ObservableList<CrewMember> crewMem = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from CrewMember");
            while (resultSet.next()) {
                crewMem.add(new CrewMember(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String >("role"));
        IdColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, Integer>("castCrewId"));
        tableView.setItems(getCrewMember());
        tableView.setItems(crewMem);
        //Edit the table data:
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    //Maybe if statementt use in addbutton tomorrow:
    public void addbtnhandler(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //add crew members to the list:
        if (actionEvent.getSource() == AddCrewButton) {
            addToDatabase();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CrewMember ORDER BY id DESC LIMIT 1");
            while(resultSet.next()) {
                crewMem.add(new CrewMember(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getInt("id")));
            }
            tableView.setItems(crewMem);

//            CrewMember newCrewMember = new CrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText()));
 //           tableView.getItems().add(newCrewMember);
//            CreditSystem.getCreditSystem().addCrewMember(nameField.getText(), emailField.getText(), Integer.parseInt(IdField.getText()));

        } else if (actionEvent.getSource() == updateButton) {
//            try {
//                CrewMemberData.updateOnAction(Integer.parseInt(IdField.getText()), emailField.getText(), nameField.getText());
//                resultLabel.setText("The data is updated in Database");
//            } catch (SQLException e) {
//                System.out.println("Error! while update the info");
//                e.printStackTrace();
//                throw e;
//            }
        } else if (actionEvent.getSource() == backButton) {
            try {
                App.setRoot(App.getCurrentRoom());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == searchButton) {
            if (searchCrewM.textProperty().get().isEmpty()) {
                tableView.setItems(crewMem);
                return;
            }
            ObservableList<CrewMember> tableData = FXCollections.observableArrayList();
            ObservableList<TableColumn<CrewMember, ?>> tableColumns = tableView.getColumns();
            for (int i = 0; i < crewMem.size(); i++) {
                for (int j = 0; j < tableColumns.size(); j++) {
                    TableColumn tableColumn = tableColumns.get(j);
                    String cellValue = tableColumn.getCellData(crewMem.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(searchCrewM.textProperty().get().toLowerCase())) {
                        tableData.add(crewMem.get(i));
                        break;
                    }
                }
            }
            tableView.setItems(tableData);
        }
    }

    public ObservableList<CrewMember> getCrewMember() {

        ObservableList<CrewMember> crewMembers = FXCollections.observableArrayList();
        ArrayList<CrewMember> fetchCrew = fetchList;
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
        //Delete crew member from list:
        ObservableList<CrewMember> selectedCrew = tableView.getSelectionModel().getSelectedItems();
        ObservableList<CrewMember> allCrewMembers = tableView.getItems();
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();

        if (tempCrew != null) {
            CreditSystem.getCreditSystem().removeCrewMember(tempCrew.getCastCrewId());
        } else {
            System.out.println("List is empty.");
        }

        deleteFromDatabase();
        if (selectedCrew != null) {
            ArrayList<CrewMember> rows = new ArrayList<>(selectedCrew);
            rows.forEach(row -> tableView.getItems().remove(row));
        }
    }

    public void updateName(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) throws SQLException, ClassNotFoundException {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newName = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(newName, tempCrew.getEmail(), tempCrew.getRole(), tempCrew.getCastCrewId());
        } else {
            System.out.println("Element not found");
        }
        try {
            CrewMemberData.updateOnActionName(tempCrew.getCastCrewId(), newName);
            resultLabel.setText("The data is updated in Database");
            tableView.setItems(crewMem);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error! while update the info");
            e.printStackTrace();
            throw e;
        }
    }

    public void updateEmail(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) throws SQLException, ClassNotFoundException {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newEmail = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            System.out.println(newEmail);
            CreditSystem.getCreditSystem().updateCrewMember(tempCrew.getName(), newEmail, tempCrew.getRole(), tempCrew.getCastCrewId());
        } else {
            System.out.println("Element not found");
        }
        try {
            CrewMemberData.updateOnActionEmail(tempCrew.getCastCrewId(), newEmail);
            resultLabel.setText("The data is updated in Database");
            tableView.setItems(crewMem);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error! while update the info");
            e.printStackTrace();
            throw e;
        }
    }
    public void updateRole(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) throws SQLException, ClassNotFoundException {
        CrewMember tempCrew = tableView.getSelectionModel().getSelectedItem();
        String newRole = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(tempCrew.getName(), tempCrew.getEmail(), newRole, tempCrew.getCastCrewId());
        } else {
            System.out.println("Element not found");
        }
        try {
            CrewMemberData.updateOnActionRole(tempCrew.getCastCrewId(), newRole);
            resultLabel.setText("The data is updated in Database");
            tableView.setItems(crewMem);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error! while update the info");
            e.printStackTrace();
            throw e;
        }
    }

    public void addToDatabase() throws SQLException, ClassNotFoundException {
        //add crew member to database:
        CrewMemberData.addbtnhandler(nameField.getText(), emailField.getText(), roleField.getText());
        resultLabel.setText("The information has been added to the Database");
        tableView.setItems(crewMem);
        nameField.clear();
        emailField.clear();
        roleField.clear();
        IdField.clear();
    }

    public void deleteFromDatabase() {
        //delete crew member from database:
      // int i = tableView.getSelectionModel().getSelectedItem().getCastCrewId();
        try {
            CrewMemberData.deletebtnHandler(tableView.getSelectionModel().getSelectedItem().getCastCrewId());
            resultLabel.setText("The data has been deleted");
        } catch (SQLException e) {
            System.out.println("Error! while delete the data");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

//    public Connection getConnection() {
//        return connection;
//    }
}
