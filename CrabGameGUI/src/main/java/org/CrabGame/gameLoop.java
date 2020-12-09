package org.CrabGame;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class gameLoop {
    private static AnimationTimer gameLoop;
    private static Pane debugLayer;
    private static Label debugLabel;
    // counter for game loop
    private static int frameCount = 0;
    private static int fpsCurrent = 0;
    private static long prevTime = -1;

    public static void startGame(){
        gameLoop.start();
    }

    public static void createDebugOverlay() throws IOException {
        debugLayer = new Pane();
        App.getRootscene().getChildren().add(debugLayer);
        //Info container
        debugLabel = new Label();
        debugLabel.setTextFill(Color.INDIANRED);
        //tilføj label under debuglayer
        debugLayer.getChildren().add(debugLabel);

        ImageView debugImageview = new ImageView();
        Image debugImage = new Image("file:src/main/java/org.CrabGame/BACTERIA.png", 128, 128, false, false);
        debugImageview.setImage(debugImage);
        debugLayer.getChildren().add(debugImageview);

        //lidt styling til debuglayer
        debugLayer.setStyle("-fx-background-color: rgba(0,0,0,0.3)");
        debugLayer.prefWidthProperty().bind(App.getRootscene().widthProperty());
        debugLayer.layoutYProperty().bind(App.getRootscene().heightProperty().subtract(debugLayer.heightProperty()));

    }
    private static void updateFps(){
        frameCount++;
        long currTime = System.currentTimeMillis();
        if (currTime - prevTime >= 1000){
            fpsCurrent = frameCount;
            prevTime = currTime;
            frameCount = 0;
        }
    }
    private static void updateDebugOverlay(){
        debugLabel.setText("FPS: " + fpsCurrent);
    }



    public static void createGameLoop(){
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // player AI (input)
                App.playerList.forEach(crab -> Crab.processInput());
                // sprite AI


                // add sprites

                // move sprites internally
                App.playerList.forEach(crab -> Crab.move());

                // move sprites in the UI
                App.playerList.forEach(crab -> SpriteBase.updateUI());

                // check if sprites can be removed

                // update debug information
                updateFps();
                updateDebugOverlay();


            }
        };
    }
}
