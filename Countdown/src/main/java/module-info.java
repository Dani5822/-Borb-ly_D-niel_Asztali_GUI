module hu.petrik.countdown {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;


    opens hu.petrik.countdown to javafx.fxml;
    exports hu.petrik.countdown;
}