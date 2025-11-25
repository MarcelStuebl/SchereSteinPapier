package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.InputStream;


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

    private Image loadImage(String filename) {
        String path = "/htl/steyr/scheresteinpapier/img/" + filename;
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            System.err.println("Resource not found: " + path);
            return null;
        }
        return new Image(stream);
    }

    public void showSelectedGesture(Gesture gesture){
        switch(gesture.getGesture()){
            case 0:
                playerShowGesture.setImage(loadImage("Schere.png"));
                break;
            case 1:
                playerShowGesture.setImage(loadImage("Stein.png"));
                break;
            case 2:
                playerShowGesture.setImage(loadImage("Papier.png"));
                break;
            default:
                playerShowGesture.setImage(null);
        }
    }

    public void showBotGesture(Gesture gesture){
        switch(gesture.getGesture()){
            case 0:
                botShowGesture.setImage(loadImage("Schere.png"));
                break;
            case 1:
                botShowGesture.setImage(loadImage("Stein.png"));
                break;
            case 2:
                botShowGesture.setImage(loadImage("Papier.png"));
                break;
            default:
                botShowGesture.setImage(null);
        }
    }


    public void gestureSelected() {

    }




}




