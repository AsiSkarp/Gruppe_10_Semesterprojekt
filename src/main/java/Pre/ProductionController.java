package Pre;

import Domain.CreditSystem;
import Domain.Production;
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
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ProductionController implements Initializable {

    //for Table Column and add Class like: TableView<Class, type>;
    @FXML public TableView<Production> tableviewProduction;
    @FXML public TableColumn<Production, String > titleColumn;
    @FXML public TableColumn<Production, String> ownerColumn;
    public TableColumn<Production, Integer> productionColunm;
    @FXML public TableColumn<Production, Date> dateColumn;

    @FXML public TextField searchField;
    @FXML public TextField titleField;
    @FXML public TextField ownerField;
    public Label resultLabel;
    public Button addBtn;
    public Button deleteBtn;

    ArrayList<Production> productions = CreditSystem.getCreditSystem().getProductionList();
    ObservableList<Production> productionList = FXCollections.observableArrayList();
    private static Production selectedProduction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Production, String>("title"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Production, String>("owner"));
        productionColunm.setCellValueFactory(new PropertyValueFactory<Production, Integer>("productionId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Production, Date>("date"));
        updateTableView();
        if (CreditSystem.getCreditSystem().getCurrentUser() != null) {
            if (CreditSystem.getCreditSystem().getCurrentUser().getIsSuperAdmin()) {
                ownerField.setVisible(true);
            }
        } else {
            titleField.setVisible(false);
            addBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }


        //Edit the table data:
        tableviewProduction.setEditable(true);
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ownerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableviewProduction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addButtonOnAction(ActionEvent actionEvent) throws SQLException {
        smartAddProduction();
    }

    public void smartAddProduction() throws SQLException {
        int productionId = CreditSystem.getCreditSystem().getProductionIdFromDatabse();
        Date date = CreditSystem.getCreditSystem().getProductionDateFromDatabase();
        String owner = CreditSystem.getCreditSystem().getCurrentUser().getName();
        if (!titleField.getText().isEmpty()) {
            if (!CreditSystem.getCreditSystem().getCurrentUser().getIsAdmin()) {
                CreditSystem.getCreditSystem().addProduction(titleField.getText(), owner, date, productionId);
            } else {
                CreditSystem.getCreditSystem().addProduction(titleField.getText(), ownerField.getText(), date, productionId);
            }
        } else {
            resultLabel.setText("You must enter a title.");
        }
        updateTableView();
        titleField.clear();
        ownerField.clear();
    }

    public void productionEnter(KeyEvent keyEvent) throws SQLException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            smartAddProduction();
        }
    }

    public void DeleteButtonOnAction(ActionEvent actionEvent) {
        ObservableList<Production> selectedProduction = tableviewProduction.getSelectionModel().getSelectedItems();
        Production temp = tableviewProduction.getSelectionModel().getSelectedItem();

        if (temp.getOwner().equals(CreditSystem.getCreditSystem().getCurrentUser().getName()) || CreditSystem.getCreditSystem().getCurrentUser().getIsAdmin()) {
            if(temp != null) {
                CreditSystem.getCreditSystem().removeProductionFromSystem(temp.getProductionId());
            } else {
                resultLabel.setText("Nothing selected");
            }
            if (selectedProduction != null) {
                ArrayList<Production> rows = new ArrayList<>(selectedProduction);
                rows.forEach(row -> tableviewProduction.getItems().remove(row));
            }
        } else {
            resultLabel.setText("You do not own this production");
        }

    }

    public void openButtonOnAction(ActionEvent actionEvent) throws IOException {
        selectedProduction = tableviewProduction.getSelectionModel().getSelectedItem();
        App.setRoot("SelectedProduction");
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        if (CreditSystem.getCreditSystem().getCurrentUser() != null) {
            App.setRoot(App.getCurrentRoom());
        } else {
            App.setRoot("GuestAndRD");
        }
    }

    public void searchButton(ActionEvent actionEvent) {
        search();
    }

    public void searchEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }

    public void search() {
        if (searchField.textProperty().get().isEmpty()) {
            updateTableView();
        } else {
            ObservableList<Production> tableData = FXCollections.observableArrayList();
            ObservableList<TableColumn<Production, ?>> tableColumns = tableviewProduction.getColumns();
            for (int i = 0; i < productions.size(); i++) {
                for (int j = 0; j < tableColumns.size(); j++) {
                    TableColumn tableColumn = tableColumns.get(j);
                    String cellValue = tableColumn.getCellData(productions.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(searchField.textProperty().get().toLowerCase())) {
                        tableData.add(productions.get(i));
                        break;
                    }
                }
            }
            tableviewProduction.setItems(tableData);
        }
    }

    public void updateTableView() {
        ArrayList<Production> productionList = CreditSystem.getCreditSystem().getProductionList();
        tableviewProduction.setItems(getProduction(productionList));
    }

    public ObservableList<Production> getProduction(ArrayList<Production> fetch) {
        ObservableList<Production> productions = FXCollections.observableArrayList();
        ArrayList<Production> fetchCrew = fetch;
        for (Production c : fetchCrew) {
            String title = c.getTitle();
            String owner = c.getOwner();
            Date date = c.getDate();
            int productionId = c.getProductionId();
            productions.add(new Production(title, owner, date, productionId));
        }
        return productions;
    }

    public void updateTitle(TableColumn.CellEditEvent<Production, String> productionStringCellEditEvent) {
        Production tempProduction = tableviewProduction.getSelectionModel().getSelectedItem();
        String newTitle = productionStringCellEditEvent.getNewValue();
        if (tempProduction.getOwner().equals(CreditSystem.getCreditSystem().getCurrentUser().getName())) {
            if (tempProduction != null) {
                CreditSystem.getCreditSystem().updateProduction(newTitle, tempProduction.getOwner(), tempProduction.getDate(), tempProduction.getProductionId());
                updateTableView();
            } else {
                resultLabel.setText("Element not found");
            }
        } else {
            updateTableView();
            resultLabel.setText("You don't own this production");
        }

    }

    public void updateOwner(TableColumn.CellEditEvent<Production, String> productionStringCellEditEvent) {
        Production tempProduction = tableviewProduction.getSelectionModel().getSelectedItem();
        String newOwner = productionStringCellEditEvent.getNewValue();
        if (tempProduction != null) {
            CreditSystem.getCreditSystem().updateProduction(tempProduction.getTitle(), newOwner, tempProduction.getDate(), tempProduction.getProductionId());
            updateTableView();
        } else {
            resultLabel.setText("Element not found");
        }
    }

    public void exportProduction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Production List");
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
            for (Production production : productions) {
                String text = production.getTitle() + ", " + production.getOwner() + ", " + production.getProductionId() + ", " + production.getDate() + "\n";
                writer.write(text);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Production getSelectedProduction() {
        return selectedProduction;
    }
}
