module com.application.weatherapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.application.weatherapplication to javafx.fxml;
    exports com.application.weatherapplication;
}