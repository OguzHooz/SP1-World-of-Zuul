package worldofzuul;

import java.util.Scanner;

public class Parser {

    private final CommandWords commands;
    private final Scanner reader;

    public Parser() {
        commands = new CommandWords();

        // Check om operativsystemet er windows
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            // Bruges til windowsbrugere for at sikre kompatibilitet med æøå
            reader = new Scanner(System.in, "ISO-8859-1");
        } else {
            reader = new Scanner(System.in);
        }
    }

    public Command getCommand() {
        // Få command input fra brugeren
        System.out.print("> ");
        String inputLine = reader.nextLine();

        // Opret skanner som bruges til at iterere over kommandoerne
        Scanner tokenizer = new Scanner(inputLine);

        // Opret array som kan indeholde kommandoerne
        String[] words = new String[4];
        
        // Få alle kommandoer fra tokenizer og gem i words arrayet
        int i = 0;
        do {
            words[i] = tokenizer.next().toLowerCase();
            i++;
        } while (tokenizer.hasNext());

        // Returner commandoen
        return new Command(commands.getCommandWord(words[0]), words[1], words[2], words[3]);
    }

    // Print alle kommandoer
    public void showCommands() {
        commands.showAll();
    }
}
