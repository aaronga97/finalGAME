package Rythm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class to save
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class Files {


    /**
     * Load highscore
     * @param game Game instance to assign score
     */
    public static void loadFile(Game game) {
        BufferedReader br = null;
        FileReader fr = null;
        String line;

        // read highscore from file
        try {
            fr = new FileReader("Highscores.txt");
            br = new BufferedReader(fr);
            line = br.readLine();
            game.setHighscore(line); //Assign highscore in current game
            fr.close();

        } catch (IOException ioe) {
            System.out.println("no hay nada grabado " + ioe.toString());
        }
    }

    
    /**
     * Save highscore on file
     * @param game Game instance to get score from current game
     */
    public static void saveFile(Game game) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("Highscores.txt"));
            printWriter.print(game.getScore()); // Get the current highscore from the game
            printWriter.close();

        } catch (IOException ex) {
            System.out.println("Se lleno el disco duro" + ex.toString());
            ex.printStackTrace();
        }

    }

}
