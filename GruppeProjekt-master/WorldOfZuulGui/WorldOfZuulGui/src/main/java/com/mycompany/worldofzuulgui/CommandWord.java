package com.mycompany.worldofzuulgui;

public enum CommandWord {
    // Opbevarede kommandoer og deres streng representation
    GO("besøg"), QUIT("afslut"), HELP("hjælp"), UNKNOWN("?"), THROWOUT("smidud"),
    PICKUP("tag"), INVENTORY("taske"), TRASH("affald");

    private final String commandString;

    // Sæt kommandostrengen til parametren
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    // Returner den satte kommandostreng fra constructoren
    public String toString() {
        return commandString;
    }
}
