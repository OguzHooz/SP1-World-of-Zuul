package org.CrabGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


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

    @Override
    public void start(Stage stage) throws IOException {
        SceneName = new Label();
        playfieldLayer = new Pane();
        //playfieldLayer.setPrefSize(Settings.GAME_HEIGHT, Settings.GAME_HEIGHT);
        playfieldLayer.setPrefSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        //playfieldLayer.setStyle("-fx-background-color: blue");


        //starter med hvilken scene vi gerne ville have (burde være start skærmen)
        scene = new Scene(loadFXML("center"),Settings.GAME_WIDTH, Settings.GAME_HEIGHT );
        rootscene.getChildren().add(playfieldLayer);
        //Titlen til vinduet der bliver åbnet
        stage.setTitle("The Crab Game");
        //load vores start skærm
        stage.setScene(scene);

        //viser scenen
        stage.show();

        loadResources();
        createLevel(scene);
        gameLoop.createDebugOverlay();
        gameLoop.createGameLoop();
        gameLoop.startGame();
    }

    public static void loadResources(){
        playerImage = new Image(App.class.getResource("/org/Images/Crab.png").toExternalForm(), 64,64,false,false);
    }

    public static void createLevel(Scene scene) {
        createPlayer(scene);
    }

    private static void createPlayer(Scene scene) {
        Input input = new Input(scene);
        input.addListeners();

        double x = Settings.GAME_WIDTH/2.0;
        double y = Settings.GAME_HEIGHT/2.0;

        player = new Crab(playfieldLayer,playerImage,x,y,0,0,0,0,Settings.PLAYER_HEALTH,0,Settings.PLAYER_SPEED, input);
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

    //loader spillet op (Kaldes i Main)
    public static void load(String[] args) {
        launch();
    }
}