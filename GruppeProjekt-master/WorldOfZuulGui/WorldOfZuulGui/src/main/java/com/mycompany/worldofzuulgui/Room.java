package com.mycompany.worldofzuulgui;

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Room {

    // Integer der tæller hvor meget skrald der er spawnet i et rum. 
    static public int spawnCounter;

    // Navnet på rummet som String
    private final String description;
    // Hashmap der beskriver hvilke exits et rum har, altså hvilken vej man kan gå.
    private final HashMap<String, Room> exits;
    // Hashmap der indeholder hvilket skrald der ligger i rummet.
    public HashMap<String, Trash> trash;
    // Hashmap der indeholder hvilke typer skrald der kan spawne i rummet.
    private final HashMap<TrashType, ArrayList<TrashType>> trashSpawn;

    // Room constructor, der tager et String som argument for at angive descriptionen. 
    // Opretter 3 HashMaps.
    public Room(String description) {
        // String argumentet bliver til rummet description. 
        this.description = description;
        // Opretter et HashMap til exits
        exits = new HashMap<>();
        // Opretter et HashMap til trash
        trash = new HashMap<>();
        // Opretter et HashMap til trashSpawn
        trashSpawn = new HashMap<>();
    }

    // Metode der tager en String og et Room som argument.
    // Sætter exit til et bestemt rum og ligger det i exits-HashMappet
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    // Metode der returnerer en kort description.
    public String getShortDescription() {
        return description;
    }

    // Metode der returnerer en lang description.
    public String getLongDescription() {
        // Returnerer description som en String + description + en String med linjeskift (\n) + metoden getExitString
        return "Du befinder dig nu " + description;
    }

    //Metoden udskriver en String med noget tekst og alle exits delt op med "|"
    private String getExitString() {
        String returnString = "Du kan tage til: | ";
        // Returnerer en Set-collection af Strings og henter alle keys fra exits
        Set<String> keys = exits.keySet();
        //Tilføjer " | " mellem hver exit
        // Kører set-collectionen keys igennem med et forEach-loop og extender returnStringen med " | " mellem hver exit 
        for (String exit : exits.keySet()) {
            if (!returnString.contains("|")) {
                returnString += " " + exit + " | ";
                continue;
            }

            returnString += exit + " | ";
        }

        // Returnerer returnString
        return returnString;
    }

    // Getter til en exit. 
    public Room getExit(String direction) {
        // Henter en bestemt exit ud fra keyen "description"
        return exits.get(direction);
    }

    // Printer tekst med affald på jorden
    public void printTrash() {
        // Tekst før listen af skrald
        System.out.println("----- Affald på jorden -----");
        // For-Each loop der går alle trash-values
        for (Trash trashPrint : trash.values()) {
            // Kører alle Trash-item 
            System.out.println(trashPrint.toString());
        }
        // Tekst efter listen af skrald
        System.out.println("----------------------------");
    }

    // Metode der tager et Trash objekt som argument. 
    // Tilføjer trash-objektet trash-HashMappet
    public void addTrash(Trash trash) {
        //Sætter med trash-name'et og selve objektet.
        this.trash.put(trash.toString(), trash);
    }

    //Metode der tager et TrashType objekt og et ArrayList et TrashTypes.
    public void addTrashType(TrashType trashType, ArrayList<TrashType> trashTypes) {
        this.trashSpawn.put(trashType, trashTypes);
    }

    // Metode der spawnerTrash i et rum
    public void spawnTrash(Inventory inventory) {

        // Opretter en et Random objekt (bruges længere nede i )
        Random random = new Random();

        // Opretter en int, der angiver hvor meget skrald vi maks skal spawne
        int maxTrash = 3;

        // Bestemmer antal af skrald der kan spawne i hvert rum. spawner random int mellem 0-3
        int randomInt = (int) (Math.floor(Math.random() * Math.floor(maxTrash)));
        // Ligger 1 til, for at det bliver 1-4, så der uanset hvad spawner noget skrald.
        randomInt++;

        // For each loop, der kører alle keys i trashSpawn igennem, spawner et tilfældigt Trash fra rummets trashTypes
        // Der spawnes mellem 1-4 stykker skrald tilfældigt.  
        for (TrashType spawn : trashSpawn.keySet()) {
            // Hvis mængden af trash er større end 3, springes den trashSpawn over. 
            // Der kan altså maks spawnes 4 stykker skrald pr. rum. 
            if (trash.size() > maxTrash - 1) {
                continue;
            }

            // Hvis spawnCounter og randomNumber. Den køres altså indtil vi har spawned den mængde vi angav tidligere.
            if (spawnCounter % randomInt == 0) {
                // Opretter et index med en random integer fra 0 til x (antallet af trashTyper der kan spawnes)
                int index = random.nextInt(trashSpawn.get(spawn).size());
                // Opretter et nyt array med vores de trashTyper der er tilladt i rummet.
                ArrayList<TrashType> currentArray = trashSpawn.get(spawn);
                // Tilføjer det tilfældige trash til vores trash-Hashmap på rummet. 
                Trash randomTrash = new Trash(currentArray.get(index).toString(), spawn);

                // forhindre at samme item spawner 
                boolean shouldSkip = false;
                for (String invTrash : inventory.trash.keySet()) {
                    Trash currentTrash = inventory.trash.get(invTrash);

                    if (currentTrash.toString().equals(randomTrash.toString())) {
                        shouldSkip = true;
                    }
                }

                if (shouldSkip) {
                    continue;
                }

                trash.put(randomTrash.toString(), randomTrash);
            }
            //Fortæller der er spawnet et item. 
            spawnCounter++;
        }
    }
}
