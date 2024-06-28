module com.projet {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.projet to javafx.fxml;
    exports com.projet;
}
