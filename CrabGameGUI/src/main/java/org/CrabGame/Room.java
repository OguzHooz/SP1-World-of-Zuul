package org.CrabGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room {

    private String description;

    private HashMap<String, Room> exits;
    final int[] xakse = {1,2,3,4,5};
    final int[] yakse = {1,2,3,4,5};
    public boolean isDiscovered = false;

    //arraylister til mad
    ArrayList<Integer> foodCoordinatex;
    ArrayList<Integer> foodCoordinatey;
    ArrayList<String> foodType;
    ArrayList<Integer> foodAmount;

    //Constructor
    public Room(String description){
        this.description = description;
        exits = new HashMap<String, Room>();

        foodAmount = new ArrayList<>();
        foodType = new ArrayList<>();
        foodCoordinatex = new ArrayList<>();
        foodCoordinatey = new ArrayList<>();

        //Vi generere mad som en del af vores constructor, så hver gang
        //der bliver oprettet et rum, bliver der altid genereret mad
        FoodSpawn<String> spawn = new FoodSpawn<String>();

        //tilføjer en entry per enum i FoodTypes sammen med deres chance
        for (FoodTypes ft : FoodTypes.values()){
            spawn.addEntry(ft.getFoodTypes(), ft.getChance());
        }
        boolean spawnFood = false;
        boolean doLoop = true;
        outerLoop:
        if (doLoop){
            for (int i = 0; i<6; i++){
                //for loop der kører gennem x-aksen
                for (int j = 0; j<6; j++){
                    //nu da vi kører gennem hver enkelt felt får vi et random output hver gang og gemmer det
                    //outputter "NOTHING" hvis der ikke er mad ellers outputter den navnet på det stykke mad der skal være
                    String e = spawn.getRandom();
                    //hvis der er mad så sæt spawnfood = true
                    if (!e.equals("NOTHING")) {
                        spawnFood = true;
                    }
                    //tjekker om spawnfood er sand
                    if (spawnFood) {
                        //hvis der ikke allerede er dette
                        foodCoordinatex.add(j);
                        foodCoordinatey.add(i);
                        switch (e) {
                            case "FUNGI" -> {
                                foodType.add("FUNGI");
                                foodAmount.add(FoodTypes.FUNGI.getAmount());
                            }
                            case "BACTERIA" -> {
                                foodType.add("BACTERIA");
                                foodAmount.add(FoodTypes.BACTERIA.getAmount());
                            }
                            case "BARNACLES" -> {
                                foodType.add("BARNACLES");
                                foodAmount.add(FoodTypes.BARNACLES.getAmount());
                            }
                            case "DETRITUS" -> {
                                foodType.add("DETRITUS");
                                foodAmount.add(FoodTypes.DETRITUS.getAmount());
                            }
                            case "WORMS" -> {
                                foodType.add("WORMS");
                                foodAmount.add(FoodTypes.WORMS.getAmount());
                            }
                            case "PLANKTON" -> {
                                foodType.add("PLANKTON");
                                foodAmount.add(FoodTypes.PLANKTON.getAmount());
                            }
                            case "KRILL" -> {
                                foodType.add("KRILL");
                                foodAmount.add(FoodTypes.KRILL.getAmount());
                            }
                            case "TURTLE_HATCHLING" -> {
                                foodType.add("TURTLE_HATCHLING");
                                foodAmount.add(FoodTypes.TURTLE_HATCHLING.getAmount());
                            }
                            case "MOLLUSCS" -> {
                                foodType.add("MOLLUSCS");
                                foodAmount.add(FoodTypes.MOLLUSCS.getAmount());
                            }
                            case "SHRIMP" -> {
                                foodType.add("SHRIMP");
                                foodAmount.add(FoodTypes.SHRIMP.getAmount());
                            }
                            case "CRAYFISH" -> {
                                foodType.add("CRAYFISH");
                                foodAmount.add(FoodTypes.CRAYFISH.getAmount());
                            }
                        }
                        //sætter spawnfood til at være falsk igen til næste løb af loopet
                        spawnFood = false;
                    }//end if spawnfood true
                }//end x axis
                //if there is no food restart loop
                if (foodCoordinatex.isEmpty()){
                    i = 0;
                }
            }//end y axis
        }//end if doloop
    }//room constructor

    public void setExit(String direction, Room neighbor){
        exits.put(direction,neighbor);
    }
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString.append(" ").append(exit);
        }
        return returnString.toString();
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    //en del gets til mad
    public ArrayList<String> getFoodType() {
        return foodType;
    }

    public ArrayList<Integer> getFoodAmount() {
        return foodAmount;
    }

    public ArrayList<Integer> getFoodCoordinatey() {
        return foodCoordinatey;
    }

    public ArrayList<Integer> getFoodCoordinatex() {
        return foodCoordinatex;
    }

}