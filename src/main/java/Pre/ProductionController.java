package Pre;


import Domain.CreditSystem;
import Domain.Production;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
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

    ArrayList<Production> productions = CreditSystem.getCreditSystem().getProductionList();
    ObservableList<Production> productionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Production, String>("title"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Production, String>("owner"));
        productionColunm.setCellValueFactory(new PropertyValueFactory<Production, Integer>("productionId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Production, Date>("date"));
        updateTableView();

        //Edit the table data:
        tableviewProduction.setEditable(true);
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ownerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableviewProduction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addButtonOnAction(ActionEvent actionEvent) throws SQLException {
        int productionId = CreditSystem.getCreditSystem().getProductionIdFromDatabse();
        Date date = CreditSystem.getCreditSystem().getProductionDateFromDatabase();
        CreditSystem.getCreditSystem().addProduction(titleField.getText(), ownerField.getText(), date, productionId);
        updateTableView();
        titleField.clear();
        ownerField.clear();
    }

    public void DeleteButtonOnAction(ActionEvent actionEvent) {
        ObservableList<Production> selectedProduction = tableviewProduction.getSelectionModel().getSelectedItems();
        Production temp = tableviewProduction.getSelectionModel().getSelectedItem();

        if(temp != null) {
            CreditSystem.getCreditSystem().removeProductionFromSystem(temp.getProductionId());
        } else {
            System.out.println("Nothing selected");
        }

        if (selectedProduction != null) {
            ArrayList<Production> rows = new ArrayList<>(selectedProduction);
            rows.forEach(row -> tableviewProduction.getItems().remove(row));
        }
    }

    public void openButtonOnAction(ActionEvent actionEvent) throws IOException {
//        productionList = tableviewProduction.getSelectionModel().getSelectedItems();
//        SelectedProductionController controller = new SelectedProductionController();
//
//        controller.titleFieldSelect.setText(titleField.getSelectedText());
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedProduction"));
//        SelectedProductionController controller = loader.getController();
//        controller.titleFieldSelect;
//        productionList = tableviewProduction.getSelectionModel().getSelectedItems();
//        System.out.println(productionList.toString());
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
            System.out.println("Element not found");
        }
    }

    public void updateOwner(TableColumn.CellEditEvent<Production, String> productionStringCellEditEvent) {
        Production tempProduction = tableviewProduction.getSelectionModel().getSelectedItem();
        String newOwner = productionStringCellEditEvent.getNewValue();
        if (tempProduction != null) {
            CreditSystem.getCreditSystem().updateProduction(tempProduction.getTitle(), newOwner, tempProduction.getDate(), tempProduction.getProductionId());
            updateTableView();
        } else {
            System.out.println("Element not found");
        }
    }
}
