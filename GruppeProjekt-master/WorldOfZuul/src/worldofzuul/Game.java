package worldofzuul;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private final Parser parser;

    private Room currentRoom;
    private HashMap<String, Room> rooms;

    private int moves;
    private final Inventory inventory;
    private final ScoreCounter score;

    private final HashMap<String, TrashCan> trashCans;

    public Game() {
        
        // så snart constructoren bliver kaldt, vil det første være at lave de rooms, som vi har bestemt i spillet
        createRooms();
        
        // derefter, skal vi initialisere vores parser, inventory, score og de forskellige skraldespande
        parser = new Parser();
        inventory = new Inventory();
        score = new ScoreCounter();
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
        Room park, hjem, byen, fodboldbanen;

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
        plasticTypes.add(TrashType.PLAST);
        plasticTypes.add(TrashType.PLASTBUCKET);
        plasticTypes.add(TrashType.PLASTSHOVEL);

        ArrayList<TrashType> metalTypes = new ArrayList<>();
        metalTypes.add(TrashType.NAILS);
        metalTypes.add(TrashType.CAN);

        ArrayList<TrashType> glassTypes = new ArrayList<>();
        glassTypes.add(TrashType.GLASSBOTTLE);

        ArrayList<TrashType> paperCardboard = new ArrayList<>();
        paperCardboard.add(TrashType.BEERFRAME);

        ArrayList<TrashType> pantTypes = new ArrayList<>();
        pantTypes.add(TrashType.PLASTPANT);
        pantTypes.add(TrashType.CANPANT);
        
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

        hjem.setExit("parken", park);
        hjem.setExit("byen", byen);
        hjem.setExit("fodboldbanen", fodboldbanen);

        byen.setExit("hjem", hjem);

        park.setExit("hjem", hjem);

        fodboldbanen.setExit("hjem", hjem);

        currentRoom = hjem;

        // efter vi har sat opsat vores spawn mekanisme
        // så spawner vi nogle items så snart spillet startet
        park.spawnTrash();
        byen.spawnTrash();
        fodboldbanen.spawnTrash();

        // efter vi har oprettet alt der er relateret til vores rooms
        // sætter vi dem ind i vores HashMap, så vi kan nemt tilgå rooms senere
        rooms.put("hjem", hjem);
        rooms.put("parken", park);
        rooms.put("byen", byen);
        rooms.put("fodboldbanen", fodboldbanen);
    }

    public void play() {
        // Vores welcome message bliver printet så snart vi kalder metoden fra main
        printWelcome();

        // vi opretter en boolean variable, som vil styre vores while loop
        // i dette tilfælde, vil loopet kører indtil finished vil blive sat til true
        boolean finished = false;
        while (!finished) {
            if (score.getScore() >= 100) {
                System.out.println("Tillykke! Du har vundet. Fortsæt det gode arbejde!");
                break;
            } else if (score.getScore() <= 0) {
                System.out.println("Desværre! Du har tabt. Prøv igen!");
                break;
            }
            
            Command command = parser.getCommand();
            
            // finished vil kun blive sat til true, hvis "afslut" bliver kaldt som vil derved sætte finished til true
            finished = processCommand(command);
        }
        //System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Velkommen til spillet The Recycle Adventurer!");
        System.out.println("Spillet vil lære dig at sortere affald korrtekt.");
        System.out.println("Værsgo, du starter med 20 point. Dit mål er at opnå 100 point.");
        System.out.println("Du opnår 10 point ved at sortere et stykke affald korrekt, og du mister 15 point ved forkert sortering.");
        System.out.println("Din mission er, at udforske de forskellige rum, og at samle skrald og derefter sortere det.");
        System.out.println("Du kan have ét stykke affald i hver hånd, og du sorterer i hjemmet");
        System.out.println("Du kan få hjælp, ved at skrive 'hjælp' i konsollen. God jagt!");
        System.out.println("Skriv '" + CommandWord.HELP + "' for at se alle kommandoer.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        // funktionen her har til opgave at vælge de forskellige commands i spillet
        
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
            System.out.println(currentRoom.getLongDescription());

            // hvis spilleren befinder sig derhjemme, kan spilleren sortere sit affald
            // for at vise hvilke muligheder spilleren har, printer vi alle skraldespandene ud
            if (currentRoom.getShortDescription().contains("derhjemme")) {
                System.out.println("\n----- Skraldespande -----");
                for (String can : trashCans.keySet()) {
                    System.out.println(can);
                }
                System.out.println("-------------------------");
            }

            // hvis der er affald i rummet, så printer vi det
            if (!currentRoom.trash.isEmpty()) {
                currentRoom.printTrash();
            }

            // her er vores spawner af skrald
            // det vil sige, for hvert 3 skridt spilleren tager, vil der spawne skrald i hvert rum
            if (moves % 3 == 0) {
                for (String room : rooms.keySet()) {
                    rooms.get(room).spawnTrash();
                }
            }

            // hver gang spilleren bevæger sig fra rum til rum
            moves++;
            score.printScore();
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Vil du ikke spille mere? :-(\nSå skal du bare skrive afslut.");
            return false;
        } else {
            return true;
        }
    }
}
