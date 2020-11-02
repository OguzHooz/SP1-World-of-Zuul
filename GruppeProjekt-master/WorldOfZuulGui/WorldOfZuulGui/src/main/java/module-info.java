module com.mycompany.worldofzuulgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.worldofzuulgui to javafx.fxml;
    exports com.mycompany.worldofzuulgui;
}