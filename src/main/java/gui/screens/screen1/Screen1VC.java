package gui.screens.screen1;

import gui.SceneManager;
import gui.ViewControllerManager;
import javafx.event.ActionEvent;

public class Screen1VC {

    public Screen1VC() {
        ViewControllerManager.instance.setScreen1VC(this);
    }

    public void initializeVC(){

    }

    public void nextButtonPushed(ActionEvent actionEvent) {
        ViewControllerManager.instance.getScreen2VC().initializeVC();
        SceneManager.instance.showScreen(SceneManager.instance.getScreen2Screen());
    }
}
