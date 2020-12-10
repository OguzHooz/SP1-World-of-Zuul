package org.CrabGame;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Crab extends SpriteBase {
    static double CrabMinX;
    static double CrabMaxX;
    static double CrabMinY;
    static double CrabMaxY;

    static Input input;

    static double speed;

    public Crab(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double damage, double speed, Input input) {
        super(layer, image, x, y, r, dx, dy, dr, health, damage);
        this.speed = Settings.PLAYER_SPEED;
        this.input = input;

        init();
    }

    private void init() {
        // calculate movement bounds of the player crab
        CrabMinX = 0;
        CrabMaxX = Settings.GAME_WIDTH ;
        CrabMinY = 0;
        CrabMaxY = Settings.GAME_HEIGHT;
    }

    public static void processInput(){
        // ------------------------------------
        // movement
        // ------------------------------------

        // vertical direction
        if( input.isMoveUp()) {
            dy = -speed;
        } else if( input.isMoveDown()) {
            dy = speed;
        } else {
            dy = 0d;
        }

        // horizontal direction
        if( input.isMoveLeft() && x != CrabMinX) {
            dx = -speed;
        } else if( input.isMoveRight() && x != CrabMaxX) {
            dx = speed;
        } else {
            dx = 0d;
        }
    }

    public static void move(){
        SpriteBase.move();
        //være sikker på spilleren ikke bevæger sig ud over skærmen
        checkBounds();
    }
    private static void checkBounds() {

        // vertical
        if( Double.compare( y, CrabMinY) < 0) {
            // public static int compare(double d1, double d2) -> 0 : d1 = d2, -n : d1 > d2, +n : d1 < d2
            y = CrabMinY;
        } else if( Double.compare(y, CrabMaxY) > 0) {
            y = CrabMaxY;
        }

        // horizontal
        if( Double.compare( x, CrabMinX) < 0) {
            x = CrabMinX;
        } else if( Double.compare(x, CrabMaxX) > 0) {
            x = CrabMaxX;
        }

    }
    public static double getX() {
        return x;
    }

    public static double getY() {
        return y;
    }

    public static Image getImage(){
        return image;
    }

    public static double getCrabMaxX() {
        return CrabMaxX;
    }

    public static double getCrabMaxY() {
        return CrabMaxY;
    }

    public static double getCrabMinX() {
        return CrabMinX;
    }

    public static double getCrabMinY() {
        return CrabMinY;
    }

    public static void setCrabMaxX(double crabMaxX) {
        CrabMaxX = crabMaxX;
    }

    public static void setCrabMaxY(double crabMaxY) {
        CrabMaxY = crabMaxY;
    }

    public static void setCrabMinX(double crabMinX) {
        CrabMinX = crabMinX;
    }

    public static void setCrabMinY(double crabMinY) {
        CrabMinY = crabMinY;
    }
    public static void setX(double X){
        x = X;
    }
    public static void setY(double Y){
        y = Y;
    }

    @Override
    public void checkRemovability() {

    }
}
