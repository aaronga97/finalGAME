package Rythm;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class for the bar that is on the bottom part of the game's screen.
 * This bar works as a visual guide for the player to keep the rythm of the game.
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */

public class Bar extends Item{
    private Game game;      // To access information from the game
    
    /**
     * Initializes a new Bar
     * @param x x position of the bar
     * @param y y position of the bar
     * @param width width of the bar
     * @param height height of the bar
     * @param game game instance to get access information
     */
    public Bar(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    /**
     * Normal functionality of the bar during the game
     */
    @Override
    public void tick() {

        
        //Depending on the beat, the height of the bar changes
        // If the next beat is the fourth beat (which indicates the super jump)
        // the bar becomes bigger
        if((game.getBeat() + 1) % 4 != 0){
            height = 20;
            y = game.getHeight() - 30 - (game.getHeight() / 8) + 20;
        } else {
            height = 60;
            y = game.getHeight() - 30 - (game.getHeight() / 8);
        }

        
        //If the tick doesnt correspond to a "beat", the bar moves to the right
        //When the there is a beat, the bar moves all the way to 
        //the initial position on the left, marking the end and beginning of a 
        // new beat
        if(!game.isJump()) {
            
            setX(getX() + game.getPlayer().getDistanceX() + (game.getUnit()*2)/(int) game.getTimeBetweenBeat());  
        }
        else {
            setX(game.getWidth()/2 - 20 - game.getUnit() - (int) game.getCam().getX());  
        }
    }

    /**
     * This will draw the Bar on the Canvas
     * @param g to manage graphics
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

    }

}
