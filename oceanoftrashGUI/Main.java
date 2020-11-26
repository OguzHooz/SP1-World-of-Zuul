/*
package oceanoftrashGUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    public class ButtonHelloWorldHandler implements EventHandler<ActiveEvent>{
        @Override
        public void handle (ActiveEvent event){
            System.out.println("Hello World!");
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Button btn = new Button();
        btn.setLabel("Say 'Hello World'");
        btn.setOnAction(new ButtonHelloWorldHandler());
        StackPane root2 = new StackPane(btn);
        Scene scene = new Scene(root2, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}*/
