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
public class Platform extends Item{
    
    Platform(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(Color.blue);
        g.drawRect(getX(),getY(),getWidth(),getHeight());

        //g.fillRect(getX(),getY(), getWidth(), getHeight());
    }
    
}
