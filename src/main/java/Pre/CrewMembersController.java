package Pre;

import Domain.CreditSystem;
import Domain.CrewMember;
import Persistance.DatabaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrewMembersController implements Initializable {
    //tableview Colunms:
    @FXML public TableView tableviewCrewmembers;
    @FXML public TableColumn nameColumn;
    @FXML public TableColumn roleColumn;
    @FXML public TableColumn emailColunm;
    @FXML public TextField searchTextField;
    @FXML public AnchorPane anchorpane;

    private Connection connection = DatabaseConn.getConnection();
    ObservableList<CrewMember> crewMem = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from CrewMember");
            while (resultSet.next()) {
                crewMem.add(new CrewMember(
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getString("email"),
                        resultSet.getInt("id"))); }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String >("role"));
        emailColunm.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        tableviewCrewmembers.setItems(crewMem);


    }

    public void searchbtn(ActionEvent actionEvent) {
        search();
    }

    public void exportdataButtonAction(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        Window stage = anchorpane.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Crew Member List");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("csv", "*.csv"),
                new FileChooser.ExtensionFilter("doc", "*.doc"),
                new FileChooser.ExtensionFilter("xml", "*.xml"),
                new FileChooser.ExtensionFilter("xls", "*.xls"));
        File file = fileChooser.showSaveDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());
        Writer writer = new BufferedWriter(new FileWriter(file));
        for (CrewMember crewMember : crewMem) {
            String text = crewMember.getName() + ",  " + crewMember.getRole() + ",  " + crewMember.getEmail() + "\n";
            writer.write(text);
        } writer.close();
    }

    public void backbtn(ActionEvent actionEvent) throws IOException {
        App.setRoot("GuestAndRD");
    }

    public void search() {
        if (searchTextField.textProperty().get().isEmpty()) {
            updateTableView();
        }
        ObservableList<CrewMember> tableData = FXCollections.observableArrayList();
        ObservableList<TableColumn<CrewMember, ?>> tableColumns = tableviewCrewmembers.getColumns();
        for (int i = 0; i < crewMem.size(); i++) {
            for (int j = 0; j < tableColumns.size(); j++) {
                TableColumn tableColumn = tableColumns.get(j);
                String cellValue = tableColumn.getCellData(crewMem.get(i)).toString();
                cellValue = cellValue.toLowerCase();
                if (cellValue.contains(searchTextField.textProperty().get().toLowerCase())) {
                    tableData.add(crewMem.get(i));
                    break;
                }
            }
        }
        tableviewCrewmembers.setItems(tableData);
    }

    public void searchEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }
    public void updateTableView() {
        ArrayList<CrewMember> dataList = CreditSystem.getCreditSystem().getCrewMemberDatabase();
        tableviewCrewmembers.setItems(getCrewMember(dataList));
    }
    public ObservableList<CrewMember> getCrewMember(ArrayList<CrewMember> fetch) {
        ObservableList<CrewMember> crewMemberList = FXCollections.observableArrayList();
        ArrayList<CrewMember> fetchCrew = fetch;
        for (CrewMember c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            String role = c.getRole();
            int id = c.getCastCrewId();
            crewMemberList.add(new CrewMember(name, email, role, id));
        }
        return crewMemberList;
    }
}
