package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.User;
import htl.steyr.scheresteinpapier.database.DatabaseUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    @FXML
    public AnchorPane root;
    public ListView highscoreListView;
    public TextField userTextField;
    public Button playButton;

    private final DatabaseUser databaseUser = new DatabaseUser();
    public User currentUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void startGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SartApplication.class.getResource("game-view.fxml"));
        Parent newRoot = fxmlLoader.load();
        Scene newScene = new Scene(newRoot);
        GameController controller = fxmlLoader.getController();
        controller.setCurrentUser(currentUser);
        Stage currentStage = (Stage) root.getScene().getWindow();

        currentStage.setTitle("Schere Stein Papier");
        currentStage.setScene(newScene);
        currentStage.getIcons().add(new Image(Objects.requireNonNull(SartApplication.class.getResourceAsStream("img/icon.png"))));
    }


    public void playButtonPressed(ActionEvent actionEvent) throws SQLException {
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
