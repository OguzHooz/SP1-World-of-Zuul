package org.CrabGame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class centerController extends AnchorPane implements Initializable{
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

    private ArrayList<ImageView> arrayImage;


    //opretter array der indeholder mad billeder

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        arrayImage = new ArrayList<>();
        arrayImage.add(f1);
        arrayImage.add(f2);
        arrayImage.add(f3);
        arrayImage.add(f4);
        arrayImage.add(f5);
        //Vi tager fat i det rum vi er i
        Room currentRoom = Game.getCurrentRoom();
        //så skal vi gå i gennem hvert stykke mad og sætte billed på f1,f2 eller f3
        //i forhold til hvilket stykke mad der er spawnet
        for (int i = 0; i < currentRoom.getFoodCoordinatex().size(); i++){
            //sætter billed id
            arrayImage.get(i).setId(currentRoom.getFoodType().get(i));
            //the image is added to the jar, access it via getResource
            arrayImage.get(i).setImage(new Image(getClass().getResource("/org/Images/"+ currentRoom.getFoodType().get(i).toUpperCase() +".png").toExternalForm()));

            //Sætter hvilket sted de skal spawne, både X og Y position
            arrayImage.get(i).setLayoutX(new Random().nextInt(795));
            arrayImage.get(i).setLayoutY(new Random().nextInt(597));
        }
        //spiller bevægelse her i guess

    }
}
