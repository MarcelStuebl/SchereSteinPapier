package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.InputStream;


public class GameController {
    public Button schereButton;
    public Button steinButton;
    public Button papierButton;
    public ImageView playerShowGesture;
    public ImageView botShowGesture;
    public Button resetButton;
    public ProgressBar botProgressBar;

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
    public void resetButtonPressed(ActionEvent actionEvent) {
        playerShowGesture.setVisible(false);
        botShowGesture.setVisible(false);
        showButtons();
        resetButton.setVisible(false);
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
        playerShowGesture.setVisible(true);
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
        botShowGesture.setVisible(true);
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

    public void playerWin(){
        /*@TODO
        Bitte Ausprogrammieren:
        Ereignisse, die passieren, wenn der Player gewinnt.
         */
    }

    public void botWin(){
        /*@TODO
        Bitte Ausprogrammieren:
        Ereignisse, die passieren, wenn der Bot gewinnt.
         */
    }

    public void drawWin(){
        /*@TODO
        Bitte Ausprogrammieren:
        Ereignisse, die passieren, wenn Unentschieden.
         */
    }


    public void gestureSelected() {
        hideButtons();
        showSelectedGesture(player.getSelectedGesture());
        bot.setRandomGesture();
        revealWinner();
    }

    public void revealWinner(){
        showBotGesture(bot.getSelectedGesture());
        if (getWinner() == player) {
            playerWin();
        } else if (getWinner() == bot) {
            botWin();
        } else {
            drawWin();
        }
        resetButton.setVisible(true);
    }

    public Player getWinner() {
        if (player.getSelectedGesture().getID() == 1 && bot.getSelectedGesture().getID() == 2) {
            // Schere vs Stein.
            // Bot gewinnt.
            return bot;
        }  else if (player.getSelectedGesture().getID() == 2 && bot.getSelectedGesture().getID() == 1) {
            // Stein vs Schere.
            // Player gewinnt.
            return player;
        } else if (player.getSelectedGesture().getID() == 1 && bot.getSelectedGesture().getID() == 3) {
            // Schere vs Papier.
            // Player gewinnt.
            return player;
        } else if (bot.getSelectedGesture().getID() == 1 && player.getSelectedGesture().getID() == 3) {
            // Papier vs Schere.
            // Bot gewinnt.
            return bot;
        } else if (player.getSelectedGesture().getID() == 2 && bot.getSelectedGesture().getID() == 3) {
            // Stein vs Papier.
            // Bot gewinnt.
            return bot;
        } else if (bot.getSelectedGesture().getID() == 2 && player.getSelectedGesture().getID() == 3) {
            // Papier vs Stein.
            // Player gewinnt.
            return player;
        }
        // Unentschieden
        return null;
    }


}




