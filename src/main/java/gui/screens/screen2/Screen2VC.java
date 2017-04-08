package gui.screens.screen2;

import gui.Main;
import gui.SceneManager;
import gui.ViewControllerManager;
import gui.components.ServerView;
import gui.screens.creation.CreationScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Connections;
import model.ServerBase;
import model.ServerInfo;
import utils.FileUtils;

import java.io.IOException;

public class Screen2VC {

    public ScrollPane scrollPane;
    public VBox vbox;

    public Screen2VC(){
        ViewControllerManager.instance.setScreen2VC(this);
    }

    public void initialize() {
        for (ServerInfo info : ServerBase.getTemp()) {
            ServerView sv = new ServerView(info, Connections.checkConnection(info));
            VBox.setVgrow(sv, Priority.NEVER);
            vbox.getChildren().addAll(sv);
        }
    }

    public void initializeVC(){

    }

    public void backButtonPushed(ActionEvent actionEvent) {
        ViewControllerManager.instance.getScreen1VC().initializeVC();
        SceneManager.instance.showScreen(SceneManager.instance.getScreen1Screen());
    }

    public void newButtonPushed(ActionEvent actionEvent) {
            if (CreationScreen.stage == null) {
                CreationScreen.stage = new Stage();
                CreationScreen.stage.setTitle("Add server");
                CreationScreen.stage.setScene(SceneManager.instance.getCreationScreen());
                CreationScreen.stage.setResizable(false);
                CreationScreen.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        CreationScreen.stage = null;
                    }
                });
                CreationScreen.stage.show();
            } else {
                CreationScreen.stage.toFront();
            }

    }
}
