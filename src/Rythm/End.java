package Rythm;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that represents the section where the level ends
 * Even though this class doesn't use the tick() method, it uses most of the
 * features of an Item, and that's why it inherits from it
 * 
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class End extends Item {

    private Animation animation;    // animation manager for the end goal
    
    /**
     * Constructor. Creates new End
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    End(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.animation = new Animation(Assets.end,40);
    }
    
    /**
     * Copy constructor for End
     * 
     * @param e 
     */
    End(End e) {
        super(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        this.animation = new Animation(Assets.end,40);
        this.animation = new Animation(Assets.end,40);
    }
    
    /**
     * This only ticks the animation
     */
    @Override
    public void tick() {
        this.animation.tick();
    } 
    
    /**
     * To draw the end zone
     * @param g To handle graphics
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
    }

}
