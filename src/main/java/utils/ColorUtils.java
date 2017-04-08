package utils;


import java.awt.*;
import java.util.Random;

public class ColorUtils {

    public static String RandomColor() {
        Random rand = new Random();
        Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
