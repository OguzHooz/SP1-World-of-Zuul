package worldofzuul;

import java.util.Arrays;
import java.util.HashMap;

public class Player {
//skal indsamles i starten på et tidspunkt
    String name;
    int Hunger;
    public static int xkoordinat;
    public static int ykoordinat;
    static int[] position = {xkoordinat, ykoordinat};

    public static void moveXakse(int x)
    {
        System.out.println("\ndu stod på x: " + Arrays.toString(position));
        xkoordinat =+ xkoordinat+x;
        System.out.println("\ndu star nu på x: " + Arrays.toString(position));
    }
    public static void moveYakse(int y)
    {
        System.out.println("\ndu stod på: " + Arrays.toString(position));
        ykoordinat =+ ykoordinat+y;
        System.out.println("\ndu står nu på: " + Arrays.toString(position));
    }

}