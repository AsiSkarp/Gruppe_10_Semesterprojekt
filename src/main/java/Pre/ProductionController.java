package Pre;


import Domain.CreditSystem;
import Domain.CrewMember;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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


    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    ObservableList<Production> productionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Production, String>("title"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Production, String>("owner"));
        productionColunm.setCellValueFactory(new PropertyValueFactory<Production, Integer>("productionId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Production, Date>("date"));
        updateTableView();
        if (CreditSystem.getCreditSystem().getCurrentUser().getIsSuperAdmin()) {
            ownerField.setVisible(true);
        }

        //Edit the table data:
        tableviewProduction.setEditable(true);
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ownerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //dateColumn.setCellFactory();
        tableviewProduction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addButtonOnAction(ActionEvent actionEvent) throws SQLException {
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
        productionList = tableviewProduction.getSelectionModel().getSelectedItems();

        productionList.get(0).getTitle();
        productionList.get(0).getOwner();
        productionList.get(0).getDate();
        System.out.println(productionList.toString());

        App.setRoot("SelectedProduction");
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
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
        }
        ObservableList<Production> tableData = FXCollections.observableArrayList();
        ObservableList<TableColumn<Production, ?>> tableColumns = tableviewProduction.getColumns();
        for (int i = 0; i < productionList.size(); i++) {
            for (int j = 0; j < tableColumns.size(); j++) {
                TableColumn tableColumn = tableColumns.get(j);
                String cellValue = tableColumn.getCellData(productionList.get(i)).toString();
                cellValue = cellValue.toLowerCase();
                if (cellValue.contains(searchField.textProperty().get().toLowerCase())) {
                    tableData.add(productionList.get(i));
                }
            }
        }
        tableviewProduction.setItems(tableData);
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
//            Java.sql.Date Date = c.getDate()
            productions.add(new Production(title, owner, date, productionId));
        }
        return productions;
    }

    public void updateTitle(TableColumn.CellEditEvent<Production, String> productionStringCellEditEvent) {
        Production tempProduction = tableviewProduction.getSelectionModel().getSelectedItem();
        String newTitle = productionStringCellEditEvent.getNewValue();
        if (tempProduction != null) {
            CreditSystem.getCreditSystem().updateProduction(newTitle, tempProduction.getOwner(), tempProduction.getDate(), tempProduction.getProductionId());
            updateTableView();
        } else {
            resultLabel.setText("Element not found");
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
}
