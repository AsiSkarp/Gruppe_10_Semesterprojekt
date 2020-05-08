package Pre;


import Domain.CreditSystem;
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

//CALL THE STATEMENT UNDER THIS SENTENCE FIRST TIME YOU RUN THE PROGRAM. COMMENT IT OUT IF YOU'RE GONNA RESTART THE PROGRAM
        CreditSystem.getCreditSystem().addSuperAdmin("SysAdm", "system", "admin");

        //Launch GUI
        launch(args);
    }
}
