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

    public static int moveRoom(String retning) {
        if (retning == "north"){
            return (0);
    }
        else if (retning == "west"){
            return (0);
    }
        else if (retning =="south"){
            return (5);
    }
        else if (retning == "east"){
            return (5);

    }
    else {
        System.out.println(retning);
            return 10;
        }
    }


    public static void setXkoordinat(int x) {
        xkoordinat = x;
    }
    public static void setYkoordinat(int y) {
        ykoordinat = y;
    }



}