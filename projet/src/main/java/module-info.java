module com.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.projet to javafx.fxml;

    exports com.projet;
}
