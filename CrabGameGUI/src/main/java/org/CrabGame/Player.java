package org.CrabGame;

public class Player {
    //skal indsamles i starten på et tidspunkt
    String name;
    private static int Hunger = 5;
    private static int xkoordinat = 2;
    private static int ykoordinat = 2;
    public static int[] position = {ykoordinat, xkoordinat};

    public static int getHunger() {
        return Hunger;
    }

    public static void setHunger(int hunger) {
        Hunger += hunger;
    }

    public static void moveXakse(int x) {
        xkoordinat = xkoordinat + x;
        position[0] = position[0] + x;
        System.out.println("\nYou are now standing on: [y:" + ykoordinat + ", x:" + xkoordinat + "]");

    }
    public static void moveYakse(int y) {
        ykoordinat = ykoordinat+y;
        position[1] = position[1] + y;
        System.out.println("\nYou are now standing on: [y:" + ykoordinat + ", x:" + xkoordinat + "]");

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

    public static int getXkoordinat() {
        return xkoordinat;
    }

    public static int getYkoordinat() {
        return ykoordinat;
    }
}