package worldofzuul;

import javax.swing.*;

public class Game
{
    private Parser parser;
    private Room currentRoom;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        Room origo, theatre, pub, lab, office;
      
        origo = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        origo.setExit("east", theatre);
        origo.setExit("south", lab);
        origo.setExit("west", pub);

        theatre.setExit("west", origo);

        pub.setExit("east", origo);

        lab.setExit("north", origo);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = origo;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.WALK) {
            walkfunc(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    public static String walkfunc(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Which way do you wanna walk?");
            System.out.println(command.getSecondWord());
            System.out.println(command.getCommandWord());
            }
        else if (command.getSecondWord().equals("north")){
            System.out.print("walking north");
            return "north";
            }
        else if (command.getSecondWord().equals("east")) {
            System.out.print("walking east");
            return "east";
            }
        else if (command.getSecondWord().equals("south")) {
            System.out.print("walking south");
            return "south";
            }
        else if (command.getSecondWord().equals("west")) {
            System.out.print("walking west");
            return "west";
            }
        else {
            System.out.print("not sure what you meant...");


        }
    return "unknown";

    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

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
