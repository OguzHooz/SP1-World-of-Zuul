package oceanoftrashGUI;

import javafx.application.Application;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.event.*;




public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GUI");
        primaryStage.setScene(new Scene(root, 1000,1000));
        primaryStage.show();
    }

    private void mouseCheck() {

    }


    public static void main(String[] args) {
        launch(args);
    }
}
