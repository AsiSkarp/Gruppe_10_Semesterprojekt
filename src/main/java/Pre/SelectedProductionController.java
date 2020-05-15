package Pre;

import Domain.CrewMember;
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
import java.util.Date;
import java.util.ResourceBundle;

public class SelectedProductionController implements Initializable {

    @FXML public TextField nameCM;
    @FXML public TextField roleCM;
    @FXML public TableView<CrewMember> tableCM;
    @FXML public TableColumn<CrewMember, String>  nameColumn;
    @FXML public TableColumn<CrewMember, String>  roleColumn;
    @FXML public TextField dateFieldSelect;

    private Date date;


    //ArrayList<CrewMember> fetchList = CreditSystem.getCreditSystem().getCrewMemberList();
    ObservableList<CrewMember> crewM = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateFieldSelect.setText(String.valueOf(date));

        nameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String >("role"));

       // tableCM.setItems((ObservableList<CrewMember>) fetchList);
        tableCM.setItems(crewM);

        //Edit the table data:
        tableCM.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCM.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addCM(ActionEvent actionEvent) {

    }

    public void deleteCM(ActionEvent actionEvent) {
    }



    public void eksportereCM(ActionEvent actionEvent) {
    }

    public void backCM(ActionEvent actionEvent) throws IOException {
        App.setRoot("Production");
    }
}
