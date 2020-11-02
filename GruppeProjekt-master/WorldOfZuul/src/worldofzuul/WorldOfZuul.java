package worldofzuul;

public class WorldOfZuul {

    // Her starter vores spil i main metoden.
    // Det er det første, som bliver kørt i programmet
    public static void main(String[] args) {
        
        // vi opretter en ny instans af Game, så vi kan starte spillet
        Game game = new Game();
        
        // derved, kan vi starte spillet ved at kalde play metoden
        game.play();
    }
    
}
