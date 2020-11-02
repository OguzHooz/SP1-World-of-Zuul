package com.mycompany.worldofzuulgui;

import java.util.Scanner;

public class Parser {

    private final CommandWords commands;
    private final Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand(String[] words) {

        // Returner commandoen
        return new Command(commands.getCommandWord(words[0]), words[1], words[2], words[3]);
    }

    // Print alle kommandoer
    public void showCommands() {
        commands.showAll();
    }
}
