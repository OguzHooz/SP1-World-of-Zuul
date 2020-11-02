/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.worldofzuulgui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GadenController implements Initializable {

    private ImageView[] arrayImage;

    @FXML
    private ImageView f1;
    @FXML
    private ImageView f2;
    @FXML
    private ImageView f3;
    @FXML
    private ImageView background;
    @FXML
    private ImageView inv11;
    @FXML
    private ImageView inv22;
    @FXML
    private Button goByen;
    @FXML
    private Pane spawnPane;
    @FXML
    private Pane inventoryPane;
    @FXML
    private Text moveTxt;
    @FXML
    private Text scoreTxt;
    @FXML
    private Text sorterTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File backgroundImage = new File("file:///" + System.getProperty("user.dir")
                + "/src/main/java/com/mycompany/JavaBilleder1/GaagadenFinal4.png");
        background.setImage(new Image(backgroundImage.getPath()));

        arrayImage = new ImageView[]{f1, f2, f3};

        WorldOfZuul.game.inventory.reloadInv(inv11, inv22);

        int paneX = 200;
        int paneY = 390;
        int bX = 400;
        int bY = 420;

        Room currentRoom = WorldOfZuul.game.getCurrentRoom();
        currentRoom.trash.clear();
        currentRoom.spawnTrash(WorldOfZuul.game.inventory);

        for (int i = 0; i < currentRoom.trash.size(); i++) {
            Trash currentTrash = (Trash) currentRoom.trash.values().toArray()[i];

            arrayImage[i].setId(currentTrash.getTrashType().toString());

            File currentImage = new File("file:///" + System.getProperty("user.dir")
                    + "/src/main/java/com/mycompany/JavaBilleder1/" + currentTrash.toString() + ".png");

            arrayImage[i].setImage(new Image(currentImage.getPath()));

            arrayImage[i].setLayoutX(new Random().nextInt((bX - paneX) + 1) + paneX);
            arrayImage[i].setLayoutY(new Random().nextInt((bY - paneY) + 1) + paneY);
        }
        
        moveTxt.setText("Besøg: " + WorldOfZuul.game.getMoves());
        scoreTxt.setText("Point: " + WorldOfZuul.game.getScoreCounter().getScore());
        sorterTxt.setText("");
    }

    private void goHjemAction(ActionEvent event) throws IOException {
        WorldOfZuul.game.play(new Command(CommandWord.GO, "hjem", "", ""));
        App.setRoot("Hjem");
    }

    @FXML
    private void goByenAction(ActionEvent event) throws IOException {
        WorldOfZuul.game.play(new Command(CommandWord.GO, "byen", "", ""));
        App.setRoot("Byen");
    }

    @FXML
    private void invClicked1(MouseEvent event) {
        WorldOfZuul.game.inventory.slotSelectedHandler(event);
    }

    @FXML
    private void invClicked2(MouseEvent event) {
        WorldOfZuul.game.inventory.slotSelectedHandler(event);
    }

    @FXML
    private void trashClicked(MouseEvent event) {
        Room currentRoom = WorldOfZuul.game.getCurrentRoom();

        ArrayList<Trash> list = new ArrayList<>(currentRoom.trash.values());

        WorldOfZuul.game.inventory.trashClicked(event, list, inv11, inv22);
    }

}
