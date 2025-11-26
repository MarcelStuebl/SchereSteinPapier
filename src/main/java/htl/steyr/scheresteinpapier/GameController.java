package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.Random;


public class GameController {
    public Button schereButton;
    public Button steinButton;
    public Button papierButton;
    public ImageView playerShowGesture;
    public ImageView botShowGesture;
    public Button resetButton;
    public ProgressBar botProgressBar;

    public Text textBotGesture;
    public Text textPlayerGesture;
    public Text highScoreTextField;
    public Button resetHighScoreButton;
    public Text winnerTextField;

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
        textBotGesture.setVisible(false);
        textPlayerGesture.setVisible(false);
        winnerTextField.setText("");
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
        highScoreTextField.setText(String.valueOf(Integer.parseInt(highScoreTextField.getText()) + 1));
        winnerTextField.setText("You Win!");
    }

    public void botWin(){
        highScoreTextField.setText("0");
        winnerTextField.setText("You Lose!");
    }

    public void drawWin(){
        winnerTextField.setText("Draw!");
    }

    public void progressBarAnimation(int animationDuration) {
        botProgressBar.setVisible(true);
        botProgressBar.setProgress(0);

        new Thread(() -> {

            final int animationSteps = 10;

            for (int i = 1; i <= animationSteps; i++) {
                try {
                    Thread.sleep(animationDuration / animationSteps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double progress = (double) i / animationSteps;
                Platform.runLater(() -> botProgressBar.setProgress(progress));
            }

            Platform.runLater(() -> botProgressBar.setVisible(false));

        }).start();
    }



    public void gestureSelected() {
        textPlayerGesture.setVisible(true);
        textBotGesture.setVisible(true);

        hideButtons();
        showSelectedGesture(player.getSelectedGesture());
        bot.setRandomGesture();
        revealWinner();
    }

    public void revealWinner(){
        Random random = new Random();
        final int animationDuration = 1000 + random.nextInt(3000);
        progressBarAnimation(animationDuration);

        new Thread(() -> {
            try {
                Thread.sleep(animationDuration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            showBotGesture(bot.getSelectedGesture());
            if (getWinner() == player) {
                playerWin();
            } else if (getWinner() == bot) {
                botWin();
            } else {
                drawWin();
            }
            resetButton.setVisible(true);
        }).start();
    }

    public Player getWinner() {
        if (player.getSelectedGesture().getID() == 0 && bot.getSelectedGesture().getID() == 1) {
            // Schere vs Stein.
            // Bot gewinnt.
            return bot;
        }  else if (player.getSelectedGesture().getID() == 1 && bot.getSelectedGesture().getID() == 0) {
            // Stein vs Schere.
            // Player gewinnt.
            return player;
        } else if (player.getSelectedGesture().getID() == 0 && bot.getSelectedGesture().getID() == 2) {
            // Schere vs Papier.
            // Player gewinnt.
            return player;
        } else if (bot.getSelectedGesture().getID() == 0 && player.getSelectedGesture().getID() == 2) {
            // Papier vs Schere.
            // Bot gewinnt.
            return bot;
        } else if (player.getSelectedGesture().getID() == 1 && bot.getSelectedGesture().getID() == 2) {
            // Stein vs Papier.
            // Bot gewinnt.
            return bot;
        } else if (bot.getSelectedGesture().getID() == 1 && player.getSelectedGesture().getID() == 2) {
            // Papier vs Stein.
            // Player gewinnt.
            return player;
        }
        // Unentschieden
        return null;
    }


    public void resetHighScoreButtonPressed(ActionEvent actionEvent) {
        highScoreTextField.setText("0");
    }
}




