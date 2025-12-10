package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.User;
import htl.steyr.scheresteinpapier.database.DatabaseUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    @FXML private AnchorPane root;
    @FXML private ListView highscoreListView;
    @FXML private TextField userTextField;
    @FXML private Button playButton;

    private final DatabaseUser databaseUser = new DatabaseUser();
    private User currentUser;


    /**
     * Sets up the highscore list view with the top 5 users from the database.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (User user : databaseUser.getBestUsers(5)) {
                highscoreListView.getItems().add(user.getUsername() + " - Highscore: " + user.getHighscore());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Serializes the current user to a file named "user.dat".
     * @throws IOException if an I/O error occurs while writing the file.
     */
    private void serializeUser() throws IOException {
        FileOutputStream out = new FileOutputStream("user.dat");
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(currentUser);
        oos.flush();
        oos.close();
    }


    /**
     * Starts the game by loading the game view and setting it as the current scene.
     * @throws IOException if an I/O error occurs while loading the FXML file.
     */
    private void startGame() throws IOException {
        serializeUser();

        FXMLLoader fxmlLoader = new FXMLLoader(SartApplication.class.getResource("game-view.fxml"));
        Parent newRoot = fxmlLoader.load();

        Scene newScene = new Scene(newRoot);
        Stage currentStage = (Stage) root.getScene().getWindow();

        currentStage.setTitle("Schere Stein Papier");
        currentStage.setScene(newScene);
        currentStage.getIcons().add(new Image(Objects.requireNonNull(SartApplication.class.getResourceAsStream("img/icon.png"))));
    }

    /**
     * Handles the play button press event. Checks if the username is valid and starts the game.
     * @throws SQLException if a database access error occurs.
     */
    @FXML private void playButtonPressed() throws SQLException {
        if (!userTextField.getText().isEmpty()) {
            if (databaseUser.getUser(userTextField.getText()) != null) {
                currentUser = databaseUser.getUser(userTextField.getText());
            } else {
                currentUser = new User(userTextField.getText());
                databaseUser.addUser(currentUser);
            }

            try {
                startGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}




