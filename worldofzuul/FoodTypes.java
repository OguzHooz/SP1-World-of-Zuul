package Food;

public enum FoodTypes {
    FUNGI("food", 1, 0.175),
    BACTERIA("food",1, 0.175),
    BARNACLES("food", 1, 0.175),
    DETRITUS("food", 1,0.175),
    WORMS("food", 2, 0.066),
    PLANKTON("food",2, 0.066),
    KRILL("food", 2, 0.066),
    TURTLE_HATCHLING("food", 2, 0.066),
    MOLLUSCS("food", 3, 0.033),
    SHRIMP("food", 4, 0.0025),
    CRAYFISH("food", 5, 0.0005);

    private final String type;
    private final int amount;
    private final double chance;

    FoodTypes(String type, int amount, double chance){
        this.type = type;
        this.amount = amount;
        if (type.equals("food")){
            Food.addScore(amount);
        }
        this.chance = chance;
    }
}
