package com.mycompany.worldofzuulgui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Inventory {

    // Hashmap til opbevaring af skraldeobjekter i spillerens inventar
    public HashMap<String, Trash> trash;

    public ImageView firstSlot;
    public ImageView secondSlot;
    private ArrayList<ImageView> inventory;

    public ImageView currentSelectedSlot;

    // Tilføj skrald fra rummet til spillerens inventar
    public boolean addTrash(Room room, Trash trash) {
        // Check om rummet indeholder skraldet
        if (room.trash.containsValue(trash)) {
            // Check om spilleren har plads
            if (this.trash.size() < 2) {
                this.trash.put(trash.toString(), trash);
                return true;
            } else {
                System.out.println("Du har ikke mere plads i hænderne!");
                return false;
            }
        }
        return false;
    }

    public boolean addTrash(Trash trash) {
        if (this.trash.containsKey(trash.toString())) {
            System.out.println("Din inventory indeholder allerede denne skraldtype.");
            return false;
        }

        if (this.trash.size() < 2) {
            this.trash.put(trash.toString(), trash);
            return true;
        } else {
            System.out.println("Du har ikke mere plads i hænderne!");
            return false;
        }
    }

    // Opret trash-hashmappet i constructeren
    public Inventory() {
        trash = new HashMap<>();
    }

    // Print alle navne på skrald i spillerens inventory
    public void printInventory() {
        System.out.println("-----Inventory-----");

        for (String trash : trash.keySet()) {
            System.out.println(trash);
        }

        System.out.println("-------------------");
    }

    // Trash clicked from fxml
    public void trashClicked(MouseEvent event, ArrayList<Trash> trashList, ImageView inv1, ImageView inv2) {
        // ved hjælp af JavaFX event, kan vi få den oprindelige ImageView som spilleren har trykket på
        ImageView image = (ImageView) event.getSource();

        // vi kører igennem den nuværende array af genstande i rummet
        for (int i = 0; i < trashList.size(); i++) {
            // vi tjekker om typen af skrald er det samme
            if (trashList.get(i).getTrashType().toString().equals(image.getId())) {
                image.setVisible(!WorldOfZuul.game.inventory.addTrash(trashList.get(i)));
            }
        }

        // når vi har gjort det så skal vi reloade spillerens inventar så vi kan vise det til skærmen
        WorldOfZuul.game.inventory.reloadInv(inv1, inv2);
    }

    // Reload gui
    public void reloadInv(ImageView btn1, ImageView btn2) {
        // hvis vi ikke tjekker om vores knapper er null
        // fordi ellers vil vi få en NullPointerException
        if (btn1 != null || btn2 != null) {
            ImageView invBtns[] = new ImageView[]{btn1, btn2};

            invBtns[0].setVisible(false);
            invBtns[1].setVisible(false);

            int i = 0;
            for (String selectedTrash : trash.keySet()) {
                invBtns[i].setVisible(true);

                File currentImage = new File("file:///" + System.getProperty("user.dir")
                        + "/src/main/java/com/mycompany/JavaBilleder1/" + trash.get(selectedTrash).toString() + ".png");
                invBtns[i].setImage(new Image(currentImage.getPath()));
                invBtns[i].setId(trash.get(selectedTrash).toString());

                i++;
            }

            firstSlot = invBtns[0];
            secondSlot = invBtns[1];
        }
    }

    public void slotSelectedHandler(MouseEvent event) {
        // tjekker igen om noget af ImageViews er null
        // ellers vil vi få NullPointerExceptions
        if (firstSlot != null) {
            firstSlot.setEffect(null);
        }
        if (secondSlot != null) {
            secondSlot.setEffect(null);
        }

        currentSelectedSlot = (ImageView) event.getSource();

        // vi sætter en effekt på den genstand som spilleren har valgt i inventaren
        DropShadow ds = new DropShadow(10, Color.YELLOW);
        currentSelectedSlot.setEffect(ds);
    }
}
