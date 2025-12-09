package htl.steyr.scheresteinpapier;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    @FXML
    public AnchorPane root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            try {
                startGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void startGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SartApplication.class.getResource("game-view.fxml"));
        Parent newRoot = fxmlLoader.load();
        Scene newScene = new Scene(newRoot);
        Stage currentStage = (Stage) root.getScene().getWindow();

        currentStage.setTitle("Schere Stein Papier");
        currentStage.setScene(newScene);
        currentStage.getIcons().add(new Image(Objects.requireNonNull(SartApplication.class.getResourceAsStream("img/icon.png"))));
    }



}
