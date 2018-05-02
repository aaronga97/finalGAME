/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.Graphics;

/**
 *
 * @author Jose
 */
public class MovingStar extends Item{
    private int direction;
    private Animation animationRight;
    private Animation animationLeft;
    public MovingStar(int x, int y, int width, int height,int direction) {
        super(x, y, width, height);
        this.direction = direction;
        this.animationRight = new Animation(Assets.movingstar,40);
        this.animationLeft = new Animation(Assets.movingstar2,40);
    }

    @Override
    public void tick() {
        if(direction == 1){
            setX(getX()+10);
            this.animationRight.tick();
        }else{
            setX(getX()-10);
            this.animationLeft.tick();
        }
        
    }

    @Override
    public void render(Graphics g) {
        if(direction == 1){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
        }else{
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
        }
    }
    
}
