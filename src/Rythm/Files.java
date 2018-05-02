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
     * Load game
     * @param game Game instance
     */
    public static void loadFile(Game game) {
        BufferedReader br = null;
        FileReader fr = null;
        String line;

        try {
            fr = new FileReader("Highscores.txt");
            br = new BufferedReader(fr);
            line = br.readLine();
            game.setHighscore(line);
            fr.close();

        } catch (IOException ioe) {
            System.out.println("no hay nada grabado " + ioe.toString());
        }
    }

    
    /**
     * Save game
     * @param game Game instance
     */
    public static void saveFile(Game game) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("Highscores.txt"));
            printWriter.print(game.getScore());
            printWriter.close();

        } catch (IOException ex) {
            System.out.println("Se lleno el disco duro" + ex.toString());
            ex.printStackTrace();
        }

    }

}
