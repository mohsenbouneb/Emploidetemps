module com.example.miniprojet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.miniprojet to javafx.fxml;
    exports com.example.miniprojet;
}