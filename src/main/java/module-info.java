module org.openjfx.GoldenStudio {
    requires javafx.controls;
    requires javafx.fxml;

    exports entities;

    opens org.openjfx.GoldenStudio to javafx.fxml;
    exports org.openjfx.GoldenStudio;
    requires java.sql;
    requires javafx.web;
}