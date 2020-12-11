package org.CrabGame;

public class Game {
    //opretter vores parser (tekstbaseret spil)
    private Parser parser;

    private static Room currentRoom;

    public Game(){
        //Kalder metode til at oprette vores rum
        createRooms();
        //opretter en parser til at forstå vores kommandoer
        parser = new Parser();
    }

    private void createRooms() {
        Room center, northwesternSea, northernSea, northeasternSea, westernSea, easternSea, southwesternSea, southernSea, southeasternSea;

        //Tilføjer descriptions til rooms
        center = new Room("In the middle of the sea", "center", 0.6, 0.02);
        northwesternSea = new Room("This is the upper left corner", "northwesternSea", 2, 0.08);
        northernSea = new Room("you are now up", "northernSea",1.2,0.04);
        northeasternSea = new Room("This is the upper right corner", "northeasternSea", 2, 0.08);
        westernSea = new Room("you are now to the left", "westernSea",1.2,0.04);
        easternSea = new Room("you are now to the right", "easternSea",1.2,0.04);
        southwesternSea = new Room("This is the lower right corner", "southwesternSea", 2, 0.08);
        southernSea = new Room("you are now down", "southernSea",1.2,0.04);
        southeasternSea = new Room("This is the lower left corner", "southeasternSea", 2, 0.08);

        //tilføjer exits til diverse rooms
        center.setExit("east", easternSea);
        center.setExit("south", southernSea);
        center.setExit("west", westernSea);
        center.setExit("north", northernSea);

        easternSea.setExit("west", center);
        easternSea.setExit("north", northeasternSea);
        easternSea.setExit("south", southeasternSea);

        westernSea.setExit("east", center);
        westernSea.setExit("north", northwesternSea);
        westernSea.setExit("south", southwesternSea);

        northernSea.setExit("east", northeasternSea);
        northernSea.setExit("south", center);
        northernSea.setExit("west", northwesternSea);

        southernSea.setExit("north", center);
        southernSea.setExit("east", southeasternSea);
        southernSea.setExit("west", southwesternSea);

        southwesternSea.setExit("east", southernSea);
        southwesternSea.setExit("north", westernSea);

        northwesternSea.setExit("east", northernSea);
        northwesternSea.setExit("south", westernSea);

        northeasternSea.setExit("south", easternSea);
        northeasternSea.setExit("west", northernSea);

        southeasternSea.setExit("west", southernSea);
        southeasternSea.setExit("north", easternSea);

        //sætter startstedet af spillet
        currentRoom = center;
        //currentroom is discovered
    }
    public void play(){
        printWelcome();
        printFoodList(currentRoom);
        //Tjekker alle commandoer på nedenstående class, hvis der skrevet quit spring over
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        //hvis spil afsluttet, udskriv denne linje
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        //velkomsttekst, udskriver hjælpe kommando, lang description for starterrum
        System.out.println();
        System.out.println("Welcome to The Crap game!");
        System.out.println("The Crap game is a new, incredibly educational adventure game.");
        System.out.println("The oceans keeps getting more polluted, because of the increased level of carbondioxide");
        System.out.println("It affects the food chain in the sea really negatively");
        System.out.println("You are a crap and have to catch some food to survive");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private void printFoodList(Room room) {
        StringBuilder listString = new StringBuilder("You can find: ");
        //loop through one of the coordinates (since they are put into array at the same point)
        for (int i = 0; i < room.getFoodCoordinatey().size(); i++) {
            if (i != 0){
                listString.append("; ");
            }
            listString.append(room.getFoodType().get(i)).append(" in: [y:").append(room.getFoodCoordinatey().get(i)).append(", x:").append(room.getFoodCoordinatex().get(i)).append("] Worth ").append(room.getFoodAmount().get(i)).append(" hunger");
        }
        System.out.println(listString + "\n");
        listString.setLength(0);
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        //hvis man har skrevet nogle ord der ikke er blandt enum, se ord i CommandWord
        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        //printer hjælp class nedefra
        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        //flytter rum
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }//flytter rundt inden i et rum
        else if (commandWord == CommandWord.WALK) {
            walkfunc(command);
        }
        //sætter wantToQuit til at TRUE, det ville sige vi afslutter spillet
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.FOODLIST){
            printFoodList(currentRoom);
        }
        return wantToQuit;
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }

    private String walkfunc(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Which way do you wanna walk?");
        } else if (command.getSecondWord().equals("north") && Player.position[1] != 5) {
            Player.moveYakse(1);
        } else if (command.getSecondWord().equals("east") && Player.position[0] != 5) {
            Player.moveXakse(1);
        } else if (command.getSecondWord().equals("south") && Player.position[1] != 0) {
            Player.moveYakse(-1);
        } else if (command.getSecondWord().equals("west") && Player.position[0] != 0) {
            Player.moveXakse(-1);
        } else {
            System.out.print("\nYou either wrote something wrong or reached the border of the 5x5 room\n");
        }
        for (int i = 0; i < currentRoom.getFoodCoordinatex().size(); i++){
            if (Player.getXkoordinat() == currentRoom.getFoodCoordinatex().get(i) && Player.getYkoordinat() == currentRoom.getFoodCoordinatey().get(i)){
                Player.setHunger(currentRoom.getFoodAmount().get(i));
                System.out.println("You have picked up " + currentRoom.getFoodType().get(i) + ", your hunger has increased by " + currentRoom.getFoodAmount().get(i) + " and your hunger is now " + Player.getHunger());
                currentRoom.getFoodCoordinatex().remove(i);
                currentRoom.getFoodCoordinatey().remove(i);
                currentRoom.getFoodAmount().remove(i);
                currentRoom.getFoodType().remove(i);
            }
        }
        return "unknown";
    }

    private void goRoom(Command command) {
        //Hvis du bare skriver "go" udskrives dette
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        //direction er det om man har brugt south,east,west,north når man opretter rummet
        String direction = command.getSecondWord();

        //gemmer det næste rum som det nuværende i forhold hvilket direction man har gået til
        Room nextRoom = currentRoom.getExit(direction);
        //hvis der ikke er en exit den vej man har skrevet
        if (nextRoom == null) {
            System.out.println("There is no room this way");
        } else if (!Player.onBorder()){
            System.out.println("You are too far from the border");
        }
        else { //hvis der en exit gem som nyt rum udskriv lang description
            //before new room save food list
            //check hvilken vej man er gået
            if (direction.equals("north") && Player.getYkoordinat() == 5) {
                Player.setYkoordinat(0);
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                //print foodlist
                printFoodList(currentRoom);
                currentRoom.isDiscovered = true;
            } else if (direction.equals("south") && Player.getYkoordinat() == 0) {
                Player.setYkoordinat(0);
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                //print foodlist
                printFoodList(currentRoom);
                currentRoom.isDiscovered = true;
            } else if (direction.equals("east") && Player.getXkoordinat() == 5) {
                Player.setXkoordinat(0);
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                //print foodlist
                printFoodList(currentRoom);
                currentRoom.isDiscovered = true;
            } else if (direction.equals("west") && Player.getXkoordinat() == 0) {
                Player.setXkoordinat(0);
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                //print foodlist
                printFoodList(currentRoom);
                currentRoom.isDiscovered = true;
            } else {
                System.out.println("You are too far from that border");
                return;
            }
        }
    }

    private void printHelp() {
        //udskriver hvor man er og hvilke kommandoer man kan bruge
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the depths of the seas.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Room currentRoom) {
        Game.currentRoom = currentRoom;
    }
}
