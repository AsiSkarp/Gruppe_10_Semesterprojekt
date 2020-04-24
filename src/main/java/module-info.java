module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens Domain to javafx.fxml;
    exports Pre;
    opens Pre to javafx.fxml;
    exports Domain;
}