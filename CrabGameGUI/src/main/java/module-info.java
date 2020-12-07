module org.CrabGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens org.CrabGame to javafx.fxml;
    exports org.CrabGame;
}