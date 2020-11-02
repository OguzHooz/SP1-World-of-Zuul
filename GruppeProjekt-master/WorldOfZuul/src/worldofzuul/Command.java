/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 *
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
package worldofzuul;

public class Command {

    // Kommandovariable
    private final CommandWord commandWord;
    private final String secondWord;
    private final String thirdWord;
    private final String fourthWord;

    // Initializer kommandovariablene til parametrene
    public Command(CommandWord commandWord, String secondWord, String thirdWord, String fourthWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
        this.fourthWord = fourthWord;
    }

    // Returner alle commandwords i følgende funktioner:
    public CommandWord getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public String getThirdWord() {
        return thirdWord;
    }

    public String getFourthWord() {
        return fourthWord;
    }

    // Check om kommandoen er en kendt kommando
    public boolean isUnknown() {
        return commandWord == CommandWord.UNKNOWN;
    }

    // Check om kommandostrengene er sat i følgende funktioner:
    public boolean hasSecondWord() {
        return secondWord != null;
    }

    public boolean hasThirdWord() {
        return thirdWord != null;
    }

    public boolean hasFourthWord() {
        return fourthWord != null;
    }
}
