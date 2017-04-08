package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import model.Receiver;
import model.ServerBase;
import utils.FileUtils;

public class Main extends Application {


    public static Window window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(8008135);
            }
        });
        primaryStage.setTitle("Horizon");
        primaryStage.getIcons().add(new Image("file:"+FileUtils.fromResources("icon-300x300.png").getAbsolutePath()));

        Model.getInstance().setPrimaryStage(primaryStage);

        SceneManager.instance.loadScenes();

        ViewControllerManager.instance.getScreen1VC().initializeVC();
        SceneManager.instance.showScreen(SceneManager.instance.getScreen1Screen());
        //window = primaryStage.getScene().getWindow();
    }


    public static void main(String[] args) {
        new Thread(new Receiver()).start();
        ServerBase.loadServers();
        launch(args);
    }
}
