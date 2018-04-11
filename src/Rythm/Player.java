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
public class Player extends Item {

    private Game game;
    private int direction;      //direction of the player
    private int distanceX;      //distance to travel in X in one beat
    private int distanceY;      //distance to travel in Y in one beat
    private boolean init;
  
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        direction = 1;
        distanceX = 0;
        distanceY = game.getHeight() - getHeight();
        init = false;
    }

    /**
     * returns <int> direction </int> value
     *
     * @return direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * sets <int> direction </int> value
     *
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDistanceX() {
        return distanceX;
    }

    public void setDistanceX(int distanceX) {
        this.distanceX = distanceX;
    }

    public int getDistanceY() {
        return distanceY;
    }

    public void setDistanceY(int distanceY) {
        this.distanceY = distanceY;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    @Override
    public void tick() {
        //changes the players direction depending on the key pressed
        if (game.getKeyManager().isLeft()) {
            setDirection(-1);
        } else if (game.getKeyManager().isRight()) {
            setDirection(1);
        }

        //if jump is active this makes the character jump
        //if the beat is between 1-3 it performs a normal jump
        // the beat that matches with 4 is a "special jump", which is larger
        if (game.isJump()) {
            if (game.getBeat() % 4 != 0) {
                //The distance between jumps is calculated and then divided by the number of frames/ticks per jump to get the distance to move each tick
                setDistanceX(((getX() + (game.getUnit() * 2 * getDirection()))- getX())/(int)game.getTimeBetweenBeat());
                setDistanceY((getY() - (getY() - game.getUnit()))/((int)game.getTimeBetweenBeat()/2));
            }
            else {
                //The distance between jumps is calculated and then divided by the number of frames/ticks per jump to get the distance to move each tick
                setDistanceX(((getX() + (game.getUnit() * 3 * getDirection()))- getX())/(int)game.getTimeBetweenBeat());
                setDistanceY((getY() - (getY() - (game.getUnit() * 2)))/((int)game.getTimeBetweenBeat()/2));
            }
        }
        
        if(!isInit()){
            setDistanceX(((getX() + (game.getUnit() * 2 * getDirection()))- getX())/(int)game.getTimeBetweenBeat());
            setDistanceY((getY() - (getY() - game.getUnit()))/((int)game.getTimeBetweenBeat()/2));
        }
        //moves the player between beats
        //setX(getX() + getDistanceX());
        // here, half of the jump, the player goes up and the other half goes down
        if (game.getTimeCounter() < game.getTimeBetweenBeat() / 2) {
            setY(getY() - getDistanceY()); 
        } 
        else {
            setY(getY() + getDistanceY());
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
