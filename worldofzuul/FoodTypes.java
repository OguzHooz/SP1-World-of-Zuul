package worldofzuul;

public enum FoodTypes {
    //bruger denne til at rette chancen for de andre
    NOTHING("NOTHING", 0, 30.000),
    //typer af mad, der hver indeholder en chance for at fremkomme og en amount til score
    FUNGI("FUNGI", 1, 0.175),
    BACTERIA("BACTERIA",1, 0.175),
    BARNACLES("BARNACLES", 1, 0.175),
    DETRITUS("DETRITUS", 1,0.175),
    WORMS("WORMS", 2, 0.066),
    PLANKTON("PLANKTON",2, 0.066),
    KRILL("KRILL", 2, 0.066),
    TURTLE_HATCHLING("TURTLE_HATCHLING", 2, 0.066),
    MOLLUSCS("MOLLUSCS", 3, 0.033),
    SHRIMP("SHRIMP", 4, 0.0025),
    CRAYFISH("CRAYFISH", 5, 0.0005);
    private final String foodTypes;
    private final int amount;
    private final double chance;

    FoodTypes(String foodTypes, int amount, double chance){
        this.foodTypes = foodTypes;
        this.amount = amount;
        this.chance = chance;
    }

    private String foodTypes(){
        return foodTypes;
    }
    private int amount(){
        return amount;
    }
    private double chance(){
        return chance;
    }

    public int getAmount() {
        return amount;
    }

    public double getChance() {
        return chance;
    }

    public String getFoodTypes() {
        return foodTypes;
    }
}
