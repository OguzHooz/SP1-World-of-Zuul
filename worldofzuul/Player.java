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

    public static void moveXakse(int x) {

        position[0] = position[0] + x;
        System.out.println("\nYou are now standing on: " + Arrays.toString(position));

    }

    public static void moveYakse(int y) {

        position[1] = position[1] + y;
        System.out.println("\nYou are now standing on: " + Arrays.toString(position));

    }

    public static void moveRoom(String retning) {
        if (retning == "north"){
            setYkoordinat(0);
    }
        else if (retning == "west"){
            setXkoordinat(0);
    }
        else if (retning =="south"){
            setYkoordinat(5);
    }
        else if (retning == "east"){
            setXkoordinat(5);
    }}


    public static void setXkoordinat(int x) {
        xkoordinat = x;
    }
    public static void setYkoordinat(int y) {
        ykoordinat = y;
    }



}