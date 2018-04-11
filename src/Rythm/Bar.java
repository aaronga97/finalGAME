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
 * @author ricky
 */
public class Bar extends Item{
    private Game game;
    
    public Bar(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    
    @Override
    public void tick() {
        if(!game.isJump()) {
            setX(getX() + 2 + (game.getUnit()*2)/(int) game.getTimeBetweenBeat());  //+ (game.getUnit()*2)/(int) game.getTimeBetweenBeat()
            //int tempX = getX() - (game.getWidth()/2 - 20 - game.getUnit() - (int) game.getCam().getX());
            //setX(1);
        }
        else {
            setX(game.getWidth()/2 - 20 - game.getUnit() - (int) game.getCam().getX());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
   
}
