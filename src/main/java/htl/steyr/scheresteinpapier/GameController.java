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




