module com.example.objectaid_sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.objectaid_sae to javafx.fxml;
    exports com.example.objectaid_sae;
}