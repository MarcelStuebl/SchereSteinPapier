package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    public ComboBox songChoiceComboBox;
    private MediaPlayer mediaPlayer;

    private static final String FILE_NAME = ".stats";

    public Player player = new Player();
    public Player bot = new Player();


    /**
     * Initialize.
     * Loads highscore and starts background music.
     */
    public void initialize() {
        globalHighScoreTextField.setText("" + loadHighscore());
        songChoiceComboBox.getItems().addAll("Lobby Classic", "Song 2", "Song 3");
        songChoiceComboBox.setValue("Lobby Classic");
        playBackgroundMusic(songChoiceComboBox.getValue().toString());

        // Event listener auf Value für die ScrollBar hinzufügen um Volumen zu ändern
        volumeScrollBar.valueProperty().addListener((observable, oldValue, newValue)
                -> mediaPlayer.setVolume(newValue.doubleValue()));
    }

    /**
     * Play background music.
     * Loads and plays the background music in a loop.
     */
    private void playBackgroundMusic(String song) {
        String path = "";
        if (Objects.equals(song, "Lobby Classic")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/lobby-classic-game.mp3")).toExternalForm();
        } else  if (Objects.equals(song, "Song 2")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/song2.mp3")).toExternalForm();
        } else  if (Objects.equals(song, "Song 3")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/song3.mp3")).toExternalForm();
        }

        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volumeScrollBar.getValue());
        mediaPlayer.play();
    }


    public void songChosenComboBox(ActionEvent actionEvent) {
        playBackgroundMusic(songChoiceComboBox.getValue().toString());
    }

    /**
     * Save high score.
     * Saves the current global high score to a .stats file.
     */
    public void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(globalHighScoreTextField.getText()));
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    /**
     * Load highscore int from .stats file.
     * If no file exists, returns 0.
     *
     * @return the highscore as int
     */
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

    /**
     * Is high score beaten.
     * Checks if the current score is higher than the global high score.
     * If so, updates the global high score and saves it.
     */
    public void isHighScoreBeaten() {
        int currentScore = Integer.parseInt(currentHighScoreTextField.getText());
        int globalHighScore = Integer.parseInt(globalHighScoreTextField.getText());
        if (currentScore > globalHighScore) {
            globalHighScoreTextField.setText(String.valueOf(currentScore));
            saveHighScore();
        }
    }

    /**
     * Schere button pressed.
     * Sets player's selected gesture to Schere and proceeds.
     */
    public void schereButtonPressed() {
        player.setSelectedGesture(0);
        gestureSelected();
    }

    /**
     * Stein button pressed.
     * Sets player's selected gesture to Stein and proceeds.
     */
    public void steinButtonPressed() {
        player.setSelectedGesture(1);
        gestureSelected();
    }

    /**
     * Papier button pressed.
     * Sets player's selected gesture to Papier and proceeds.
     */
    public void papierButtonPressed() {
        player.setSelectedGesture(2);
        gestureSelected();
    }

    /**
     * Brunnen button pressed.
     * Sets player's selected gesture to Brunnen and proceeds.
     */
    public void brunnenButtonPressed() {
        player.setSelectedGesture(3);
        gestureSelected();
    }


    /**
     * Reset button pressed.
     * Resets the game state for a new round.
     * Hides the gestures, shows the buttons, and clears the winner text.
     */
    public void resetButtonPressed() {
        playerShowGesture.setVisible(false);
        botShowGesture.setVisible(false);
        showButtons();
        resetButton.setVisible(false);
        winnerTextField.setText("");
    }

    /**
     * Hide buttons.
     * Hides the gesture selection buttons.
     */
    public void hideButtons() {
        schereButton.setVisible(false);
        steinButton.setVisible(false);
        papierButton.setVisible(false);
        brunnenButton.setVisible(false);
    }

    /**
     * Show buttons.
     * Shows the gesture selection buttons.
     */
    public void showButtons() {
        schereButton.setVisible(true);
        steinButton.setVisible(true);
        papierButton.setVisible(true);
        brunnenButton.setVisible(true);
    }

    /**
     * Load image from resources.
     * Loads an image given its filename from the resources folder.
     *
     * @param filename the filename
     * @return the image
     */
    private Image loadImage(String filename) {
        String path = "/htl/steyr/scheresteinpapier/img/" + filename;
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            System.err.println("Resource not found: " + path);
            return null;
        }
        return new Image(stream);
    }


    /**
     * Show gesture.
     * Displays the gesture image in the given ImageView.
     *
     * @param gesture the gesture
     * @param view    the view
     */
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


    /**
     * Player win.
     * Increases current high score by 1, checks for global high score update, and sets winner text.
     */
    public void playerWin() {
        currentHighScoreTextField.setText(String.valueOf(Integer.parseInt(currentHighScoreTextField.getText()) + 1));
        isHighScoreBeaten();
        winnerTextField.setText("You Win!");
    }

    /**
     * Bot win.
     * Resets current high score to 0, checks for global high score update, and sets winner text.
     */
    public void botWin() {
        currentHighScoreTextField.setText("0");
        isHighScoreBeaten();
        winnerTextField.setText("You Lose!");
    }

    /**
     * Draw win.
     * Sets winner text to indicate a draw.
     */
    public void drawWin() {
        winnerTextField.setText("Draw!");
    }


    /**
     * Progress bar animation.
     * Animates the bot's progress bar over the specified (random) duration.
     *
     * @param animationDuration the animation duration
     */
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


    /**
     * Gesture selected.
     * Handles the event when a player selects a gesture.
     * Hides buttons, shows player's gesture, sets bot's random gesture, and reveals the winner.
     */
    public void gestureSelected() {
        hideButtons();
        showGesture(player.getSelectedGesture(), playerShowGesture);
        bot.setRandomGesture();
        revealWinner();
    }


    /**
     * Reveal winner.
     * Animates the bot's progress bar, then reveals the bot's gesture and determines the winner.
     * Updates the UI accordingly.
     */
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


    /**
     * Gets winner.
     * Determines the winner based on the selected gestures of the player and bot.
     *
     * @return the winner
     */
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




