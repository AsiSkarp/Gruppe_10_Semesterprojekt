package Pre;

import Domain.CreditSystem;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrewMembersController implements Initializable {
    //tableview Colunms:
    @FXML public TableView tableviewCrewmembers;
    @FXML public TableColumn nameColumn;
    @FXML public TableColumn roleColumn;
    @FXML public TableColumn emailColunm;
    @FXML public TextField searchTextField;

    ObservableList<CrewMember> crewMem = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String >("role"));
        emailColunm.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        tableviewCrewmembers.setItems(crewMem);


    }



    public void searchbtn(ActionEvent actionEvent) {
    }

    public void exportdataButtonAction(ActionEvent actionEvent) {

    }

    public void backbtn(ActionEvent actionEvent) throws IOException {
        App.setRoot("GuestAndRD");
    }



}
