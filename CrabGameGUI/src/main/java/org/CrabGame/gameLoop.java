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
                x+=24;
            }
            counterHp = new Text();
            counterHp.relocate(8, 72);
            App.player.setHealth(20);
            counterHp.setText("HP: " + valueOf(App.player.getHealth()));
            hp.getChildren().add(counterHp);
        //yderligere mangler vi hp bar
        counterCO2 = new Text();
            counterCO2.relocate(8,84);
            counterCO2.setFont(Font.font("/org/Images/Mario-Kart-DS.ttf"));
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
                counterCO2.setText(valueOf(CO2));

                for (int i = 0; i<App.getFoodList().size(); i++){
                    if (App.getFoodList().get(i).getLayoutX() < App.player.getX() && App.player.getX() < (App.getFoodList().get(i).getLayoutX()+App.getFoodList().get(i).getImage().getWidth())){
                        if (App.getFoodList().get(i).getLayoutY() < App.player.getY() && App.player.getY() < (App.getFoodList().get(i).getLayoutY()+App.getFoodList().get(i).getImage().getHeight())) {
                            if (App.player.getHealth() + 25 <= 100) {
                                App.player.setHealth(100);
                                System.out.println("hejsa2");
                            } else {
                                System.out.println("hejsa");
                                App.player.setHealth(App.player.getHealth() + 25);

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
