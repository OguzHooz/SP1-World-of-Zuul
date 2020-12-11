package org.CrabGame;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class gameLoop {
    private static AnimationTimer gameLoop;
    private static Pane debugLayer;
    private static Label debugLabel;
    // counter for game loop
    private static int frameCount = 0;
    private static int fpsCurrent = 0;
    private static long prevTime = -1;
    //imageview and layer for life
    private static List life;
    private static Text counterHp;
    private static Text counterCO2;
    private static double CO2 = Game.getCurrentRoom().getInitialCO2();
    private static Pane hp;

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
    public static void createHPOverlay(int antalliv){
        hp = new Pane();
        App.getRootscene().getChildren().add(hp);
        //life container
            life = new ArrayList<>();
            for (int i = 0; i < antalliv; i++){
                life.add(i);
            }
            int x = 16;
            int y = 0;
            for (int i = 0; i<life.size();i++){
                ImageView container = new ImageView();
                Image billed = new Image(App.class.getResource("/org/Images/HEART64.png").toExternalForm());
                container.setImage(billed);
                container.relocate(x,y);
                hp.getChildren().add(container);
                x+=68;
            }
            counterHp = new Text();
            counterHp.relocate(8, 72);
            App.player.setHealth(20);
            counterHp.setText("HP: " + valueOf(App.player.getHealth()));
            counterHp.setFill(Color.ANTIQUEWHITE);
            hp.getChildren().add(counterHp);
        //yderligere mangler vi hp bar
        counterCO2 = new Text();
            counterCO2.relocate(8,84);
            counterCO2.setStyle("-fx-font-family: Mario-Kart-DS");
            counterCO2.setText("CO2: " + valueOf(0));
            counterCO2.setFill(Color.ANTIQUEWHITE);
            hp.getChildren().add(counterCO2);
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
                    App.getRootscene().getChildren().add(hp);
                    App.createfood();
                    App.getRootscene().getChildren().add(App.getFoodLayer());
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
                    App.getRootscene().getChildren().add(hp);
                    App.createfood();
                    App.getRootscene().getChildren().add(App.getFoodLayer());
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
                    App.getRootscene().getChildren().add(hp);
                    App.createfood();
                    App.getRootscene().getChildren().add(App.getFoodLayer());
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
                    App.getRootscene().getChildren().add(hp);
                    App.createfood();
                    App.getRootscene().getChildren().add(App.getFoodLayer());

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


                // move sprites internally
                App.player.move();

                CO2 = CO2+Game.getCurrentRoom().getHigherCO2();
                counterCO2.setText("CO2: " + Math.round(CO2*1d) + "%");
                if (33 < CO2 && CO2 < 66) {
                    Crab.setShellState(2);
                    App.loadResources();
                }
                if (66 < CO2 && CO2 < 100) {
                    Crab.setShellState(1);
                    App.loadResources();
                }
                if (CO2 >= 100) {
                    Crab.setShellState(0);
                    App.loadResources();
                    counterCO2.setText("CO2: 100%");

                }

                for (int i = 0; i<Game.getCurrentRoom().getFoodList().size(); i++){
                    if (Game.getCurrentRoom().getFoodList().get(i).getLayoutX() < App.player.getX() && App.player.getX() < (Game.getCurrentRoom().getFoodList().get(i).getLayoutX()+Game.getCurrentRoom().getFoodList().get(i).getImage().getWidth())){
                        if (Game.getCurrentRoom().getFoodList().get(i).getLayoutY() < App.player.getY() && App.player.getY() < (Game.getCurrentRoom().getFoodList().get(i).getLayoutY()+Game.getCurrentRoom().getFoodList().get(i).getImage().getHeight())) {
                            if (App.player.getHealth() + 25 >= 100) {
                                hp.getChildren().remove(counterHp);
                                App.player.setHealth(100);
                                counterHp.setText("HP: " + valueOf(App.player.getHealth()));
                                hp.getChildren().add(counterHp);

                                Game.getCurrentRoom().getFoodList().remove(i);
                                App.getFoodLayer().getChildren().clear();
                                App.getFoodLayer().getChildren().addAll(Game.getCurrentRoom().getFoodList());
                            } else {
                                hp.getChildren().remove(counterHp);
                                App.player.setHealth(App.player.getHealth() + 25);
                                counterHp.setText("HP: " + valueOf(App.player.getHealth()));
                                hp.getChildren().add(counterHp);

                                Game.getCurrentRoom().getFoodList().remove(i);
                                App.getFoodLayer().getChildren().clear();
                                App.getFoodLayer().getChildren().addAll(Game.getCurrentRoom().getFoodList());
                            }
                        }
                    }
                }


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
