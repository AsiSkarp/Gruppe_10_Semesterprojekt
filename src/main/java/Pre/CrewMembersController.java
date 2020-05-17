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

import java.io.IOException;
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

    private Connection connection = DatabaseConn.getConnection();
    private CrewMember crewMember;
    ObservableList<CrewMember> crewMem = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        crewMember = AddCrewMemberController.getSelectedCrewMember();
        nameLabel.setText("Name: " + crewMember.getName());

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
            cast.add(new CrewProduction(crewMember, production, role, crewMember.getName()));
        }
        return cast;
    }

    public void exportdataButtonAction(ActionEvent actionEvent) throws IOException {
//
//        FileChooser fileChooser = new FileChooser();
//        Window stage = anchorpane.getScene().getWindow();
//        fileChooser.setTitle("Save Dialog");
//        fileChooser.setInitialFileName("Crew Member List");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("text file", "*.txt"),
//                new FileChooser.ExtensionFilter("csv", "*.csv"),
//                new FileChooser.ExtensionFilter("doc", "*.doc"),
//                new FileChooser.ExtensionFilter("xml", "*.xml"),
//                new FileChooser.ExtensionFilter("xls", "*.xls"));
//        try {
//            File file = fileChooser.showSaveDialog(stage);
//            fileChooser.setInitialDirectory(file.getParentFile());
//            Writer writer = new BufferedWriter(new FileWriter(file));
//            for (CrewMember crewMember : crewMem) {
//                String text = crewMember.getName() + ",  " + crewMember.getRole() + ",  " + crewMember.getEmail() + "\n";
//                writer.write(text);
//            } writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public void backbtn(ActionEvent actionEvent) throws IOException {
        if (CreditSystem.getCreditSystem().getCurrentUser() != null) {
            App.setRoot("AddCrewMember");
        } else {
            App.setRoot("GuestAndRD");
        }

    }

    public void search() {
//        if (searchTextField.textProperty().get().isEmpty()) {
//            updateTableView();
//        }
//        ObservableList<CrewMember> tableData = FXCollections.observableArrayList();
//        ObservableList<TableColumn<CrewMember, ?>> tableColumns = tableviewCrewmembers.getColumns();
//        for (int i = 0; i < crewMem.size(); i++) {
//            for (int j = 0; j < tableColumns.size(); j++) {
//                TableColumn tableColumn = tableColumns.get(j);
//                String cellValue = tableColumn.getCellData(crewMem.get(i)).toString();
//                cellValue = cellValue.toLowerCase();
//                if (cellValue.contains(searchTextField.textProperty().get().toLowerCase())) {
//                    tableData.add(crewMem.get(i));
//                    break;
//                }
//            }
//        }
//        tableviewCrewmembers.setItems(tableData);
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
