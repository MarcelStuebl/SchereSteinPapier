package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;
import htl.steyr.scheresteinpapier.Model.Player;
import htl.steyr.scheresteinpapier.Model.User;
import htl.steyr.scheresteinpapier.database.DatabaseUser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class GameController implements Initializable {
    @FXML
    public Button schereButton;
    @FXML
    public Button steinButton;
    @FXML
    public Button papierButton;
    @FXML
    public Button brunnenButton;
    @FXML
    public ImageView playerShowGesture;
    @FXML
    public ImageView botShowGesture;
    @FXML
    public Button resetButton;
    @FXML
    public ProgressBar botProgressBar;

    @FXML
    public Text globalHighScoreTextFieldPlayer;
    @FXML
    public Text currentHighScoreTextFieldPlayer;
    @FXML
    public Text globalHighScoreTextField;
    @FXML
    public Text winnerTextField;

    @FXML
    public ScrollBar volumeScrollBar;
    @FXML
    public ComboBox<String> songChoiceComboBox;

    private MediaPlayer mediaPlayer;

    public Player player = new Player();
    public Player bot = new Player();

    private DatabaseUser databaseUser = new DatabaseUser();
    public User currentUser = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            currentUser = deserializeUser();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Highscores laden
        if (currentUser != null) {
            globalHighScoreTextFieldPlayer.setText("" + currentUser.getHighscore());
        } else {
            globalHighScoreTextFieldPlayer.setText("0");
        }

        User bestUser = null;
        try {
            bestUser = databaseUser.getBestUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (bestUser != null) {
            globalHighScoreTextField.setText("" + bestUser.getHighscore());
        } else {
            globalHighScoreTextField.setText("0");
        }

        // Songs initialisieren
        songChoiceComboBox.getItems().addAll("Lobby Classic", "Der Mann mit dem Koks", "Epic Boss Fight", "Minecraft Theme", "Deine Augen", "Intastellar");
        songChoiceComboBox.setValue("Lobby Classic");
        playBackgroundMusic(songChoiceComboBox.getValue());

        // Volume Listener
        volumeScrollBar.valueProperty().addListener((observable, oldValue, newValue)
                -> mediaPlayer.setVolume(newValue.doubleValue()));
    }


    public User deserializeUser() throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream("user.dat");
        ObjectInputStream ois = new ObjectInputStream(in);
        User deserializedUser = (User) ois.readObject();
        ois.close();
        return deserializedUser;
    }


    /**
     * Play background music.
     * Plays the selected background music track in a loop.
     *
     * @param song the song
     */
    private void playBackgroundMusic(String song) {
        String path = "";
        if (Objects.equals(song, "Lobby Classic")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/lobby-classic-game.mp3")).toExternalForm();
        } else if (Objects.equals(song, "Der Mann mit dem Koks")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/der-mann-mit-dem-koks.mp3")).toExternalForm();
        } else if (Objects.equals(song, "Epic Boss Fight")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/epic-boss-fight.mp3")).toExternalForm();
        } else if (Objects.equals(song, "Minecraft Theme")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/minecraft-c418-aria-math.mp3")).toExternalForm();
        } else if (Objects.equals(song, "Deine Augen")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/tream-deine-augen.mp3")).toExternalForm();
        } else if (Objects.equals(song, "Intastellar")) {
            path = Objects.requireNonNull(getClass().getResource("/htl/steyr/scheresteinpapier/sound/intastellar.mp3")).toExternalForm();
        }

        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volumeScrollBar.getValue());
        mediaPlayer.play();
    }


    /**
     * Song chosen combo box.
     * Event handler for song selection from the combo box.
     * Stops current music and plays the selected song.
     */
    public void songChosenComboBox() {
        mediaPlayer.stop();
        playBackgroundMusic(songChoiceComboBox.getValue());
    }


    /**
     * Is high score beaten.
     * Checks if the current high score exceeds the global high score for player or bot.
     * If so, updates the global high score.
     */
    public void isHighScoreBeaten() {
        int currentPlayerScore = Integer.parseInt(currentHighScoreTextFieldPlayer.getText());
        int globalPlayerScore = Integer.parseInt(globalHighScoreTextFieldPlayer.getText());

        if (currentPlayerScore > globalPlayerScore) {
            updateHighScore(currentPlayerScore);
        }
    }


    /**
     * Update high score.
     * Updates the global high score text field and persists the new high score to the database.
     *
     * @param newScore the new score
     */
    public void updateHighScore(int newScore) {
        globalHighScoreTextFieldPlayer.setText(String.valueOf(newScore));
        try {
            currentUser.setHighscore(newScore);
            databaseUser.updateUserScore(currentUser);
            globalHighScoreTextField.setText("" + databaseUser.getBestUser().getHighscore());
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
     * Loads an image given its filename from the "resources" folder.
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
        currentHighScoreTextFieldPlayer.setText(String.valueOf(Integer.parseInt(currentHighScoreTextFieldPlayer.getText()) + 1));
        isHighScoreBeaten();
        winnerTextField.setText("You Win!");
    }

    /**
     * Bot win.
     * Resets current high score to 0, checks for global high score update, and sets winner text.
     */
    public void botWin() {
        currentHighScoreTextFieldPlayer.setText("0");
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
            showGesture(player.getSelectedGesture(), playerShowGesture);
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
        if (playerGestureID == 3 && (botGestureID == 0 || botGestureID == 1))
            return player;    // Brunnen schlägt Schere und Stein
        if (botGestureID == 3 && (playerGestureID == 0 || playerGestureID == 1))
            return bot;    // Brunnen schlägt Schere und Stein
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




