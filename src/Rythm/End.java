package Rythm;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that represents the section where the level ends
 * Even though this class doesn't use the tick() method, it uses most of the
 * features of an Item, and that's why it inherits from it
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class End extends Item {

    private int level;
    private Animation animation;
    
    End(int x, int y, int width, int height, int level) {
        super(x, y, width, height);
        this.animation = new Animation(Assets.end,40);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
     * This method doesn't use a tick
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
        //g.setColor(Color.PINK);
        //g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
    }

}
