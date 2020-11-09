package worldofzuul;

import java.util.Arrays;
import java.util.HashMap;

public class Player {
    //skal indsamles i starten på et tidspunkt ikke brugt endnu
    public int Hunger;
    //laver to ints som er koordinater for playerclassen, og laver et array af de to koordinater
    public static int xkoordinat;
    public static int ykoordinat;
    static int[] position = {xkoordinat, ykoordinat};
//funktion der tager et tal og flytter ad x aksen, ved at ændre xkoordinat
    public static void moveXakse(int x) {

        position[0] = position[0] + x;
        System.out.println("\nYou are now standing on: " + Arrays.toString(position));

    }
    //funktion der tager et tal og flytter ad y aksen, ved at ændre ykoordinat
    public static void moveYakse(int y) {

        position[1] = position[1] + y;
        System.out.println("\nYou are now standing on: " + Arrays.toString(position));

    }

    //funktion der checker om man er tæt på grænsen mellem rum, ved at se om koord = 0 eller 5
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
}
