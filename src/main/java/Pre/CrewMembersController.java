package Pre;

import Domain.CreditSystem;
import Domain.CrewMember;
import Domain.CrewProduction;
import Domain.Production;
import Persistance.DatabaseConn;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrewMembersController implements Initializable {

    //Tableview Colunms:
    @FXML public TableView<CrewProduction> tableViewCrewMembers;
    @FXML public TableColumn<CrewProduction, String> productionColumn;
    @FXML public TableColumn<CrewProduction, String> roleColumn;
    @FXML public TableColumn emailColunm;
    @FXML public TextField searchTextField;
    @FXML public AnchorPane anchorpane;
    public Label nameLabel;
    
    private CrewMember crewMember;
    ArrayList<CrewProduction> crewProductions;
    ObservableList<CrewMember> crewMem = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        crewMember = AddCrewMemberController.getSelectedCrewMember();
        nameLabel.setText("Name: " + crewMember.getName());
        crewProductions = CreditSystem.getCreditSystem().getPersonalRecord(crewMember.getId());

        productionColumn.setCellValueFactory(new PropertyValueFactory<CrewProduction, String>("productionTitle"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<CrewProduction, String>("role"));

        updateTableView(crewMember.getId());

        tableViewCrewMembers.setEditable(false);
        productionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewCrewMembers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    public void searchbtn(ActionEvent actionEvent) {
        search();
    }

    public ObservableList<CrewProduction> getCrewProduction(ArrayList<CrewProduction> fetch){
        ObservableList<CrewProduction> cast = FXCollections.observableArrayList();
        for (CrewProduction c : fetch) {
            CrewMember crewMember = c.getCrewMember();
            Production production = c.getProduction();
            String role = c.getRole();
            cast.add(new CrewProduction(crewMember, production, role));
        }
        return cast;
    }

    public void exportdataButtonAction(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("csv", "*.csv"),
                new FileChooser.ExtensionFilter("doc", "*.doc"));
        try {
            File file = fileChooser.showSaveDialog(null);
            fileChooser.setInitialDirectory(file.getParentFile());
            Writer writer = new BufferedWriter(new FileWriter(file));
            writer.write(crewMember.getName() + "\n\nRoles:\n");
            for (CrewProduction crewMember : crewProductions) {
                String text = crewMember.getProductionTitle() + ",  " + crewMember.getRole()  + "\n";
                writer.write(text);
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backbtn(ActionEvent actionEvent) throws IOException {
            App.setRoot("AddCrewMember");
    }

    public void search() {
        if (searchTextField.textProperty().get().isEmpty()) {
            updateTableView(0);
        }
        ObservableList<CrewProduction> tableData = FXCollections.observableArrayList();
        ObservableList<TableColumn<CrewProduction, ?>> tableColumns = tableViewCrewMembers.getColumns();
        for (int i = 0; i < crewMem.size(); i++) {
            for (int j = 0; j < tableColumns.size(); j++) {
                TableColumn tableColumn = tableColumns.get(j);
                String cellValue = tableColumn.getCellData(crewMem.get(i)).toString();
                cellValue = cellValue.toLowerCase();
                if (cellValue.contains(searchTextField.textProperty().get().toLowerCase())) {
                    tableData.add(crewProductions.get(i));
                    break;
                }
            }
        }
        tableViewCrewMembers.setItems(tableData);
    }

    public void searchEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }

    public void updateTableView(int id) {
        ArrayList<CrewProduction> record = CreditSystem.getCreditSystem().getPersonalRecord(id);
        tableViewCrewMembers.setItems(getCrewProduction(record));
    }
}
