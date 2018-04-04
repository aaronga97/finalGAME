/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ricky
 */
public class Files {    
    public static void loadFile(Game game) {
        BufferedReader br = null;
        FileReader fr = null;
        String line;        
        
        try {
            fr = new FileReader("save.txt");
            br = new BufferedReader(fr);
            
            fr.close();

        } catch (IOException ioe) {
            System.out.println("no hay nada grabado " + ioe.toString());
        }
    }
    
    public static void saveFile(Game game) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("save.txt"));
            
            printWriter.close();

        } catch (IOException ex) {
            System.out.println("Se lleno el disco duro" + ex.toString() );
            ex.printStackTrace();
        }
        
    }
    
}
