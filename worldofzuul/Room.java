package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    //TODO Så af en eller anden grund giver den samme food til alle rooms
    private final String description;
    private final HashMap<String, Room> exits;
    final int[] xakse = {1,2,3,4,5};
    final int[] yakse = {1,2,3,4,5};
    public boolean isDiscovered = false;
    private static ArrayList<Integer> foodCoordinatex = null;
    private static ArrayList<Integer> foodCoordinatey = null;
    private static ArrayList<String> foodType = null;
    private static ArrayList<Integer> foodAmount = null;

    public static ArrayList<Integer> getFoodCoordinatex() {
        return foodCoordinatex;
    }

    public static ArrayList<Integer> getFoodCoordinatey() {
        return foodCoordinatey;
    }

    public static ArrayList<String> getFoodType() {
        return foodType;
    }

    public static ArrayList<Integer> getFoodAmount() {
        return foodAmount;
    }

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();

        //generere food og smider i forskellige lister
        foodAmount = new ArrayList<>();
        foodType = new ArrayList<>();
        foodCoordinatex = new ArrayList<>();
        foodCoordinatey = new ArrayList<>();
        //opretter et spawn
        FoodSpawn<String> spawn = new FoodSpawn<String>();

        //tilføjer en entry per enum i FoodTypes sammen med deres chance
        for (FoodTypes ft : FoodTypes.values()) {
            spawn.addEntry(ft.getFoodTypes(), ft.getChance());
        }

        //opretter en boolean til at sige hvis der skal være mad i et felt
        boolean spawnFood = false;
        boolean doLoop = true;
        //for loop til y-aksen
        outerloop:
        if (doLoop) {
            for (int i = 0; i < 6; i++) {
                //for loop der kører gennem x-aksen
                for (int j = 0; j < 6; j++) {
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
                                foodType.add("A fungi");
                                foodAmount.add(FoodTypes.FUNGI.getAmount());
                            }
                            case "BACTERIA" -> {
                                foodType.add("Some bacteria");
                                foodAmount.add(FoodTypes.BACTERIA.getAmount());
                            }
                            case "BARNACLES" -> {
                                foodType.add("A barnacle");
                                foodAmount.add(FoodTypes.BARNACLES.getAmount());
                            }
                            case "DETRITUS" -> {
                                foodType.add("Some detritus");
                                foodAmount.add(FoodTypes.DETRITUS.getAmount());
                            }
                            case "WORMS" -> {
                                foodType.add("Some worms");
                                foodAmount.add(FoodTypes.WORMS.getAmount());
                            }
                            case "PLANKTON" -> {
                                foodType.add("Some plankton");
                                foodAmount.add(FoodTypes.PLANKTON.getAmount());
                            }
                            case "KRILL" -> {
                                foodType.add("A few krill");
                                foodAmount.add(FoodTypes.KRILL.getAmount());
                            }
                            case "TURTLE_HATCHLING" -> {
                                foodType.add("Some turtle hatchlings");
                                foodAmount.add(FoodTypes.TURTLE_HATCHLING.getAmount());
                            }
                            case "MOLLUSCS" -> {
                                foodType.add("A mollusc");
                                foodAmount.add(FoodTypes.MOLLUSCS.getAmount());
                            }
                            case "SHRIMP" -> {
                                foodType.add("A shrimp");
                                foodAmount.add(FoodTypes.SHRIMP.getAmount());
                            }
                            case "CRAYFISH" -> {
                                foodType.add("A crayfish");
                                foodAmount.add(FoodTypes.CRAYFISH.getAmount());
                            }
                        }
                        //sætter spawnfood til at være falsk igen til næste løb af loopet
                        spawnFood = false;
                    }//end if spawnfood true
                    //break label if more 4 food spawns
                    if (foodCoordinatex.size() == 4) {
                        doLoop = false;
                        break outerloop;
                    }

                }//end x-axis
                //if there is no food restart loop
                if (foodCoordinatey.isEmpty()) {
                    i = 0;
                }
            }//end y-axis
        }//doLoop if loop
    }//Room constructor

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
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
}

