package org.CrabGame;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Food extends SpriteBase {
    public Food(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double damage) {
        super(layer, image, x, y, r, dx, dy, dr, 1, 1); // TODO: health/damage
    }
    @Override
    public void checkRemovability(){
        
    }
}
