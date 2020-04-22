package Pre;

import Domain.CreditSystem;
import Domain.Producer;
import Domain.SuperAdmin;
import Domain.User;
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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        CreditSystem creditSystem = new CreditSystem();
        /*creditSystem.addProducerToSystem("Casper", "casper@kevin", "123123");
        creditSystem.readFromFile(); */

        //Login System Launches here....
        User user1 = new SuperAdmin("Bingi", "Slyngi", "Sjøræningi");
        User user2 = new Producer("Rassi", "Slyngi", "Sjøræningi");
//
        creditSystem.addAdminToSystem("jauns","blarg","sicko", user2);
        creditSystem.addAdminToSystem("55","asdda","ggagg", user2);


        //Launch GUI
        launch();

        }

}