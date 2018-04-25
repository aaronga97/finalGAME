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
public class Proyectile extends Item{
    
    private Game game;
    private int direction;
    private int type;
    
    public Proyectile(int x, int y, int width, int height, int direction, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void tick() {
        if(direction==1){
            setX(getX()+15);
        }
        if(direction==-1){
            setX(getX()-15);
        }
    }

    @Override
    public void render(Graphics g) {
        //        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
        g.setColor(Color.red);
        g.drawRect(getX(),getY(),getWidth(),getHeight());
       // g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
