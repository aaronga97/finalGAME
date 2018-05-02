/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.Graphics;

/**
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class StaticStar extends Item{
    private Animation animation;    // Animation instance to manage star animation
    
    /**
     * Constructor. Initializes new star
     * @param x X position
     * @param y Y position
     * @param width Width of star
     * @param height Height of star
     */
    public StaticStar(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.animation = new Animation(Assets.star,40);
    }

    /**
     * Ticks the animation
     */
    @Override
    public void tick() {
        this.animation.tick();
    }

    /**
     * Renders the star on the game
     * @param g Graphics instance to handle graphics
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
    }
    
}
