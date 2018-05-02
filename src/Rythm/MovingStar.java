/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.Graphics;

/**
 * Class to set MovingStar animation on background
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class MovingStar extends Item{
    private int direction;      // Direction of movement
    private Animation animationRight;   //Stores animation of star moving to the right
    private Animation animationLeft;    //Stores animation of star moving to the right
    
    /**
     * Constructor. Initializes new MovingStar
     * @param x X position
     * @param y Y Position
     * @param width Width value
     * @param height Height value
     * @param direction Sets direction
     */
    public MovingStar(int x, int y, int width, int height,int direction) {
        super(x, y, width, height);
        this.direction = direction;
        this.animationRight = new Animation(Assets.movingstar,40);
        this.animationLeft = new Animation(Assets.movingstar2,40);
    }

    /**
     *  set direction
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * return direction
     * @return direction
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * Ticks animation, depending on type
     */
    @Override
    public void tick() {
        if(direction == 1){
            setX(getX()+9);
            this.animationRight.tick();
        }else{
            setX(getX()-6);
            this.animationLeft.tick();
        }
        
    }

    /**
     * Renders moving star
     * @param g Graphics instance to handle graphics
     */
    @Override
    public void render(Graphics g) {
        if(direction == 1){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
        }else{
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
        }
    }
    
}
