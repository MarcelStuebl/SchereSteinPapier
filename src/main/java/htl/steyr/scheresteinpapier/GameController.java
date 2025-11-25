package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


public class GameController {
    @FXML
    public Button schereButton;
    @FXML
    public Button steinButton;
    @FXML
    public Button papierButton;

    public Player player = new Player();
    public Player bot = new Player();
    public ImageView playerShowGesture;
    public ImageView botShowGesture;
    public Button resetButton;


    public void schereButtonPressed(ActionEvent actionEvent) {
        player.setSelectedGesture(0);
        gestureSelected();
    }
    public void steinButtonPressed(ActionEvent actionEvent) {
        player.setSelectedGesture(1);
        gestureSelected();
    }
    public void papierButtonPressed(ActionEvent actionEvent) {
        player.setSelectedGesture(2);
        gestureSelected();
    }

    public void hideButtons(){
        /*
        Bitte Ausprogrammieren:
        Soll alle Buttons verstecken
         */
        schereButton.setVisible(false);
        steinButton.setVisible(false);
        papierButton.setVisible(false);
    }

    public void showButtons(){
        /*@TODO
        Bitte Ausprogrammieren:
        Soll alle Buttons anzeigen
         */
        schereButton.setVisible(true);
        steinButton.setVisible(true);
        papierButton.setVisible(true);
    }

    public void showSelectedGesture(Gesture gesture){
        /*@TODO
        Bitte Ausprogrammieren:
        Soll die ausgewählte Gesture links groß anzeigen.
         */
        switch(gesture.getGesture()){
            case 0:
                playerShowGesture.setImage(new Image("Schere.png"));
                break;
            case 1:
                playerShowGesture.setImage(new Image("Stein.png"));
                break;
            case 2:
                playerShowGesture.setImage(new Image("Papier.png"));
                break;
        }
    }

    public void showBotGesture(Gesture gesture){
        /*@TODO
        Bitte Ausprogrammieren:
        Soll die Gesture vom Bot rechts groß anzeigen.
         */
        switch(gesture.getGesture()){
            case 0:
                botShowGesture.setImage(new Image("Schere.png"));
                break;
            case 1:
                botShowGesture.setImage(new Image("Stein.png"));
                break;
            case 2:
                botShowGesture.setImage(new Image("Papier.png"));
                break;
        }
    }


    public void gestureSelected() {

    }




}




