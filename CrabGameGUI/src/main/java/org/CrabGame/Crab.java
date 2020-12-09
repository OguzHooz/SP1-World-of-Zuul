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
        // calculate movement bounds of the player ship
        // allow half of the ship to be outside of the screen
        CrabMinX = 0 - image.getWidth() / 2.0;
        CrabMaxX = Settings.GAME_WIDTH - image.getWidth() / 2.0;
        CrabMinY = 0 - image.getHeight() / 2.0;
        CrabMaxY = Settings.GAME_HEIGHT -image.getHeight() / 2.0;
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
        if( input.isMoveLeft()) {
            dx = -speed;
        } else if( input.isMoveRight()) {
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


    @Override
    public void checkRemovability() {

    }
}
