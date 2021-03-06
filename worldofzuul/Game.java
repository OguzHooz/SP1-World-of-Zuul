package worldofzuul;


import javax.swing.*;
import java.util.*;


public class Game
{
    //Her finder vi den som det teksbaseret spil skal forstå af kommandoer
    private Parser parser;
    //Gemmer det rum som man er i
    private Room currentRoom;



    public Game() 
    {
        //kalder funktionen nedenunder
        createRooms();
        //opretter ny parser til at forstå kommandoer
        parser = new Parser();
        generateFood();
    }


    private void createRooms()
    {
        //Opretter en del forskellige rooms med hver deres navn
        Room center, northwesternSea, northernSea, northeasternSea, westernSea, easternSea, southwesternSea, southernSea, southeasternSea;

        //Tilføjer descriptions til rooms
        center = new Room("in the middle of the sea");
        northwesternSea = new Room("in the upper left corner");
        northernSea = new Room("now up");
        northeasternSea = new Room("in the upper right corner");
        westernSea = new Room("to the left");
        easternSea = new Room("to the right");
        southwesternSea = new Room("in the lower right corner");
        southernSea = new Room(" now down");
        southeasternSea = new Room("in the lower left corner");

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
    }

    public void play() 
    {
        //Printer velkomst nedenfor
        printWelcome();

        //Er sikker på der altid er mad
        if (foodCoordinatex.size() == 0){
            generateFood();
        }
        //får settet af entries
        Set set1 = foodCoordinatex.entrySet();
        Set set2 = foodCoordinatey.entrySet();
        //sætter op en tæller
        Iterator i = set1.iterator();
        Iterator i2 = set2.iterator();
        while(i.hasNext()){
            //mapper entries
            Map.Entry me = (Map.Entry)i.next();
            Map.Entry me2 = (Map.Entry)i2.next();
            //udskriver entries
            System.out.println("You can find food in " + me.getValue() + ", " + me2.getValue() );
        }
        //Tjekker alle commandoer på nedenstående class, hvis der skrevet quit spring over
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        //hvis spil afsluttet, udskriv denne linje
        System.out.println("Thank you for playing.  Goodbye.");
    }

    private void printWelcome()
    {
        //velkomsttekst, udskriver hjælpe kommando, lang description for starterrum
        System.out.println();
        System.out.println("Welcome to The Crap game!");
        System.out.println("The Crap game is a new, incredibly educational adventure game.");
        System.out.println("The oceans keeps getting more polluted, because of the increased level of carbondioxide");
        System.out.println("It affects the food chain in the sea really negatively");
        System.out.println("You are a crab and have to catch some food to survive");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {

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
            //moveRoom(command.getSecondWord());
            goRoom(command);
        }
        //flytter rundt inden i et rum
        else if (commandWord == CommandWord.WALK) {
            walkfunc(command);
            System.out.println(Food.score);
            //får settet af entries
            Set set1 = foodCoordinatex.entrySet();
            Set set2 = foodCoordinatey.entrySet();
            //sætter op en tæller
            Iterator i = set1.iterator();
            Iterator i2 = set2.iterator();
            //TODO få det til at virke
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                Map.Entry me2 = (Map.Entry) i2.next();
                if (Player.xkoordinat == Integer.parseInt(me.getValue().toString()) && Player.ykoordinat == Integer.parseInt(me2.getValue().toString()) ){
                    Food.addScore(1);
                    System.out.println(Food.score + " you have gained a point");
                }
            }
        }

        //sætter wantToQuit til at TRUE, det ville sige vi afslutter spillet
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    public String walkfunc(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Which way do you wanna walk?");
            }
        else if (command.getSecondWord().equals("north") && Player.position[1] != 5){
            Player.moveYakse(1);
            }
        else if (command.getSecondWord().equals("east") && Player.position[0] != 5) {
            Player.moveXakse(1);
            }
        else if (command.getSecondWord().equals("south") && Player.position[1] != 0) {
            Player.moveYakse(-1);
            }
        else if (command.getSecondWord().equals("west") && Player.position[0] != 0) {
            Player.moveXakse(-1);
            }
        else {
            System.out.print("\nYou either wrote something wrong or reached the border of the 5x5 room\n");


        }
    return "unknown";

    }

    private void printHelp() 
    {
        //udskriver hvor man er og hvilke kommandoer man kan bruge
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }



    //sætter op et hashmap til at gemme i hvilke coordinater der skal være mad
    HashMap<Integer, Integer> foodCoordinatex = new HashMap<>();
    HashMap<Integer, Integer> foodCoordinatey = new HashMap<>();
    public void generateFood(){
        //opretter et spawn
        FoodSpawn<String> spawn = new FoodSpawn<String>();

        //tilføjer en entry per enum i FoodTypes sammen med deres chance
        for (FoodTypes ft : FoodTypes.values()) {
            spawn.addEntry(ft.getFoodTypes(), ft.getChance());
        }

        //opretter en boolean til at sige hvis der skal være mad i et felt
        boolean spawnfood = false;
        //for loop til y-aksen
        for (int i = 0; i < 5; i++){
            //for loop der kører gennem x-aksen
            for (int j = 0; j < 5; j++){
                //nu da vi kører gennem hver neklt felt får vi et random output hver gang og gemmer det
                //outputter "NOTHING" hvis der ikke er mad ellers outputter den navnet på det stykke mad der skal være
                String e = spawn.getRandom();
                //hvis der er mad så sæt spawnfood = true
                if (!e.equals("NOTHING")){
                    spawnfood = true;
                }
                //tjekker om spawnfood er sand
                if (spawnfood){
                    //gemmer y-koordinatet
                    foodCoordinatey.put(i,i);
                            //System.out.println(i + " " + j);
                    //gemmer x koordinatet
                    foodCoordinatex.put(i,j);
                    //sætter spawnfood til at være falsk igen til næste løb af loopet
                    spawnfood = false;
                }
            }
        }
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
            //check hvilken vej man er gået
            if (direction.equals("north") && Player.position[1] == 5){
                Player.position[1] = 0;
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
            } else if (direction.equals("south") && Player.position[1] == 0){
                Player.position[1] = 5;
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
            } else if (direction.equals("east") && Player.position[0] == 5){
                Player.position[0] = 0;
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
            } else if (direction.equals("west") && Player.position[0] == 0){
                Player.position[0] = 5;
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
            } else {
                System.out.println("You are too far from that border");
                return;
            }

        }

    }
    //return true så man kan exit
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}

