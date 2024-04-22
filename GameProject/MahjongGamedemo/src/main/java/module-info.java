module com.example.mahjonggamedemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.mahjonggamedemo to javafx.fxml;
    exports com.example.mahjonggamedemo;
}