module htl.steyr.scheresteinpapier {
    requires javafx.controls;
    requires javafx.fxml;


    opens htl.steyr.scheresteinpapier to javafx.fxml;
    exports htl.steyr.scheresteinpapier;
}