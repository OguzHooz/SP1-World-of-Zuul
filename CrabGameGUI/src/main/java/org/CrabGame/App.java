package org.CrabGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //starter med hvilken scene vi gerne ville have (burde være start skærmen)
        //altså er det her vi starter spillet
        scene = new Scene(loadFXML("northwesternSea"), 960,720);
        //Titlen til vinduet der bliver åbnet
        stage.setTitle("The Crab Game");
        //load vores start skærm
        stage.setScene(scene);
        //viser scenen
        stage.show();
    }
    //sætter root for scenen, så den ved hvilken fil der skal vises
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    //metode til at indlæse den nye .fxml fil som skal vises
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    //loader spillet op (Kaldes i Main)
    public static void load(String[] args) {
        launch();
    }

}