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
    @FXML public TextField nameCM;
    @FXML public TextField roleCM;
    @FXML public TableView<CrewProduction> tableCM;
    @FXML public TableColumn<CrewProduction, String>  nameColumn;
    @FXML public TableColumn<CrewProduction, String>  roleColumn;
    public Label titleLabel;
    public Label ownerLable;
    public Label dateLabel;
    public TextField nameField;
    public TextField emailField;
    public TextField roleField;
    public Button addBtn;
    public Button deleteBtn;
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

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        if(!CreditSystem.getCreditSystem().getCurrentUser().getName().equals(production.getOwner()) &&
                !CreditSystem.getCreditSystem().getCurrentUser().getIsAdmin()) {
            nameField.setVisible(false);
            emailField.setVisible(false);
            roleField.setVisible(false);
            addBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }

        updateTableView(production.getProductionId());

        //Edit the table data:
        tableCM.setEditable(false);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCM.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addCM(ActionEvent actionEvent) {
        if(nameField.getText().isEmpty() || emailField.getText().isEmpty() || roleField.getText().isEmpty()) {
            System.out.println("you must enter values");
        } else {
            CreditSystem.getCreditSystem().addCrewToProduction(nameField.getText(), emailField.getText(), roleField.getText(), production.getProductionId());
            System.out.println("added to database");
        }
        updateTableView(production.getProductionId());
        nameField.clear();
        emailField.clear();
        roleField.clear();
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
