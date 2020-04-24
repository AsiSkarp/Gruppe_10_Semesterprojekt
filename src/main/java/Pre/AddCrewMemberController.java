package Pre;

import Domain.CreditSystem;
import Domain.CrewMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class AddCrewMemberController {

    @FXML
    public TextField searchCrewM;
    @FXML
    public TableView<CrewMember> crewMemberTable;
    @FXML
    public TableColumn<CrewMember, String> nameColumn;
    @FXML
    public TableColumn<CrewMember, String> emailColumn;
    @FXML
    public TableColumn<CrewMember, Integer> idColumn;


    //ProducerA c = new ProducerA("john", "kevin@kevin", 01);


    ArrayList<ArrayList> fetchList = CreditSystem.getCreditSystem().readFromPersistance();

    public void addBtnHandler(ActionEvent actionEvent) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("email"));
        idColumn.setCellValueFactory(new PropertyValueFactory<CrewMember, Integer>("castCrewId"));

        //crewMemberTable.setItems(getProducerA());
        //System.out.println(fetchList);

        //crewMemberTable.setItems(getCrewMembers());
        //getCrewMembers();
        crewMemberTable.getItems().add(new CrewMember("john","doe", 01));
    }

    //crew.addAll(fetchCrew);

    public ObservableList<CrewMember> getCrewMembers() {
        ObservableList<CrewMember> crew = FXCollections.observableArrayList();
       ArrayList<CrewMember> fetchCrew = fetchList.get(2);
//        //System.out.println(fetchCrew);
        for (CrewMember c : fetchCrew) {
            String name = c.getName();
            String email = c.getEmail();
            int id = c.getCastCrewId();
        //crew.add(new CrewMember(name,email,id));
            System.out.println(name + "  " + email + "  " + id);
        }
        return crew;
    }
}