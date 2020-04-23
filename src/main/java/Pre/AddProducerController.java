package Pre;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AddProducerController {
    public void goBackBtnHandler(ActionEvent actionEvent) throws IOException {
        App.setRoot(App.getCurrentRoom());
    }
}
