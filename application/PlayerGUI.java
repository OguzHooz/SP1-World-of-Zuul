package application;

public class PlayerGUI {

    private static int PlayerXPos;
    private static int PlayerYPos;
    public int[] PlayerPos = new int[] {PlayerXPos, PlayerYPos};
    public final static String PlayerImage = "resources/crab.png";


    public static void movePlayer(String retning)
    {
        switch(retning)
        {
            case "W":
                PlayerYPos = PlayerYPos+1;
                break;
            case "A":
                PlayerXPos = PlayerXPos-1;
                break;
            case "S":
                PlayerYPos = PlayerYPos-1;
                break;
            case "D":
                PlayerXPos = PlayerXPos+1;
                break;
        }
    }

}
