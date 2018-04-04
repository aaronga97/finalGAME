/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.Graphics;

/**
 *
 * @author Usuario1
 */
public class Enemy extends Item{
    
    private Game game;
    int iDirection;
    int type;

    public Enemy(int x, int y, int width, int height, int type, Game game) {
        super(x, y, width, height);
        this.game = game;
        iDirection = -1;
        this.type = type;
    }

    public int getiDirection() {
        return iDirection;
    }

    public int getType() {
        return type;
    }
    
    public void setiDirection(int iDirection) {
        this.iDirection = iDirection;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
