package Pre;


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
import java.io.IOException;
import java.net.URL;
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


    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

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
        //dateColumn.setCellFactory();
        tableviewProduction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void DeleteButtonOnAction(ActionEvent actionEvent) {
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
//        //int crewMemId = CreditSystem.getCreditSystem().getCrewMemIdFromDatabase();
//        ArrayList<Production> productions = Production.getProductionSystem().getProductionDatabase();
//        Production.getProductionSystem().addProduction(titleField.getText(), ownerField.getText());
//        //resultLabel.setText("The information has been added to the Database");
//        updateTableView();
    }
    public ObservableList<Production> getProduction(ArrayList<Production> fetch) {
        ObservableList<Production> productions = FXCollections.observableArrayList();
        ArrayList<Production> fetchCrew = fetch;
        for (Production c : fetchCrew) {
            String title = c.getTitle();
            String owner = c.getOwner();
            int productionId = c.getProductionId();
            String date = String.valueOf(c.getDate());
            productions.add(new Production(title, owner, productionId));
        }
        return productions;
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
    }
    public void updateTableView() {
        ArrayList<Production> productionList = Production.getProductionSystem().getProductionDatabase();
        tableviewProduction.setItems(getProduction(productionList));
    }
}
