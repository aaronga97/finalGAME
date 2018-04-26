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
 * @author Jose
 */
public class End extends Item{
    private int level;
    End(int x, int y, int width, int height,int level){
        super(x, y, width, height);
    }
    
    public int getLevel(){
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(getX(),getY(), getWidth(), getHeight());
    }
    
}
