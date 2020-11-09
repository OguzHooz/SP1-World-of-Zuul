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


    public static boolean onBorder(){
        boolean canMove = false;
        if (position[1] == 0){
            canMove = true;
        } else if (position[1] == 5){
            canMove = true;
        } else if (position[0] == 0){
            canMove = true;
        } else if (position[0] == 5){
            canMove = true;
        }
        return canMove;
    }


    public static void setXkoordinat(int x) {
        xkoordinat = x;
    }
    public static void setYkoordinat(int y) {
        ykoordinat = y;
    }



}
