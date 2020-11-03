package Food;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

public class Food {
    public static int score;
    //Vi starter med en score på fem, lidt sulten men kan klare sig til næste med
    private Food() {this.score = 5;}
    //opretter diverse gets og adds
    public static void  addScore(int amount){
        score += amount;
    }
    public int getScore() {
        return score;
    }
    //TODO lav en funktion der siger man har taget et stykke mad


//TODO få det til at virke

/*    public static void main(String[] args) {
        //opretter en spawn
        FoodSpawn<String> spawn = new FoodSpawn<String>();

        //tilføjer en entry per enum i FoodTypes sammen med deres chance
        for (FoodTypes ft : FoodTypes.values()) {
            spawn.addEntry(ft.getFoodTypes(), ft.getChance());
        }

        //bruger xakse og yakse til at gå gennem hver enkelte felt
        final int[] xakse = {1,2,3,4,5};
        final int[] yakse = {1,2,3,4,5};


        //sætter op et hashmap til at gemme i hvilke coordinater der skal være mad
        HashMap<Integer, Integer> foodCoordinatex = new HashMap<>();
        HashMap<Integer, Integer> foodCoordinatey = new HashMap<>();

        boolean spawnfood = false;
        for (int i = 0; i < yakse.length; i++){
            String e = spawn.getRandom();
            if (e != "NOTHING"){
                spawnfood = true;
            }
            if (spawnfood){
                foodCoordinatey.put(i,i);
            }
            for (int j = 0; j < xakse.length; j++){
                if (spawnfood){
                    foodCoordinatex.put(i,j);
                } else {
                    System.out.println("NOTHING");
                }
            }
        }
    }*/
}

