package com.mycompany.worldofzuulgui;

import DataLayer.ScoreLogger;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public final class Game {

    private final Parser parser;

    private Room currentRoom;
    private HashMap<String, Room> rooms;

    private int moves;
    public final Inventory inventory;
    private final ScoreCounter score;
    private final ScoreLogger logger;

    private final HashMap<String, TrashCan> trashCans;

    public Game() {

        // start med at lave spillerens inventory
        inventory = new Inventory();

        // så snart constructoren bliver kaldt, vil det første være at lave de rooms, som vi har bestemt i spillet
        createRooms();

        // derefter, skal vi initialisere vores parser, score og de forskellige skraldespande
        parser = new Parser();
        logger = new ScoreLogger();
        score = new ScoreCounter(logger);
        trashCans = new HashMap<>();

        // her vil der blive lavet de forskellige skraldesprande
        // det virker på den måde, at vi sætter parametrene for hvad skraldespande kan "spise".
        // eksempelvis med en skraldespand der kun accepterer mad, vil vi benytte enumen FOOD
        // vi laver en ArrayList af de typer, som skraldespanden skal acceptere
        ArrayList<TrashType> foodTrashType = new ArrayList<>();

        // tilføjer dem til vores array
        foodTrashType.add(TrashType.FOOD);

        // og derved opretter vores skraldespand
        TrashCan food = new TrashCan("madaffald", foodTrashType, score);

        // dette gør vi indtil vi har 4 forskellige skraldespande, der kan acceptere forskellige typer af skrald
        // eftersom vores spil omhandler at sortere at skrald
        ArrayList<TrashType> metalTrashType = new ArrayList<>();
        metalTrashType.add(TrashType.PLASTIC);
        metalTrashType.add(TrashType.METAL);
        metalTrashType.add(TrashType.GLAS);

        TrashCan metalplastCan = new TrashCan("metal_glas_plast", metalTrashType, score);

        ArrayList<TrashType> papirPapTrashType = new ArrayList<>();
        papirPapTrashType.add(TrashType.PAPER);
        papirPapTrashType.add(TrashType.CARDBOARD);

        TrashCan papirPapCan = new TrashCan("papir_pap", papirPapTrashType, score);

        ArrayList<TrashType> pantTrashType = new ArrayList<>();
        pantTrashType.add(TrashType.PANT);

        TrashCan pantCan = new TrashCan("pant", pantTrashType, score);

        ArrayList<TrashType> restAffaldType = new ArrayList<>();
        restAffaldType.add(TrashType.REST);

        TrashCan restAffaldCan = new TrashCan("rest_affald", restAffaldType, score);

        // efter vi har oprettet de forskellige skraldespande, tilføjer vi dem til en HashMap
        // key er naven på skraldespanden og valuen er vores objekt
        trashCans.put(food.toString(), food);
        trashCans.put(metalplastCan.toString(), metalplastCan);
        trashCans.put(papirPapCan.toString(), papirPapCan);
        trashCans.put(pantCan.toString(), pantCan);
        trashCans.put(restAffaldCan.toString(), restAffaldCan);
    }

    private void createRooms() {
        // vi frem deklarere vores room
        Room park, hjem, byen, fodboldbanen, stranden, gaden;

        // da vi vil gerne tilgå vores rooms senere hen i andre metoder, opretter vi en Hashmap over de rooms.
        rooms = new HashMap<>();

        // først opretter vi parken med en beskrivelse "i parken"
        park = new Room("i parken");

        // vi opretter også spawner mekanismen i denne metode
        // det vil sige, vi tager alle vores items i spillet, og sætter dem i en HashMap som har en string og en ArrayList.
        // data strukturen vil se ud således { "FOOD", { "BANANA", "APPLE" ... } }
        // på den måde, kan vi nemt tilgå alle de items i spillet der indgår i den type af items
        // eksempelvis, i mad kategorien, har vi banan, æble osv, mens i metal kategorien har vi søm og en dåse
        // det her gør vi for hver kategori af items, henvis til TrashType.java.
        // først opretter vi en ArrayList over TrashType
        ArrayList<TrashType> foodTypes = new ArrayList<>();
        foodTypes.add(TrashType.BANANA);
        foodTypes.add(TrashType.APPLE);
        foodTypes.add(TrashType.PIZZA);
        foodTypes.add(TrashType.AVOCADO);
        foodTypes.add(TrashType.POTATO);

        ArrayList<TrashType> plasticTypes = new ArrayList<>();
        plasticTypes.add(TrashType.PLASTBUCKET);
        plasticTypes.add(TrashType.PLASTSHOVEL);

        ArrayList<TrashType> metalTypes = new ArrayList<>();
        metalTypes.add(TrashType.NAILS);

        ArrayList<TrashType> glassTypes = new ArrayList<>();
        glassTypes.add(TrashType.GLASSBOTTLE);

        ArrayList<TrashType> paperCardboard = new ArrayList<>();
        paperCardboard.add(TrashType.BEERFRAME);
        paperCardboard.add(TrashType.PAPER);
        paperCardboard.add(TrashType.PAPKASSE);

        ArrayList<TrashType> pantTypes = new ArrayList<>();
        pantTypes.add(TrashType.PLASTPANT);
        pantTypes.add(TrashType.CAN);

        ArrayList<TrashType> restType = new ArrayList<>();
        restType.add(TrashType.JUICE);
        restType.add(TrashType.PIZZACONTAINER);
        restType.add(TrashType.MILK);
        restType.add(TrashType.PLASTBAG);

        // efter vi har oprettet alle vores items i de forskellige typer af affald
        // så tilføjer vi dem til hver af de forskellige rooms
        park.addTrashType(TrashType.FOOD, foodTypes);
        park.addTrashType(TrashType.PLASTIC, plasticTypes);
        park.addTrashType(TrashType.METAL, metalTypes);
        park.addTrashType(TrashType.GLAS, glassTypes);
        park.addTrashType(TrashType.PAPER, paperCardboard);
        park.addTrashType(TrashType.CARDBOARD, paperCardboard);
        park.addTrashType(TrashType.PANT, pantTypes);
        park.addTrashType(TrashType.REST, restType);

        hjem = new Room("derhjemme");

        // vi gør dette for at begrænse, hvad der kan spawne i et room
        // eksempelvis, i hjemmet kan der spawne mad, plastik og pant
        hjem.addTrashType(TrashType.FOOD, foodTypes);
        hjem.addTrashType(TrashType.PLASTIC, plasticTypes);
        hjem.addTrashType(TrashType.PANT, pantTypes);

        byen = new Room("i byen");

        // mens for eksempelvis i byen, kan der spawne flere ting
        byen.addTrashType(TrashType.FOOD, foodTypes);
        byen.addTrashType(TrashType.PLASTIC, plasticTypes);
        byen.addTrashType(TrashType.METAL, metalTypes);
        byen.addTrashType(TrashType.GLAS, glassTypes);
        byen.addTrashType(TrashType.PAPER, paperCardboard);
        byen.addTrashType(TrashType.CARDBOARD, paperCardboard);
        byen.addTrashType(TrashType.PANT, pantTypes);
        byen.addTrashType(TrashType.REST, restType);

        fodboldbanen = new Room("på fodboldbanen");

        fodboldbanen.addTrashType(TrashType.FOOD, foodTypes);
        fodboldbanen.addTrashType(TrashType.PLASTIC, plasticTypes);
        fodboldbanen.addTrashType(TrashType.METAL, metalTypes);
        fodboldbanen.addTrashType(TrashType.GLAS, glassTypes);
        fodboldbanen.addTrashType(TrashType.PAPER, paperCardboard);
        fodboldbanen.addTrashType(TrashType.CARDBOARD, paperCardboard);
        fodboldbanen.addTrashType(TrashType.PANT, pantTypes);
        fodboldbanen.addTrashType(TrashType.REST, restType);

        stranden = new Room("på stranden");
        stranden.addTrashType(TrashType.FOOD, foodTypes);
        stranden.addTrashType(TrashType.PLASTIC, plasticTypes);
        stranden.addTrashType(TrashType.METAL, metalTypes);
        stranden.addTrashType(TrashType.GLAS, glassTypes);
        stranden.addTrashType(TrashType.PAPER, paperCardboard);
        stranden.addTrashType(TrashType.CARDBOARD, paperCardboard);
        stranden.addTrashType(TrashType.PANT, pantTypes);
        stranden.addTrashType(TrashType.REST, restType);

        gaden = new Room("i gågaden");
        gaden.addTrashType(TrashType.FOOD, foodTypes);
        gaden.addTrashType(TrashType.PLASTIC, plasticTypes);
        gaden.addTrashType(TrashType.METAL, metalTypes);
        gaden.addTrashType(TrashType.GLAS, glassTypes);
        gaden.addTrashType(TrashType.PAPER, paperCardboard);
        gaden.addTrashType(TrashType.CARDBOARD, paperCardboard);
        gaden.addTrashType(TrashType.PANT, pantTypes);
        gaden.addTrashType(TrashType.REST, restType);

        hjem.setExit("parken", park);
        hjem.setExit("byen", byen);
        hjem.setExit("fodboldbanen", fodboldbanen);
        hjem.setExit("stranden", stranden);

        byen.setExit("hjem", hjem);
        byen.setExit("gaden", gaden);

        park.setExit("hjem", hjem);

        fodboldbanen.setExit("hjem", hjem);

        stranden.setExit("hjem", hjem);

        gaden.setExit("byen", byen);

        currentRoom = hjem;

        // efter vi har sat opsat vores spawn mekanisme
        // så spawner vi nogle items så snart spillet startet
        park.spawnTrash(inventory);
        byen.spawnTrash(inventory);
        fodboldbanen.spawnTrash(inventory);
        stranden.spawnTrash(inventory);
        gaden.spawnTrash(inventory);

        // efter vi har oprettet alt der er relateret til vores rooms
        // sætter vi dem ind i vores HashMap, så vi kan nemt tilgå rooms senere
        rooms.put("hjem", hjem);
        rooms.put("parken", park);
        rooms.put("byen", byen);
        rooms.put("fodboldbanen", fodboldbanen);
        rooms.put("stranden", stranden);
        rooms.put("gaden", gaden);
    }

    public void play(Command command) {
        // finished vil kun blive sat til true, hvis "afslut" bliver kaldt som vil derved sætte finished til true
        processCommand(command);
    }

    public void printWelcome() {
        // alert bliver benyttet til at "stoppe" vores spil, og dermed vise information til spilleren
        // i dette tilfælde, viser vi velkommen beskeden til spilleren
        // for at spilleren skal komme videre, skal man trykke på ok
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.getDialogPane().setMinWidth(575);
        alert.setHeaderText("Velkommen til spillet The Recycle Adventurer!");

        // her bestemmer vi om spilleren har spillet spillet før
        String scoreText;
        
        // loadHighestScore metoden vil retunere et tal over 0
        // eftersom det er meget usandsynligt at spilleren kan få en score på 0
        // derfor hvis der ikke findes nogle score så vil metoden retunere 0        
        if (score.getLogger().loadHighestScore() == 0) {
            scoreText = "Du har ingen gemte score!";
        } else {
            scoreText = "Lavest antal besøg: " + score.getLogger().loadHighestScore();
        }

        alert.setContentText("Spillet vil lære dig at sortere affald korrekt.\n"
                + "Du starter med 20 point. Dit mål er at opnå 150 point.\n"
                + "Du opnår 10 point ved at sortere et stykke affald korrekt, og du mister 15 point ved forkert sortering.\n"
                + "Din mission er, at udforske de forskellige rum, og at samle skrald og derefter sortere det.\n"
                + "Du kan have ét stykke affald i hver hånd, og du sorterer i hjemmet. God fornøjelse!\n"
                + currentRoom.getLongDescription() + "\n\n"
                + scoreText);

        // vent med at køre indtil spilleren har trykket ok
        alert.showAndWait();
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        // vi tager commandword som er det første der skrives af spilleren
        CommandWord commandWord = command.getCommandWord();

        // vi har en switch statement her, der vil bestemme hvad der skal blive kørt baseret på hvad spilleren har indtastet
        // eksempelvis, hvis spilleren indtaster "hjælp", så vil funktionen "printHelp()" blive kørt
        // og koden vil gå ud af den switch statement.
        switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case THROWOUT:
                throwOut(command);
                break;
            case PICKUP:
                pickUp(command);
                break;
            case INVENTORY:
                inventory.printInventory();
                break;
            case TRASH:
                currentRoom.printTrash();
                break;
            default:
                System.out.println("Hvad mener du?");
                break;
        }

        return wantToQuit;
    }

    private void pickUp(Command command) {
        // vi skal være sikker på at spilleren har specificeret hvilken item spilleren vil tage up
        // dette gøres med en if statement
        // hvis der er et andet ord vil dette blive sprunget over
        if (!command.hasSecondWord()) {
            System.out.println("Tag hvad?");
        } else {

            // hvis spilleren har indtastet hvilken item man vil samle op
            String targetTrash = command.getSecondWord();

            // vi skal dog væres sikre på at det skrald overhovedet eksistere i rummet
            if (!currentRoom.trash.containsKey(targetTrash)) {
                System.out.printf("%s eksisterer ikke i rummet!%n", targetTrash);
                return;
            }

            // hvis det gør så tilføjer vi det til spillerens inventory
            if (inventory.addTrash(currentRoom, currentRoom.trash.get(targetTrash))) {
                // og fjerner det fra rooms "inventory"
                currentRoom.trash.remove(targetTrash);
                System.out.printf("Du har taget %s op i hænderne!%n", targetTrash.toLowerCase());
            }
        }
    }

    private void printHelp() {
        System.out.println("Du har kaldt efter hjælp!\n"); // \n = new line
        System.out.println("Dine muligheder er:");
        parser.showCommands();
    }

    private void throwOut(Command command) {
        // vi tjekker om spilleren overhovedet befinder sig derhjemme
        if (!currentRoom.getShortDescription().contains("derhjemme")) {
            System.out.println("Du skal være hjemme for at kunne sortere dit affald!");
            return;
        }

        // vi skal også være sikre på at spilleren har overhovedet specificeret hvilken skraldespand der skal smides ud i
        if (!command.hasSecondWord()) {
            System.out.println("Smid hvor hen?");
            return;
        }

        // hvis spilleren har det, så bruger vi det ord som den skraldespand spilleren vil smide affald ud i
        String targetTrashCan = command.getSecondWord();

        // når vi har gjort det, kan vi ved hjælp af HashMapen tilgå vores skraldesprand objekt
        TrashCan currentTrashCan = trashCans.get(targetTrashCan);

        if (currentTrashCan == null) {
            System.out.println("Hov! Den skraldespand findes ikke!");
            return;
        }

        // vi skal være sikre på at spilleren har specificeret hvilket skrald spilleren vil smide ud
        if (!command.hasThirdWord()) {
            System.out.println("Smid hvad ud?");
        }

        // vi skal også finde ud af, hvad spilleren vil smide ud og dette gøres ved det tredje argument
        String targetTrash = command.getThirdWord();

        // ved hjælp af det, skal vi finde det objekt der tilhører det skrald vi har taget op tidligere og vil gerne smide ud
        Trash currentTrash = inventory.trash.get(targetTrash);

        if (currentTrash == null) {
            System.out.println("Hov! Det har du ikke i hænderne!");
            return;
        }

        // hvis det hele er som det skal, så tilføjer vi bare det sbbkrald ind i vores skraldespands "inventory"
        if (currentTrashCan.addTrash(inventory, currentTrash, score)) {
            System.out.printf("Tillykke. Du har fået point! Du har sorteret korrekt.\nDin score er nu: %d%n", score.getScore());
        }
    }

    private void goRoom(Command command) {

        if (!command.hasSecondWord()) {
            System.out.println("Besøg hvad?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Du kan ikke gå den vej");
        } else {
            currentRoom = nextRoom;
            inventory.currentSelectedSlot = null;
            moves++;
        }
    }

    private boolean quit(Command command) {
        // det her vil lukke vores spil og dermed vil spillet afslutte
        // dette kan blive kaldt hvis spilleren enten taber eller vinder
        System.exit(0);
        return true;
    }

    public int getMoves() {
        return moves;
    }

    public ScoreCounter getScoreCounter() {
        return score;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public HashMap<String, TrashCan> getTrashCans() {
        return trashCans;
    }

    public boolean recycleTrash(MouseEvent event, Text text, ImageView inv11, ImageView inv22) {
        for (String item : inventory.trash.keySet()) {
            System.out.println("Trash: " + inventory.trash.get(item).toString());
        }
        
        // befinder spilleren sig derhjemme?
        if (!currentRoom.getShortDescription().contains("derhjemme")) {
            System.out.println("Du skal være hjemme for at kunne sortere dit affald!");
            return false;
        }

        // vi skal også tjekke om spilleren har valgt en gesntand fra inventaren
        if (inventory.currentSelectedSlot == null) {
            System.out.println("Du har ikke valgt en genstand!");
            return false;
        }

        // Ved hjælp af ImageView id, kan vi bestemme hvilken skraldespand spilleren har trykket på
        ImageView currentTrashIv = (ImageView) event.getSource();

        // eftersom vi har en hashmap over de skraldespand derhjemme, kan vi hurtigt tilgå dem
        TrashCan currentTrashCan = trashCans.get(currentTrashIv.getId());

        // et tjek der forhindre en NullPointerException
        if (currentTrashCan == null) {
            return false;
        }

        // få den nuværende valgt genstand fra spillerens inventar
        ImageView slot = inventory.currentSelectedSlot;

        // tilgå trash objektet ved hjælp af ImageView id
        Trash trash = inventory.trash.get(slot.getId());

        // der benyttes en boolean her for at gøre det muligt at tjekke en spillers score
        boolean sortedCorrectly = false;
        
        // vi genbruger addTrash metoden fra tidligere iteration af spillet
        // hvis det skrald som spilleren har valgt bliver tilføjet til skraldespands inventar
        // vil koden der ordner spillerens inventar blive kørt
        if (currentTrashCan.addTrash(inventory, trash, getScoreCounter())) {
            
            // vi tjekker overhovedet om vores skraldespand acceptere denne skraldetype
            if (currentTrashCan.containsTrashType(trash.getTrashType())) {
                
                // hvis den gør så tjekker vi hvilken genstand spilleren har valgt
                if (inv11 != null && slot.getId().equals(inv11.getId())) {
                    inv11.setVisible(false);
                    inv11 = null;
                } else if (inv22 != null && slot.getId().equals(inv22.getId())) {
                    inv22.setVisible(false);
                    inv22 = null;
                }

                if (inventory.firstSlot != null
                        && slot.getId().equals(inventory.firstSlot.getId())) {
                    inventory.firstSlot.setVisible(false);
                    inventory.firstSlot = null;
                } else if (inventory.secondSlot != null
                        && slot.getId().equals(inventory.secondSlot.getId())) {
                    inventory.secondSlot.setVisible(false);
                    inventory.secondSlot = null;
                }

                // når spilleren har sorteret noget skrald så fjerner vi glow effekten
                inventory.currentSelectedSlot.setEffect(null);
                inventory.currentSelectedSlot = null;

                // vi sætter også sorterTxt
                text.setText("Tillykke! Du har sorteret korrekt! Du har fået 10 point");

                // derudover skal vi også retunere true hvis spilleren har sorteret korrekt
                sortedCorrectly = true;
            }
        } else {
            // hvis spilleren ikke lige fik sorteret korrekt
            text.setText("Desværre. Du har sorteret forkert. Du har mistet 15 point!");
            sortedCorrectly = false;
        }

        // dermed skal vi også tjekke spillerens score her
        // på den måde kan vi også tjekke om spilleren er indenfor en specifik threshold
        // hvis spilleren har en score >= 150 vil spilleren vinde
        // hvis spilleren har en score <= 0 vil spilleren tabe
        if (score.getScore() >= 150) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Tillykke!");
            alert.setContentText("Du har vundet. Fortsæt det gode arbejde!");
            alert.showAndWait();

            score.getLogger().saveScore(moves);

            play(new Command(CommandWord.QUIT, "", "", ""));

            return false;
        } else if (score.getScore() <= 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Desværre!");
            alert.setContentText("Du har tabt. Prøv igen!");
            alert.showAndWait();

            play(new Command(CommandWord.QUIT, "", "", ""));

            return false;
        }

        return sortedCorrectly;
    }

}
