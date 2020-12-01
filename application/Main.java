package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GameViewManager;
import view.ViewManager;


public class Main extends Application {




    @Override
    public void start(Stage primaryStage){
        GameViewManager manager = new GameViewManager();
        primaryStage = manager.getGameStage();
        primaryStage.show();


    }




    public static void main(String[] args) {
        launch(args);
    }
}
