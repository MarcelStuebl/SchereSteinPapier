package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


public class GameController {
    public Button schereButton;
    public Button steinButton;
    public Button papierButton;

    public Player player = new Player();
    public Player bot = new Player();


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
        /*@TODO
        Bitte Ausprogrammieren:
        Soll alle Buttons verstecken
         */
    }

    public void showButtons(){
        /*@TODO
        Bitte Ausprogrammieren:
        Soll alle Buttons anzeigen
         */
    }

    public void showSelectedGesture(Gesture gesture){
        /*@TODO
        Bitte Ausprogrammieren:
        Soll die ausgewählte Gesture links groß anzeigen.
         */
    }

    public void showBotGesture(Gesture gesture){
        /*@TODO
        Bitte Ausprogrammieren:
        Soll die Gesture vom Bot rechts groß anzeigen.
         */
    }


    public void gestureSelected() {

    }




}




