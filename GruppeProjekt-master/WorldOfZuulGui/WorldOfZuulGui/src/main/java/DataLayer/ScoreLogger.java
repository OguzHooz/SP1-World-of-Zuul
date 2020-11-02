package DataLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ScoreLogger implements IScoreLogger {

    // læs fra load() metoden færst helt nederst i bunden og læs opad
    
    public void saveScore(int moves) {
        List<String> data = new ArrayList<>();

        data.add("The Recycle Adventurer");
        data.add("-------------------------------");

        Date date = new Date();
        data.add(date.toString());

        data.add("Besøg: " + moves);

        data.add("-------------------------------");

        data.add("");

        data.addAll(load());

        save(data);
    }

    // metoden her følger egentlig det samme som load()
    // start fra load() metoden og bevæg dig opad
    // men den er blevet lavet lidt om for kun at læse de forskellige scores som spilleren har gemt
    // og retunere den laveste værdi ud eftersom 
    public int loadHighestScore() {
        ArrayList<Integer> scores = new ArrayList<>();
        
        File dir = new File(System.getProperty("user.dir") + "\\savegame.txt");

        try {
            Scanner in = new Scanner(new FileReader(dir));

            // vi iterere gennem hver linje i dokumentet
            while (in.hasNextLine()) {
                // vi gemmer linjen i en temp variable
                String line = in.nextLine();

                // vi tjekker overhovedet om linjen har besøg 
                if (line.contains("Besøg")) {
                    // hvis det har laver vi en ny scanner for linjen
                    // det er egentlig ligesom tokenizer for kommandoerne
                    Scanner movesInt = new Scanner(line);
                    
                    // vi skal væk fra "besøg:"
                    movesInt.next();
                    
                    // vi tilføjer det der er efter "besøg:" ind i arrayet
                    scores.add(movesInt.nextInt());
                }
            }
        } catch (FileNotFoundException ex) {
        }

        // vi tjekker om vi overhovedet har nogle scores
        if (scores.isEmpty()) {
            // hvis ikke, bare retunere 0
            return 0;
        }

        // vi sortere alle de highscores i ascending order
        // på den måde kan vi bare tage det første element ud i arrayet
        // og derved har vi fundet vores laveste skridt high score
        Collections.sort(scores);
        
        // retunere den
        return scores.get(0);
    }

    @Override
    public void save(List<String> data) {
        // læs hvad den her linje gør i load() metoden nedenunder
        File dir = new File(System.getProperty("user.dir") + "/savegame.txt");
        
        // debug
        System.out.println("Dir: " + dir.getPath());

        // igen bruges der en exception handler så programmet ikke lukker hvis filen ikke bliver fundet
        try {
            // der bruges PrintWriter til at skrive til filen
            PrintWriter writer = new PrintWriter(dir);

            // skriv til konsollen og skriv til filen er næsten det samme
            // for hver linje der er i arrayet, skriv det til filen
            for (String d : data) {
                writer.println(d);
            }

            // man behøver egentlig ikke at skrive .flush()
            // det er dog en god ting at gøre
            // https://stackoverflow.com/questions/2340106/what-is-the-purpose-of-flush-in-java-streams
            writer.flush();
            
            // vi skal huske at lukke vores stream
            // i kan læse hvorfor på Java docs men det kommer nok senere hen i dybere detalje hvorfor
            writer.close();
        } catch (FileNotFoundException ex) {
        }
    }

    @Override
    public List<String> load() {
        // vi starter først med at lave en ny array som skal senere benyttes til at retunere
        // det som vi har indlæst fra filen
        List<String> result = new ArrayList<>();

        // vi bruger File klassen til at læse filen
        // der benyttes System.getProperty("user.dir") for at få den nuværende sti fra vores applikation
        // det gør vi så vi ikke behøver at hardcode en sti
        File dir = new File(System.getProperty("user.dir") + "/savegame.txt");

        // vi laver en exception handler her så programmet ikke lukker ned hvis filen ikke eksistere
        try {
            // vi bruger både FileReader klassen og Scanner klassen til at læse indholdet af filen 
            Scanner in = new Scanner(new FileReader(dir));

            // vi læser hver enkel linje for sig selv
            while (in.hasNextLine()) {
                // for hver linje, tilføj det til vores array som vi lavede tidligere
                result.add(in.nextLine());
            }
        } catch (FileNotFoundException ex) {
        }

        // retunere det der blev tilføjet til arrayet
        // hvis der ikke er noget i arrayet
        // så sker der ikke det store
        return result;
    }
}
