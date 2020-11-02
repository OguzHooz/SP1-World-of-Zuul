package com.mycompany.worldofzuulgui;

public class Trash {

    // Indeholder information om skrald-objektet
    private final String name;
    private final TrashType trashType;

    // Constructor som gemmer informationen fra parametrerne
    public Trash(String name, TrashType trashType) {
        this.name = name;
        this.trashType = trashType;
    }

    // Overskriv tostring med skrald-objektets navn givet fra constructoren
    @Override
    public String toString() {
        return name;
    }

    // Returner skrald-objektets skraldetype
    public TrashType getTrashType() {
        return trashType;
    }
}
