/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Usuario1
 */
public class Proyectile extends Item {

    private int direction;
    private int type;
    private Game game;
    private Animation animation;

    public Proyectile(int x, int y, int width, int height, int direction, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.direction = direction;
        this.animation = new Animation(Assets.lazer,100);
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void tick() {
        if (direction == 1) {
            setX(getX() + 15);
        }
        if (direction == -1) {
            setX(getX() - 15);
        }
        this.animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(),getHeight(),null);
    }
}
