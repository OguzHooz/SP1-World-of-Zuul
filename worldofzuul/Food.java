package worldofzuul;

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
}

