package com.mycompany.worldofzuulgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    
    private static Scene scene;

    // ved sådan set ikke rigtig hvad der ikke er obvious her
    
    @Override
    public void start(Stage stage) throws IOException {
        // start med at lave vores scene. Det starter med at loade hjem.fxml da det er jo der hvor spillet starter 
        scene = new Scene(loadFXML("Hjem"), 960, 720);
        
        // set titlen for vinduet
        stage.setTitle("The Recycle Adventurer");
        
        // load vores hjem.fxml scene
        stage.setScene(scene);
        
        //¯¯\_(ツ)_/¯
        stage.show();
        WorldOfZuul.game.printWelcome();
    }

    // metoden her vil hjælpe os med at gå fra rum til rum
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // wrapper function til at indlæsse de forskellige .fxml filer
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // ja
    public static void load(String[] args) {
        launch();
    }

}
