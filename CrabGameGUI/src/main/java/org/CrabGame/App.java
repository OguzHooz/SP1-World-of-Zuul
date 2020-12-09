package org.CrabGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private static Pane rootscene;

    @Override
    public void start(Stage stage) throws IOException {
        //starter med hvilken scene vi gerne ville have (burde være start skærmen)
        scene = new Scene(loadFXML("center"), Settings.GAME_HEIGHT, Settings.GAME_WIDTH);
        //Titlen til vinduet der bliver åbnet
        stage.setTitle("The Crab Game");
        //load vores start skærm
        stage.setScene(scene);
        //viser scenen
        stage.show();
        gameLoop.createDebugOverlay();
        gameLoop.createGameLoop();
        gameLoop.startGame();
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

    //loader spillet op (Kaldes i Main)
    public static void load(String[] args) {
        launch();
    }
}