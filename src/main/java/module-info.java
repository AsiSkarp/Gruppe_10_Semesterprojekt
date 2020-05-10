module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;
    requires controlsfx;

    opens Domain to javafx.fxml;
    exports Pre;
    opens Pre to javafx.fxml;
    exports Domain;
}