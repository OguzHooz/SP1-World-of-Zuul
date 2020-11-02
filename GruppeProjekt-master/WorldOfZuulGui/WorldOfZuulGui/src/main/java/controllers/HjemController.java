package com.mycompany.worldofzuulgui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HjemController implements Initializable {

    @FXML
    private Button goParken;
    @FXML
    private Button goByen;
    @FXML
    private Button goFodboldbanen;
    @FXML
    private Pane spawnPane;
    @FXML
    private ImageView inv11;
    @FXML
    private ImageView inv22;
    @FXML
    private Button goStranden;
    @FXML
    private ImageView background;
    @FXML
    private ImageView pant;
    @FXML
    private ImageView metal_glas_plast;
    @FXML
    private ImageView papir_pap;
    @FXML
    private ImageView madaffald;
    @FXML
    private ImageView rest_affald;
    private ImageView mad;
    @FXML
    private Text moveTxt;
    @FXML
    private Text scoreTxt;
    @FXML
    private Text sorterTxt;
    @FXML
    private ImageView f1;
    @FXML
    private ImageView f2;
    @FXML
    private ImageView f3;

    private ImageView[] arrayImage;

    int bX = 763;
    int bY = 159;

    // hver gang vi kommer ind i rummet, vil denne metode blive kørt
    @Override
    public void initialize(URL url, ResourceBundle rb) {               
        // reload spillerens inventar hver gang spilleren kommer ind i rummet
        WorldOfZuul.game.inventory.reloadInv(inv11, inv22);

        // på grund af JavaFX er det mere consistent at vi loader baggrunden hver gang vi kommer ind i rummet
        // den eneste ulempe ved det her er performance
        // hver gang så skal JVM'en kører gennem de her directories og finde vores fil
        File backgroundImage = new File("file:///" + System.getProperty("user.dir")
                + "/src/main/java/com/mycompany/JavaBilleder1/HjemFinal4.png");

        // derved skal vi også sætte billedet på vores ImageView
        background.setImage(new Image(backgroundImage.getPath()));

        // vi benytter DropShadows her for at vise hvilken skraldespand er blevet selected
        // det her er egentlig bare QoL (Quality of Life) feature som egentlig ikke har nogen funktion
        DropShadow hover = new DropShadow(15, Color.YELLOW);

        // ligesom med baggrundensbilledet sætter vi billederne for skraldespandene
        File metalGlasPlast = new File("file:///" + System.getProperty("user.dir")
                + "/src/main/java/com/mycompany/JavaBilleder1/BlaaSkraldespand.png");
        metal_glas_plast.setImage(new Image(metalGlasPlast.getPath()));

        // vi laver en array med 3 ImageViews så vi kan iterere igennem istedet for at sætte hver enkel
        arrayImage = new ImageView[]{f1, f2, f3};

        // for at få det nuværende skrald i rummet, skal vi benytte det nuværende rum 
        Room currentRoom = WorldOfZuul.game.getCurrentRoom();

        // for at få det til at være fuldstændig tilfædigt sletter vi alle elementer i HashMapen
        currentRoom.trash.clear();

        // dermed spawner vi nyt skrald 
        currentRoom.spawnTrash(WorldOfZuul.game.inventory);

        // vi kører igennem alle de skrald der findes i rummet
        for (int i = 0; i < currentRoom.trash.size(); i++) {
            // vi konvertere vores HashMap til en fuldstændig ordinær array
            // og tilgår værdien ved hjælp af [i] ligesom med almene arrays
            Trash currentTrash = (Trash) currentRoom.trash.values().toArray()[i];

            // vi sætter også id'en for det billedet for at gøre det nemmere i fremtiden 
            // når vi skal sortere genstandene
            arrayImage[i].setId(currentTrash.getTrashType().toString());

            // på den måde kan vi også finde de sprites hver item har
            // vi skal være sikre på at filerne hedder det samme ligesom vores enums
            File currentImage = new File("file:///" + System.getProperty("user.dir")
                    + "/src/main/java/com/mycompany/JavaBilleder1/" + currentTrash.toString() + ".png");

            // sætter også billedet for den genstand
            arrayImage[i].setImage(new Image(currentImage.getPath()));

            // JVMen bestemmer en tilfædig værdi for både X og Y position
            arrayImage[i].setLayoutX(new Random().nextInt(bX));
            arrayImage[i].setLayoutY(new Random().nextInt(bY));
        }

        // setOnMouseEntered event handleren vil kører når musen er overfor skraldespanden
        // i dette tilfælde vil den sætte en effekt på den skraldespand som spilleren har musen over
        // dette er også en QoL feature og er dermed ikke nødvendig
        metal_glas_plast.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                metal_glas_plast.setEffect(hover);
            }
        });

        metal_glas_plast.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                metal_glas_plast.setEffect(null);
            }
        });

        File papPair = new File("file:///" + System.getProperty("user.dir")
                + "/src/main/java/com/mycompany/JavaBilleder1/RoedSkraldespand.png");
        papir_pap.setImage(new Image(papPair.getPath()));

        papir_pap.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                papir_pap.setEffect(hover);
            }
        });

        papir_pap.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                papir_pap.setEffect(null);
            }
        });

        File madSkraldespand = new File("file:///" + System.getProperty("user.dir")
                + "/src/main/java/com/mycompany/JavaBilleder1/GulSkraldespand.png");
        madaffald.setImage(new Image(madSkraldespand.getPath()));

        madaffald.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                madaffald.setEffect(hover);
            }
        });

        madaffald.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                madaffald.setEffect(null);
            }
        });

        File restSkraldespand = new File("file:///" + System.getProperty("user.dir")
                + "/src/main/java/com/mycompany/JavaBilleder1/TyrkisSkraldespand.png");
        rest_affald.setImage(new Image(restSkraldespand.getPath()));

        rest_affald.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                rest_affald.setEffect(hover);
            }
        });

        rest_affald.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                rest_affald.setEffect(null);
            }
        });

        File pantPose = new File("file:///" + System.getProperty("user.dir")
                + "/src/main/java/com/mycompany/JavaBilleder1/PantPose.png");
        pant.setImage(new Image(pantPose.getPath()));

        pant.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                pant.setEffect(hover);
            }
        });
        pant.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                pant.setEffect(null);
            }
        });

        // hver gang spilleren kommer ind i hvilket som helst rum
        // sætter vi følgende tekst
        moveTxt.setText("Besøg: " + WorldOfZuul.game.getMoves());
        scoreTxt.setText("Point: " + WorldOfZuul.game.getScoreCounter().getScore());
        sorterTxt.setText("");
    }

    @FXML
    private void goParkenAction() throws IOException {
        WorldOfZuul.game.play(new Command(CommandWord.GO, "parken", "", ""));
        App.setRoot("Parken");
    }

    @FXML
    private void goByenAction() throws IOException {
        WorldOfZuul.game.play(new Command(CommandWord.GO, "byen", "", ""));
        App.setRoot("Byen");
    }

    @FXML
    private void goFodboldbanenAction() throws IOException {
        WorldOfZuul.game.play(new Command(CommandWord.GO, "fodboldbanen", "", ""));
        App.setRoot("Fodboldbanen");
    }

    @FXML
    private void goStrandenAction(ActionEvent event) throws IOException {
        WorldOfZuul.game.play(new Command(CommandWord.GO, "stranden", "", ""));
        App.setRoot("Stranden");
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
    private void metalGlasPlastClicked(MouseEvent event) {
        WorldOfZuul.game.recycleTrash(event, sorterTxt, inv11, inv22);
        scoreTxt.setText("Point: " + WorldOfZuul.game.getScoreCounter().getScore());
    }

    @FXML
    private void papPapirClicked(MouseEvent event) {
        WorldOfZuul.game.recycleTrash(event, sorterTxt, inv11, inv22);
        scoreTxt.setText("Point: " + WorldOfZuul.game.getScoreCounter().getScore());
    }

    @FXML
    private void madClicked(MouseEvent event) {
        WorldOfZuul.game.recycleTrash(event, sorterTxt, inv11, inv22);
        scoreTxt.setText("Point: " + WorldOfZuul.game.getScoreCounter().getScore());
    }

    @FXML
    private void restClicked(MouseEvent event) {
        WorldOfZuul.game.recycleTrash(event, sorterTxt, inv11, inv22);
        scoreTxt.setText("Point: " + WorldOfZuul.game.getScoreCounter().getScore());
    }

    @FXML
    private void pantClicked(MouseEvent event) {
        WorldOfZuul.game.recycleTrash(event, sorterTxt, inv11, inv22);
        scoreTxt.setText("Point: " + WorldOfZuul.game.getScoreCounter().getScore());
    }

    @FXML
    private void trashClicked(MouseEvent event) {
        Room currentRoom = WorldOfZuul.game.getCurrentRoom();

        ArrayList<Trash> list = new ArrayList<>(currentRoom.trash.values());

        WorldOfZuul.game.inventory.trashClicked(event, list, inv11, inv22);
    }
}
