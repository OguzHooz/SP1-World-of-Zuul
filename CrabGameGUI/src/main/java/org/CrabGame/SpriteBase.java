package org.CrabGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class SpriteBase {
    static Image image;
    static ImageView imageView;

    static Pane layer;

    static double x;
    static double y;
    static double r;

    static double dx;
    static double dy;
    static double dr;

    static double health;
    static double damage;

    static boolean removable = false;

    static double w;
    static double h;

    public SpriteBase(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double damage) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;

        this.health = health;
        this.damage = damage;

        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);
        this.imageView.setRotate(r);

        this.w = image.getWidth();
        this.h = image.getHeight();

        addToLayer();

    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public static void move() {

        x += dx;
        y += dy;
        r += dr;

    }

    public boolean isAlive() {
        return Double.compare(health, 0) > 0;
    }

    public static void updateUI() {

        imageView.relocate(x, y);
        imageView.setRotate(r);

    }

    public abstract void checkRemovability();

    // -----------------------------------------
    // automatically generated getters & setters
    // -----------------------------------------

}
