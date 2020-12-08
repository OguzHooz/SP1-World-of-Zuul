package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.GameViewManager;

public class PlayerGUI extends SpriteBase
{
    public String playerSprite = "resources/Crab.png";
    static int playerPosMinX;
    static int playerPosMaxX;
    static int playerPosMinY;
    static int playerPosMaxY;
    double speed;

    public static int playerPosX;
    public static int playerPosY;


    public PlayerGUI(Pane layer, Image image, double x, double y, double health, double damage, double speed) {
        super(layer, image, x, y, health, damage);

        this.speed = speed;
        init();
    }
    private void init(){
        //udregn movement bounds for player
        // bruger /2 for at halvdelen fra midten af krabbe spriten kan være ude af vinduet.
        playerPosMinX = (int) (0 - image.getWidth() / 2);
        playerPosMaxX = (int) (GameViewManager.GameWidth - image.getWidth() / 2);
        playerPosMinY = (int) (0 - image.getHeight() / 2);
        playerPosMaxY = (int) (GameViewManager.GameHeight - image.getHeight() / 2);
    }
    private static void checkBounds()
    {
        if (playerPosY < playerPosMinY)
        {
            playerPosY = playerPosMinY;
        }    else if(x > playerPosMaxY)
            {
            playerPosY = playerPosMaxY;
            }
        if (playerPosX < playerPosMinX)
        {
            playerPosX = playerPosMinX;
        }    else if(x > playerPosMaxY)
            {
            playerPosX = playerPosMaxY;
            }
    }

    public static void movePlayer(String retning)
    {
        checkBounds();
        switch (retning) {

            case "W" -> playerPosY = playerPosY - 5;
            case "S" -> playerPosY = playerPosY + 5;
            case "A" -> playerPosX = playerPosX - 5;
            case "D" -> playerPosX = playerPosX + 5;
        }

    }

}
