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
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SelectedProductionController implements Initializable {
    @FXML public TableView<CrewMember> tableCM;
    @FXML public TableColumn<CrewMember, String>  nameColumn;
    @FXML public TableColumn<CrewMember, String>  roleColumn;
    @FXML public Label titleLabel;
    @FXML public Label ownerLable;
    @FXML public Label dateLabel;
    @FXML public TextField emailField;
    @FXML public TextField nameField;
    @FXML public TextField roleField;
    @FXML public Label resultLabel;


    ArrayList<CrewMember> crewM = CreditSystem.getCreditSystem().getCrewMembers();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        //roleColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("role"));

       // tableCM.setItems((ObservableList<CrewMember>) fetchList);
        updateTableView();

        //Edit the table data:
        tableCM.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCM.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addCM(ActionEvent actionEvent) throws SQLException {
        int crewMemId = CreditSystem.getCreditSystem().getCrewMemIdFromDatabase();
        CreditSystem.getCreditSystem().addCrewMember(nameField.getText(), emailField.getText(), crewMemId);
        resultLabel.setText("The information has been added to the Database");
        updateTableView();
        nameField.clear();
        emailField.clear();
    }
    public void updateName(TableColumn.CellEditEvent<CrewMember, String> crewMemberStringCellEditEvent) {
        CrewMember tempCrew = tableCM.getSelectionModel().getSelectedItem();
        String newName = crewMemberStringCellEditEvent.getNewValue();
        if (tempCrew != null) {
            CreditSystem.getCreditSystem().updateCrewMember(newName, tempCrew.getEmail(), tempCrew.getCastCrewId());
            resultLabel.setText("The data is updated in Database");
            updateTableView();
        } else {
            System.out.println("Element not found");
        }
    }
    public void updateTableView() {
        ArrayList<CrewMember> dataList = CreditSystem.getCreditSystem().getCrewMembers();
        tableCM.setItems(getCrewMember(dataList));
    }

    public ObservableList<CrewMember> getCrewMember(ArrayList<CrewMember> fetch) {
        ObservableList<CrewMember> crewMembers = FXCollections.observableArrayList();
        ArrayList<CrewMember> fetchCrew = fetch;
        for (CrewMember c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            int id = c.getCastCrewId();
            crewMembers.add(new CrewMember(name, email, id));
        }
        return crewMembers;
    }

    public void deleteCM(ActionEvent actionEvent) {
        ObservableList<CrewMember> selectedCrew = tableCM.getSelectionModel().getSelectedItems();
        CrewMember tempCrew = tableCM.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure, that you want to remove this Crew member?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user choose OK
            //Delete crew member from list:
            if (tempCrew != null) {
                CreditSystem.getCreditSystem().removeCrewMember(tempCrew.getEmail());
            } else {
                System.out.println("List is empty.");
            }

            if (selectedCrew != null) {
                ArrayList<CrewMember> rows = new ArrayList<>(selectedCrew);
                rows.forEach(row -> tableCM.getItems().remove(row));
                resultLabel.setText("The information is deleted!");
            }
        } else {
            // ... user chose CANCEL or closed the dialog
            System.out.println("user chose CANCEL or closed the dialog");
        }
    }

    public void eksportereCM(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Crew Member List");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("csv", "*.csv"),
                new FileChooser.ExtensionFilter("doc", "*.doc"),
                new FileChooser.ExtensionFilter("xml", "*.xml"),
                new FileChooser.ExtensionFilter("xls", "*.xls"));
        try {
            File file = fileChooser.showSaveDialog(null);

            fileChooser.setInitialDirectory(file.getParentFile());
            Writer writer = new BufferedWriter(new FileWriter(file));
            for (CrewMember crewMember : crewM) {
                String text = crewMember.getName() + ", " + crewMember.getEmail() + "\n";
                writer.write(text);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backCM(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }
}
