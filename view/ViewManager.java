package view;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ViewManager {

    private static final int HØJDE = 600;
    private static final int BREDDE = 800;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager(){

        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,HØJDE,BREDDE);
        mainStage = new Stage();


    }
    public Stage getMainStage(){

        return mainStage;

    }




}


