package htl.steyr.scheresteinpapier;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public Button reviewButton;
    public Button schereLeftButton;
    public Button steinLeftButton1;
    public Button papierLeftButton1;
    public Button schereRightButton1;
    public Button steinRightButton11;
    public Button papierRightButton11;

    public Player pLeft = new Player();
    public Player pRight = new Player();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void reviwOnButtonPressed(ActionEvent actionEvent) {
    }

    public void schereLeftOnButtonPressed(ActionEvent actionEvent) {
        pLeft.setSelectedGesture();
    }

    public void steinLeftOnButtonPressed(ActionEvent actionEvent) {
    }

    public void papierLeftOnButtonPressed(ActionEvent actionEvent) {
    }

    public void schereRightOnButtonPressed(ActionEvent actionEvent) {
    }

    public void steinRightOnButtonPressed(ActionEvent actionEvent) {
    }

    public void papierRightOnButtonPressed(ActionEvent actionEvent) {
    }


}
