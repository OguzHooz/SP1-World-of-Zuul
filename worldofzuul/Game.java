package worldofzuul;

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
    }


    private void createRooms()
    {
        //Opretter en del forskellige rooms med hver deres navn
        Room outside, theatre, pub, lab, office;

        //Tilføjer descriptions til rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        //tilføjer exits til diverse rooms
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        //sætter startstedet af spillet
        currentRoom = outside;
    }

    public void play() 
    {
        //Printer velkomst nedenfor
        printWelcome();

        //Tjekker alle commandoer på nedenstående class, hvis der skrevet quit spring over
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        //hvis spil afsluttet udskriv denne linje
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        //velkomsttekst, udskriver hjælpe kommando, lang description for starterrum
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
        }
        //sætter wantToQuit til at TRUE, det ville sige vi afslutter spillet
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
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

    private void goRoom(Command command) 
    {
        //Hvis du bare skriver "go" udskrives dette
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        //direction er det om man har brugt south,east,west,north når man opretter rummet
        String direction = command.getSecondWord();
        //gemmer det næste rum som det nuværende i forhold hvilket direction man har gået til
        Room nextRoom = currentRoom.getExit(direction);
        //hvis der ikke er en exit den vej man har skrevet
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else { //hvis der en exit gem som nyt rum udskriv lang description
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
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
