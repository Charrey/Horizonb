package utils;


import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;


public class SoundPlayer  {


    private static boolean inited = false;


    public static void playSound(File file) {
        if (!inited) {
            TinySound.init();
            inited = true;
        }
        Sound sound = TinySound.loadSound(file);
        sound.play();
    }





}
