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
public class Enemy extends Item{
    
    private Game game;
    private int direction;              //direction of the enemy
    private int distanceX;              //distance to travel in X in one beat
    private int distanceY;              //distance to travel in Y in one beat
    private int distanceToFloor;        //distance from the enemy to the floor
    private int floor;                  //location of the floor
    private boolean onPlataform;        //tells if the player is touching a plataform
    private boolean init;               //tells if the player is making the first jump

    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        direction = 1;
        distanceX = 0;
        distanceY = game.getHeight() - getHeight();
        floor = y;
        init = false;
        onPlataform = false;
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
    public void setDirection(int iDirection) {
        this.direction = iDirection;
    }

     /**
     * returns <int> distanceX </int> value
     *
     * @return distanceX
     */
    public int getDistanceX() {
        return distanceX;
    }

    /**
     * sets <int> distanceX </int> value
     *
     * @param distanceX
     */
    public void setDistanceX(int distanceX) {
        this.distanceX = distanceX;
    }

    /**
     * returns <int> distanceY </int> value
     *
     * @return distanceY
     */
    public int getDistanceY() {
        return distanceY;
    }

    /**
     * sets <int> distanceY </int> value
     *
     * @param distanceY
     */
    public void setDistanceY(int distanceY) {
        this.distanceY = distanceY;
    }

        /**
     * returns <int> distanceToFloor </int> value
     *
     * @return distanceToFloor
     */
    public int getDistanceToFloor() {
        return distanceToFloor;
    }

    /**
     * sets <int> distanceToFloor </int> value
     *
     * @param distanceToFloor
     */
    public void setDistanceToFloor(int distanceToFloor) {
        this.distanceToFloor = distanceToFloor;
    }

        /**
     * returns <boolean> init </boolean> value
     *
     * @return init
     */
    public boolean isInit() {
        return init;
    }

    /**
     * sets <boolean> init </boolean> value
     *
     * @param init
     */
    public void setInit(boolean init) {
        this.init = init;
    }
    
        /**
     * returns <boolean> onPlataform </boolean> value
     *
     * @return onPlataform
     */
    public boolean isOnPlataform() {
        return onPlataform;
    }

    /**
     * sets <boolean> onPlataform </boolean> value
     *
     * @param onPlataform
     */
    public void setOnPlataform(boolean onPlataform) {
        this.onPlataform = onPlataform;
    }

    @Override
    public void tick() {
        setX(getX());

        if(game.isJump()){
            if (game.getBeat() % 4 != 0) {
                //The distance between jumps is calculated and then divided by the number of frames/ticks per jump to get the distance to move each tick
                setDistanceY((getY() - (getY() - game.getUnit()))/((int)game.getTimeBetweenBeat()/2));
            }
            else {
                //The distance between jumps is calculated and then divided by the number of frames/ticks per jump to get the distance to move each tick
                setDistanceY((getY() - (getY() - (game.getUnit() * 2)))/((int)game.getTimeBetweenBeat()/2));
            }
            //calculate the distance that is between the player and the floor
            setDistanceToFloor((floor - (getY() - (getDistanceY() * ((int)game.getTimeBetweenBeat()/2)))) / ((int)game.getTimeBetweenBeat()/2));
        }

        if(!isInit()){
            setDistanceY((getY() - (getY() - game.getUnit()))/((int)game.getTimeBetweenBeat()/2));
            setDistanceToFloor((floor - (getY() - (getDistanceY() * ((int)game.getTimeBetweenBeat()/2)))) / ((int)game.getTimeBetweenBeat()/2));
        }
        
        //here, half of the jump, the enemy goes up and the other half goes down
        if (game.getTimeCounter() < game.getTimeBetweenBeat() / 2) {
            setY(getY() - getDistanceY());
        }
        else {
            setOnPlataform(false);
            setY(getY() + getDistanceToFloor());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(getX(), getY(), 40, 40);
    }
}
