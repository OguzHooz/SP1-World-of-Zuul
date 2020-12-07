package org.CrabGame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class northwesternSeaController implements Initializable {
    //Vi bruger disse til at spawne mad i, vi kan flytte deres position senere
    //så længe objektet er i fxml filen
    //få fat på Imageview med id f1, f2 og f3
    @FXML
    private ImageView f1;
    @FXML
    private ImageView f2;
    @FXML
    private ImageView f3;
    @FXML
    private ImageView f4;
    @FXML
    private ImageView f5;
    //opretter array der indeholder mad billeder
    private ImageView[] arrayImage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //putter f1,f2,f3 i array
        arrayImage = new ImageView[]{f1,f2,f3,f4,f5};
        //Vi tager fat i det rum vi er i
        Room currentRoom = Game.getCurrentRoom();

        //så skal vi gå i gennem hvert stykke mad og sætte billed på f1,f2 eller f3
        //i forhold til hvilket stykke mad der er spawnet
        for (int i = 0; i < currentRoom.foodCoordinatex.size(); i++){
            //sætter billed id
            arrayImage[i].setId(currentRoom.foodType.get(i));
            //opretter sti til billedet ud fra mad typens navn inde i vores enum
            //File currentImage = new File("file:///" + System.getProperty("user.dir") + "/src/billeder" + currentRoom.foodType + ".png");
            //Sætter billedet fra stien vi lige har oprettet
            //arrayImage[i].setImage(new Image(currentImage.getPath()));

            //Sætter hvilket sted de skal spawne, både X og Y position
            arrayImage[i].setLayoutX(new Random().nextInt(795));
            arrayImage[i].setLayoutY(new Random().nextInt(597));

            //gør billedet synligt
            arrayImage[i].setVisible(true);
            arrayImage[i].managedProperty().bind(arrayImage[i].visibleProperty());
        }
    }
}
