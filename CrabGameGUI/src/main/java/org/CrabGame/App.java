package org.CrabGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * JavaFX App
 */
public class App extends Application {
    public static Scene scene;
    private static Pane rootscene;
    public static Pane playfieldLayer;
    public static Crab player;
    private static Image playerImage;
    private static Label SceneName;
    private static Pane foodLayer;

    @Override
    public void start(Stage stage) throws IOException {
        SceneName = new Label();
        foodLayer = new Pane();
        playfieldLayer = new Pane();
        playfieldLayer.setPrefSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        //starter med hvilken scene vi gerne ville have (burde være start skærmen)
        scene = new Scene(loadFXML("center"),Settings.GAME_WIDTH, Settings.GAME_HEIGHT );
        scene.getStylesheets().add(getClass().getResource("/org/CSS/application.css").toExternalForm());
        rootscene.getChildren().add(foodLayer);
        rootscene.getChildren().add(playfieldLayer);
        //Titlen til vinduet der bliver åbnet
        stage.setTitle("The Crab Game");
        //load vores start skærm
        stage.setScene(scene);

        //viser scenen
        stage.show();

        loadResources();

        //start med 3 liv

        gameLoop.createHPOverlay(player.getLife());
        createLevel(scene);
        gameLoop.createDebugOverlay();
        gameLoop.createGameLoop();
        gameLoop.startGame();

    }

    public static void loadResources(){
        playerImage = new Image(App.class.getResource("/org/Images/CRABHAPPY.png").toExternalForm(), 64,64,false,false);

    }

    public static void createLevel(Scene scene) {
        createfood();
        createPlayer(scene);
        gameLoop.heartBreaker();
    }
    public static void createfood(){

        ArrayList<ImageView> arrayImage = new ArrayList<>();
        //så skal vi gå i gennem hvert stykke mad og sætte billederne
        //i forhold til hvilket stykke mad der er spawnet
            for (int i = 0; i < Game.getCurrentRoom().getFoodCoordinatex().size(); i++) {
                ImageView imageView = new ImageView();
                arrayImage.add(imageView);
                //sætter billed id
                arrayImage.get(i).setId(Game.getCurrentRoom().getFoodType().get(i));
                //the image is added to the jar, access it via getResource
                arrayImage.get(i).setImage(new Image(App.class.getResource("/org/Images/" + Game.getCurrentRoom().getFoodType().get(i) + ".png").toExternalForm()));

                //Sætter hvilket sted de skal spawne, både X og Y position
                arrayImage.get(i).setLayoutX(Math.random() * Settings.GAME_WIDTH);
                arrayImage.get(i).setLayoutY(Math.random() * Settings.GAME_HEIGHT);

                foodLayer.getChildren().add(arrayImage.get(i));
                Game.getCurrentRoom().foodList.add(arrayImage.get(i));



        }
    }
    private static void createPlayer(Scene scene) {
        Input input = new Input(scene);
        input.addListeners();

        double x = Settings.GAME_WIDTH/2.0;
        double y = Settings.GAME_HEIGHT/2.0;

        player = new Crab(playfieldLayer,playerImage,x,y,0,0,0,0,Settings.PLAYER_HEALTH,0,Settings.PLAYER_SPEED, input, 3);
    }


    //sætter root for scenen, så den ved hvilken fil der skal vises
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    //metode til at indlæse den nye .fxml fil som skal vises
    public static Parent loadFXML(String fxml) throws IOException {
        rootscene = FXMLLoader.load(App.class.getResource(fxml + ".fxml"));
        return rootscene;
    }

    public static Pane getRootscene() {
        return rootscene;
    }

    public static Label getSceneName() {
        return SceneName;
    }

    public static Pane getFoodLayer() {
        return foodLayer;
    }

    //loader spillet op (Kaldes i Main)
    public static void load(String[] args) {
        launch();
    }
}