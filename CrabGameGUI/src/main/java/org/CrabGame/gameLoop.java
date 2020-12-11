package org.CrabGame;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class gameLoop {
    private static AnimationTimer gameLoop;
    private static Pane debugLayer;
    private static Label debugLabel;
    // counter for game loop
    private static int frameCount = 0;
    private static int fpsCurrent = 0;
    private static long prevTime = -1;
    //label for scene you are entering

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

    private static void moveScene(double x, double y){
        //sætter sceneName til at display i toppen center
            //sæt den til at strække ud of hele canvas
        App.getSceneName().setLayoutX((Settings.GAME_WIDTH-App.getSceneName().getLayoutX())/2);
            //top center
        App.getSceneName().setAlignment(Pos.TOP_CENTER);
        //til venstre
        if (x == Crab.getCrabMinX()){
            Room nextRoom = Game.getCurrentRoom().getExit("west");
            if (nextRoom == null){
                System.out.println("there is no way");
            } else {
                try {
                    App.setRoot(nextRoom.getRoomName());
                    App.player.setX(Settings.GAME_WIDTH-32);
                    Game.setCurrentRoom(nextRoom);
                    Game.getCurrentRoom().isDiscovered = true;
                    App.getRootscene().getChildren().add(App.playfieldLayer);
                    //tilføjelse af transition på label
                    App.getSceneName().setText(("You have now entered: " + Game.getCurrentRoom().getRoomName()).toUpperCase());
                    App.getSceneName().setTextFill(Color.YELLOW);
                    FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(1500), App.getSceneName());
                    fadeTransition2.setFromValue(1.0);
                    fadeTransition2.setToValue(0.0);
                    fadeTransition2.play();
                    App.getRootscene().getChildren().add(App.getSceneName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //til højre
        if (x == Crab.getCrabMaxX()){
            Room nextRoom = Game.getCurrentRoom().getExit("east");
            if (nextRoom == null){
                System.out.println("there is no way");
            } else {
                try {
                    App.setRoot(nextRoom.getRoomName());
                    App.player.setX(32);
                    Game.setCurrentRoom(nextRoom);
                    Game.getCurrentRoom().isDiscovered = true;
                    App.getRootscene().getChildren().add(App.playfieldLayer);
                    //tilføjelse af transition på label
                    App.getSceneName().setText(("You have now entered: " + Game.getCurrentRoom().getRoomName()).toUpperCase());
                    App.getSceneName().setTextFill(Color.YELLOW);
                    FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(1500), App.getSceneName());
                    fadeTransition2.setFromValue(1.0);
                    fadeTransition2.setToValue(0.0);
                    fadeTransition2.play();
                    App.getRootscene().getChildren().add(App.getSceneName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //til nord
        if (y == Crab.getCrabMinY()){
            Room nextRoom = Game.getCurrentRoom().getExit("north");
            if (nextRoom == null){
                System.out.println("there is no way");
            } else {
                try {
                    App.setRoot(nextRoom.getRoomName());
                    App.player.setY(Settings.GAME_HEIGHT-32);
                    Game.setCurrentRoom(nextRoom);
                    Game.getCurrentRoom().isDiscovered = true;
                    App.getRootscene().getChildren().add(App.playfieldLayer);
                    //tilføjelse af transition på label
                    App.getSceneName().setText(("You have now entered: " + Game.getCurrentRoom().getRoomName()).toUpperCase());
                    App.getSceneName().setTextFill(Color.YELLOW);
                    FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(1500), App.getSceneName());
                    fadeTransition2.setFromValue(1.0);
                    fadeTransition2.setToValue(0.0);
                    fadeTransition2.play();
                    App.getRootscene().getChildren().add(App.getSceneName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (y == Crab.getCrabMaxY()){
            Room nextRoom = Game.getCurrentRoom().getExit("south");
            if (nextRoom == null){
                System.out.println("there is no way");
            } else {
                try {
                    App.setRoot(nextRoom.getRoomName());
                    App.player.setY(32);
                    Game.setCurrentRoom(nextRoom);
                    Game.getCurrentRoom().isDiscovered = true;
                    App.getRootscene().getChildren().add(App.playfieldLayer);
                    //tilføjelse af transition på label
                    App.getSceneName().setText(("You have now entered: " + Game.getCurrentRoom().getRoomName()).toUpperCase());
                    App.getSceneName().setTextFill(Color.YELLOW);
                    FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(1500), App.getSceneName());
                    fadeTransition2.setFromValue(1.0);
                    fadeTransition2.setToValue(0.0);
                    fadeTransition2.play();
                    App.getRootscene().getChildren().add(App.getSceneName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void createGameLoop(){
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // player AI (input)
                App.player.processInput();
                // sprite AI


                // add sprites

                // move sprites internally
                App.player.move();
                /*for (Node n : App.getRootscene().getChildren()){
                    if ("food".equals(n.getUserData())){
                        if ((n.getLayoutY() )){
                           n.setVisible(false);
                            System.out.println("usynlig");
                        }
                    }
                }*/

                // move sprites in the UI
                App.player.updateUI();

                // check if sprites can be moved scene
               moveScene(App.player.getX(),App.player.getY());

                // update debug information
                updateFps();
                updateDebugOverlay();


            }
        };
    }
}
