package Pre;

import Domain.CreditSystem;
import Domain.CrewMember;
import Domain.CrewProduction;
import Domain.Production;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class SelectedProductionController implements Initializable {

    @FXML public TextArea titleArea;
    @FXML public TextArea ownerArea;
    @FXML public TextArea dateArea;
    @FXML public TextField nameField;
    @FXML public TextField roleField;
    @FXML public TableView<CrewProduction> tableCM;
    @FXML public TableColumn<CrewProduction, String>  nameColumn;
    @FXML public TableColumn<CrewProduction, String>  roleColumn;
    @FXML public Label titleLabel;
    @FXML public Label ownerLable;
    @FXML public Label dateLabel;
    @FXML public Button deleteCMBT;
    @FXML public Button addCMBT;
    private Date date;


    //ArrayList<CrewMember> fetchList = CreditSystem.getCreditSystem().getCrewMemberList();
    ObservableList<CrewMember> crewM = FXCollections.observableArrayList();
    private Production production;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        production = ProductionController.getSelectedProduction();
        titleLabel.setText("Title:\n" + production.getTitle());
        ownerLable.setText("Owner:\n" + production.getOwner());
        dateLabel.setText("Date:\n" + production.getDate());

        nameColumn.setCellValueFactory(new PropertyValueFactory<CrewProduction, String>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<CrewProduction, String>("role"));
        updateTableView(production.getProductionId());

        if (CreditSystem.getCreditSystem().getCurrentUser() != null) {
            if (CreditSystem.getCreditSystem().getCurrentUser().getIsSuperAdmin()
            || CreditSystem.getCreditSystem().getCurrentUser().getIsAdmin()
            || CreditSystem.getCreditSystem().getCurrentUser().getIsProducer()) {
                nameField.setVisible(true);
                roleField.setVisible(true);

                deleteCMBT.setVisible(true);
                addCMBT.setVisible(true);

                //Edit the table data:
                tableCM.setEditable(true);
                nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                tableCM.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }
        } else {
            nameField.setVisible(false);
            roleField.setVisible(false);
            deleteCMBT.setVisible(false);
            addCMBT.setVisible(false);
        }
    }

    public void addCM(ActionEvent actionEvent) {

    }

    public void deleteCM(ActionEvent actionEvent) {
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

    public void updateTableView(int id) {
        ArrayList<CrewProduction> cast = CreditSystem.getCreditSystem().getCrewProduction(id);
        tableCM.setItems(getCrewProduction(cast));
    }

    public void eksportereCM(ActionEvent actionEvent) {
    }

    public void backCM(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }
}
