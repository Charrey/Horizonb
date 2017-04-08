package gui;

import gui.screens.creation.CreationScreen;
import gui.screens.screen1.Screen1VC;
import gui.screens.screen2.Screen2VC;

public enum ViewControllerManager {

    instance;

    private Screen1VC screen1VC;
    private Screen2VC screen2VC;
    private CreationScreen creationScreen;


    public Screen1VC getScreen1VC() {
        return screen1VC;
    }

    public Screen2VC getScreen2VC() {
        return screen2VC;
    }

    public CreationScreen getCreationScreen() {
        return creationScreen;
    }

    public void setScreen1VC(Screen1VC screen1VC) {
        this.screen1VC = screen1VC;
    }

    public void setScreen2VC(Screen2VC screen2VC) {
        this.screen2VC = screen2VC;
    }


    private ViewControllerManager() {
    }

    public void setCreationScreenC(CreationScreen creationScreen) {
        this.creationScreen = creationScreen;
    }
}
