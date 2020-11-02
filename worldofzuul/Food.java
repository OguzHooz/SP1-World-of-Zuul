package Food;

public class Food {
    public static int score;
    //Vi starter med en score på fem, lidt sulten men kan klare sig til næste med
    private Food() {this.score = 5;}
    //Opretter en række af forskellige typer mad som kan spawnes



    public static void  addScore(int amount){
        score += amount;
    }
    public int getScore() {
        return score;
    }

    //TODO randomgeneration af hvilken FOOD

}
