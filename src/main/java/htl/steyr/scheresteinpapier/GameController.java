package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;


public class GameController {
    public Button schereButton;
    public Button steinButton;
    public Button papierButton;
    public Button brunnenButton;
    public ImageView playerShowGesture;
    public ImageView botShowGesture;
    public Button resetButton;
    public ProgressBar botProgressBar;

    public Text globalHighScoreTextField;
    public Text currentHighScoreTextField;
    public Text winnerTextField;

    public ScrollBar volumeScrollBar;
    private MediaPlayer mediaPlayer;

    private static final String FILE_NAME = ".stats";

    public Player player = new Player();
    public Player bot = new Player();


    public void initialize() {
        globalHighScoreTextField.setText("" + loadHighscore());
        playBackgroundMusic();

        // Event listener auf Value für die ScrollBar hinzufügen
        volumeScrollBar.valueProperty().addListener((observable, oldValue, newValue)
                -> volumeScrollBarInputChanged(newValue.doubleValue()));
    }


    private void volumeScrollBarInputChanged(double newValue) {
        mediaPlayer.setVolume(newValue);
    }

    private void playBackgroundMusic() {
        String path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/lobby-classic-game.mp3")).toExternalForm();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volumeScrollBar.getValue());
        mediaPlayer.play();
    }

    public void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(globalHighScoreTextField.getText()));
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public int loadHighscore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Kein Highscore gefunden, starte bei 0.");
        }
        return 0;
    }

    public void isHighScoreBeaten() {
        int currentScore = Integer.parseInt(currentHighScoreTextField.getText());
        int globalHighScore = Integer.parseInt(globalHighScoreTextField.getText());
        if (currentScore > globalHighScore) {
            globalHighScoreTextField.setText(String.valueOf(currentScore));
            saveHighScore();
        }
    }

    public void schereButtonPressed() {
        player.setSelectedGesture(0);
        gestureSelected();
    }

    public void steinButtonPressed() {
        player.setSelectedGesture(1);
        gestureSelected();
    }

    public void papierButtonPressed() {
        player.setSelectedGesture(2);
        gestureSelected();
    }

    public void brunnenButtonPressed() {
        player.setSelectedGesture(3);
        gestureSelected();
    }


    public void resetButtonPressed() {
        playerShowGesture.setVisible(false);
        botShowGesture.setVisible(false);
        showButtons();
        resetButton.setVisible(false);
        winnerTextField.setText("");
    }

    public void hideButtons() {
        schereButton.setVisible(false);
        steinButton.setVisible(false);
        papierButton.setVisible(false);
        brunnenButton.setVisible(false);
    }

    public void showButtons() {
        schereButton.setVisible(true);
        steinButton.setVisible(true);
        papierButton.setVisible(true);
        brunnenButton.setVisible(true);
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


    public void showGesture(Gesture gesture, ImageView view) {
        view.setVisible(true);
        switch (gesture.getGesture()) {
            case 0:
                view.setImage(loadImage("Schere.png"));
                break;
            case 1:
                view.setImage(loadImage("Stein.png"));
                break;
            case 2:
                view.setImage(loadImage("Papier.png"));
                break;
            case 3:
                view.setImage(loadImage("Brunnen.png"));
                break;
            default:
                view.setImage(null);
        }
    }


    public void playerWin() {
        currentHighScoreTextField.setText(String.valueOf(Integer.parseInt(currentHighScoreTextField.getText()) + 1));
        isHighScoreBeaten();
        winnerTextField.setText("You Win!");
    }

    public void botWin() {
        currentHighScoreTextField.setText("0");
        isHighScoreBeaten();
        winnerTextField.setText("You Lose!");
    }

    public void drawWin() {
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
                    Logger.getLogger(getClass().getName()).severe("Animation interrupted: " + e.getMessage());
                }
                double progress = (double) i / animationSteps;
                Platform.runLater(() -> botProgressBar.setProgress(progress));
            }

            Platform.runLater(() -> botProgressBar.setVisible(false));

        }).start();
    }


    public void gestureSelected() {
        hideButtons();
        showGesture(player.getSelectedGesture(), playerShowGesture);
        bot.setRandomGesture();
        revealWinner();
    }


    public void revealWinner() {
        Random random = new Random();
        final int animationDuration = 1000 + random.nextInt(3000);
        progressBarAnimation(animationDuration);

        new Thread(() -> {
            try {
                Thread.sleep(animationDuration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            showGesture(bot.getSelectedGesture(), botShowGesture);
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
        int playerGestureID = player.getSelectedGesture().getID();
        int botGestureID = bot.getSelectedGesture().getID();
        // Schere = 0, Stein = 1, Papier = 2, Brunnen = 3

        if (playerGestureID == 3 && (botGestureID == 0 || botGestureID == 1)) return player;    // Brunnen schlägt Schere und Stein
        if (botGestureID == 3 && (playerGestureID == 0 || playerGestureID == 1)) return bot;    // Brunnen schlägt Schere und Stein
        if (playerGestureID == 2 && botGestureID == 3) return player;   // Papier vs Brunnen
        if (botGestureID == 2 && playerGestureID == 3) return bot;      // Papier vs Brunnen
        if (playerGestureID == 0 && botGestureID == 1) return bot;      // Schere vs Stein
        if (playerGestureID == 1 && botGestureID == 0) return player;   // Stein vs Schere
        if (playerGestureID == 0 && botGestureID == 2) return player;   // Schere vs Papier
        if (playerGestureID == 2 && botGestureID == 0) return bot;      // Papier vs Schere
        if (playerGestureID == 1 && botGestureID == 2) return bot;      // Stein vs Papier
        if (playerGestureID == 2 && botGestureID == 1) return player;   // Papier vs Stein
        return null;    // Unentschieden
    }


}




