package gui.screens.creation;

import gui.SceneManager;
import gui.ViewControllerManager;
import gui.components.ServerView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import model.Connections;
import model.ServerInfo;

import java.awt.*;
import java.util.Optional;

public class CreationScreen {

    public static Stage stage;
    public Label errorLabel;
    @FXML private CheckBox checkbox;
    @FXML private TextField ipBar;
    @FXML private TextField nameBar;
    @FXML private TextField portBar;

    public CreationScreen() {
        ViewControllerManager.instance.setCreationScreenC(this);
    }

    public void checkBoxPressed(ActionEvent actionEvent) {
        if (checkbox.isSelected()) {
            ipBar.setText("127.0.0.1");
            ipBar.setDisable(true);
        } else {
            ipBar.setText("");
            ipBar.setDisable(false);
        }
    }


    public void addPressed(ActionEvent actionEvent) {
        try {
            boolean boolres = Connections.checkConnection(ipBar.getText(), portBar.getText());
            if (!boolres) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm");
                alert.setHeaderText("Offline server");
                alert.setContentText("The server could not be reached. Add anyway?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    stage.close();
                    stage = null;
                } else {
                    return;
                }
            }
            VBox spane = ViewControllerManager.instance.getScreen2VC().vbox;
            ServerInfo info = new ServerInfo(nameBar.getText(), ipBar.getText(), Integer.parseInt(portBar.getText()));
            ServerView sv = new ServerView(info, boolres);
            VBox.setVgrow(sv, Priority.NEVER);
            spane.getChildren().addAll(sv);

        } catch (Connections.IncorrectFieldException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
