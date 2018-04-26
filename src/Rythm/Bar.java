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
public class Bar extends Item {

    private int counter;

    private Game game;

    public Bar(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        counter = 0;
    }

    @Override
    public void tick() {
        if ((game.getBeat() + 1) % 4 != 0) {
            height = 20;
            y = game.getHeight() - 30 - (game.getHeight() / 8) + 20;
        } else {
            height = 60;
            y = game.getHeight() - 30 - (game.getHeight() / 8);
        }
        if (!game.isJump()) {

            setX(getX() + game.getPlayer().getDistanceX() + (game.getUnit() * 2) / (int) game.getTimeBetweenBeat());
        } else {
            setX(game.getWidth() / 2 - 20 - game.getUnit() - (int) game.getCam().getX());

        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

    }

}
