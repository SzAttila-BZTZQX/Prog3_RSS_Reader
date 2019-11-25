module RSS.Reader {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;

    exports sample.resources.views;
    opens sample;
}