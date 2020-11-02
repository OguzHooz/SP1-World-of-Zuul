package worldofzuul;

public class ScoreCounter {

    private int score;

    public ScoreCounter() {
        this.score = 20;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public void decreaseScore(int amount) {
        score -= amount;

    }

    public void printScore() {
        System.out.println("Score: " + score);
    }
    
    public int getScore() {
        return score;
    }

}
