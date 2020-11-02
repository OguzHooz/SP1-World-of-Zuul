package worldofzuul;

import java.util.HashMap;

public class CommandWords {
    private final HashMap<String, CommandWord> validCommands;
    // Creates a HashMap that takes a String and CommandWord as index and value

    public CommandWords() {
        validCommands = new HashMap<>(); // Creates a new HashMap
        for (CommandWord command : CommandWord.values()) { // Runs through all commandWords
            if (command != CommandWord.UNKNOWN) { // Checks to make sure command is different from CommandWord
                validCommands.put(command.toString(), command); // Puts our commandWord into our HashMap
            }
        }
    }

    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord); // Assigns our commandWord to a variable "command"
        if (command != null) { // Checks if our variable "command" is empty
            return command;
        } else {
            return CommandWord.UNKNOWN; // Returns String "?"
        }
    }

    public boolean isCommand(String aString) { // Used to check if a command exists.
        return validCommands.containsKey(aString); // Takes aString as parameter and returns True if it is a valid command
    }

    public void showAll() { // Prints all our validCommands
        System.out.println("besøg <sted>");
        System.out.println("afslut");
        System.out.println("hjælp");
        System.out.println("smidud <skraldespand> <affald>");
        System.out.println("tag <affald>");
        System.out.println("taske\n");
    }
}
