package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import view.GameViewManager;

public class PlayerGUI extends SpriteBase
{
    double playerPosMinX;
    double playerPosMaxX;
    double playerPosMinY;
    double playerPosMaxY;
    double speed;

    public static int playerPosX;
    public static int playerPosY;


    public PlayerGUI(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double damage, double speed) {
        super(layer, image, x, y, r, dx, dy, dr, health, damage);

        this.speed = speed;
        init();
    }
    private void init(){
        //udregn movement bounds for player
        // bruger /2 for at halvdelen fra midten af krabbe spriten kan være ude af vinduet.
        playerPosMinX = 0 - image.getWidth() / 2.0;
        playerPosMaxX = GameViewManager.GameWidth - image.getWidth() / 2.0;
        playerPosMinY = 0 - image.getHeight() / 2.0;
        playerPosMaxY = GameViewManager.GameHeight - image.getHeight() / 2.0;
    }

    private void checkBounds()
    {
        if (Double.compare(y, playerPosMinY) < 0) {
            y = playerPosMinY;
        }    else if(Double.compare(x, playerPosMaxY) > 0){
            y = playerPosMaxY;
            }
        if (Double.compare(x, playerPosMinX) < 0) {
            x = playerPosMinX;
        }    else if(Double.compare(x, playerPosMaxY) > 0){
            x = playerPosMaxY;
        }
    }
    public static void movePlayer(String retning)
    {
        switch (retning) {
            case "W" -> playerPosY = playerPosY + 10; 
            case "S" -> playerPosY = playerPosY - 10;
            case "A" -> playerPosX = playerPosX - 10;
            case "D" -> playerPosX = playerPosX + 10;
        }

    }
}
