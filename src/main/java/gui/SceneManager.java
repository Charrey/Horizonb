package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.FileUtils;

import java.io.IOException;

public enum SceneManager {

    instance;

    private Scene screen1Screen;
    private Scene screen2Screen;
    private Scene creationScreen;

    public void loadScenes() {
        try {
            Pane screen1Pane = FXMLLoader.load(FileUtils.fromResources("screens/screen1.fxml").toURI().toURL());
            setScreen1Screen(new Scene(screen1Pane));

            Pane screen2Pane = FXMLLoader.load(FileUtils.fromResources("screens/screen2.fxml").toURI().toURL());
            setScreen2Screen(new Scene(screen2Pane));

            Pane creationPane = FXMLLoader.load(FileUtils.fromResources("screens/addserver.fxml").toURI().toURL());
            setCreationScreen(new Scene(creationPane));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showScreen(Scene scene) {
        Stage stage = Model.getInstance().getPrimaryStage();
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScreen1Screen() {
        return screen1Screen;
    }

    public void setScreen1Screen(Scene screen1Screen) {
        this.screen1Screen = screen1Screen;
    }

    public Scene getCreationScreen() {
        return creationScreen;
    }

    public void setCreationScreen(Scene creationScreen) {
        this.creationScreen = creationScreen;
    }

    public Scene getScreen2Screen() {
        return screen2Screen;
    }

    public void setScreen2Screen(Scene screen2Screen) {
        this.screen2Screen = screen2Screen;
    }

}
