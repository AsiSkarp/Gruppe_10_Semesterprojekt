package Pre;


import Domain.CreditSystem;
import Domain.Login;
import Persistance.CreditSystemFileIO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class
App extends Application {

    private static Scene scene;
    private static String currentRoom;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static String getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(String currentRoom) {
        App.currentRoom = currentRoom;
    }

    public static void main(String[] args) {

//        CreditSystem.getCreditSystem().addProducerToSystem("John", "hello", "78");
//        CreditSystem.getCreditSystem().addCrewMember("John","kevin@kevin", 94464);
//        CreditSystem.getCreditSystem().addCrewMember("n","kekevin", 1231);
//        CreditSystem.getCreditSystem().addCrewMember("awdn","kekevin", 1231);
//        CreditSystem.getCreditSystem().addCrewMember("nawd","kekevin", 1231);
//        CreditSystem.getCreditSystem().addAdminToSystem("Popopop", "asssaaassa","123456");
//        CreditSystem.getCreditSystem().addProductionToSystem("Bagedysten", 78);
          CreditSystem.getCreditSystem().readFromPersistance();
//        CreditSystem.getCreditSystem().writeToPersistance();
        //Login.getLogin().login("asssaaassa", "123456");

        System.out.println(CreditSystem.getCreditSystem().getCrewMemberList());

        //Launch GUI
        launch(args);
    }
}
