module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens Domain to javafx.fxml;
    exports org.example
            ;
}