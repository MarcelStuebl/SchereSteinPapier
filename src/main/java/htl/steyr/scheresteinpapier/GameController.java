package htl.steyr.scheresteinpapier;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public Button reviewButton;
    public Button schereButton;
    public Button steinButton;
    public Button papierButton;

    public Player player = new Player();
    public Player bot = new Player();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void reviwButtonPressed(ActionEvent actionEvent) {
    }

    public void schereButtonPressed(ActionEvent actionEvent) {
        player.setSelectedGesture(0);
    }

    public void steinButtonPressed(ActionEvent actionEvent) {
    }

    public void papierButtonPressed(ActionEvent actionEvent) {
    }




}
