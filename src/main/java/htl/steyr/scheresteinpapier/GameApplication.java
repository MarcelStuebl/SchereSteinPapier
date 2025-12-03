package htl.steyr.scheresteinpapier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Schere Stein Papier");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(GameApplication.class.getResourceAsStream("img/icon.png"))));
        stage.show();






    }
}




