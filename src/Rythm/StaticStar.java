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
public class StaticStar extends Item{
    private Animation animation;
    
    public StaticStar(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.animation = new Animation(Assets.star,40);
    }

    @Override
    public void tick() {
        this.animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
    }
    
}
