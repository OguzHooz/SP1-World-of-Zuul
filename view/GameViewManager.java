package view;

import application.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GameWidth = 600;
    private static final int GameHeight = 800;

    public GameViewManager(){
        initializeStage();
        createKeyListeners();
    }
    private void createKeyListeners (){

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.W) {
                    System.out.println("W is being pressed");
                }
                else if(keyEvent.getCode() == KeyCode.A){
                    System.out.println("A is being pressed");
                }
                else if(keyEvent.getCode() == KeyCode.S){
                    System.out.println("S is being pressed");
                }
                else if(keyEvent.getCode() == KeyCode.D){
                    System.out.println("D is being pressed");
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(javafx.scene.input.KeyEvent keyEvent) {
            if(keyEvent.getCode() == KeyCode.W) {
                System.out.println("W is no longer being pressed");
            }
            else if(keyEvent.getCode() == KeyCode.A){
                System.out.println("A is no longer being pressed");
            }
            else if(keyEvent.getCode() == KeyCode.S){
                System.out.println("S is no longer being pressed");
            }
            else if(keyEvent.getCode() == KeyCode.D){
                System.out.println("D is no longer being pressed");
            }
        }
    });
}
    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,GameWidth,GameHeight);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public Stage getGameStage() {
        return gameStage;
    }
    

}
