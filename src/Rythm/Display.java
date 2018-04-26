package Rythm;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Class to create the game window and the canvas
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class Display {

    private int height;     // height of the window
    private int width;      // width of the window

    private Canvas canvas;  // to display images
    private JFrame jframe;  // this is the app class
    private String title;   // title of the window

    /**
     * initializes the values for the application game
     *
     * @param title to display the title of the window
     * @param width to set the width
     * @param height to set the height
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    /**
     * to get the canvas of the game
     *
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * to get the jframe of the game
     *
     * @return jframe
     */
    public JFrame getJframe() {
        return jframe;
    }

    /**
     * create the app and the canvas and add the canvas to the window app
     */
    public void createDisplay() {
        // create the app window
        jframe = new JFrame(title);

        // set the size of the window
        jframe.setSize(width, height);

        // setting not resizable, visible and possible to close
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        // creating the canvas to paint and setting size
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        // adding the canvas to the app window and packing to
        // get the right dimensions
        jframe.add(canvas);
        jframe.pack();
    }
}
