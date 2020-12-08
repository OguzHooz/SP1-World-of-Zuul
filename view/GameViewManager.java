package view;
import javafx.application.Application;
import application.PlayerGUI;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import java.util.Timer;


public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    public static final int GameWidth = 600;
    public static final int GameHeight = 800;

    public GameViewManager(){
        initializeStage();
        createKeyListeners();


    }
    public void createKeyListeners (){

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent)
            {
                if(keyEvent.getCode() == KeyCode.W)
                {
                    PlayerGUI.movePlayer("W");
                }
                else if(keyEvent.getCode() == KeyCode.A)
                {
                    PlayerGUI.movePlayer("A");
                }
                else if(keyEvent.getCode() == KeyCode.S)
                {
                    PlayerGUI.movePlayer("S");
                }
                else if(keyEvent.getCode() == KeyCode.D)
                {
                    PlayerGUI.movePlayer("D");
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
    public void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,GameWidth,GameHeight);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        Image img = new Image("resources/Crab.png");
        ImageView imgView = new ImageView(img);
        gamePane.getChildren().add(imgView);
        gameLoop();










    }


    public Stage getGameStage() {
        return gameStage;
    }
    private void gameLoop() {

        // game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {


            }

        };

        timer.start();

    }

    

}
