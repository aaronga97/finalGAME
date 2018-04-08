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
public class Player extends Item{
    
    private Game game;
    private int direction;      //direction of the player
    private int xToGo;          //how much to move in X in one beat
    private int yToGo;          //how much to move upwards in Y in one beat
    private int xDistance;      //distance to travel in X in one beat
    private int yDistance;      //distance to travel in Y in one beat
    private int pixelsToMoveX;  //pixels to move in X in one beat
    private int pixelsToMoveY;  //pixels to move in Y in one beat
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        direction = 1;
        xToGo = 0;
        yToGo = game.getHeight() - getHeight();
    }

    /**
     * returns <int> direction </int> value
     * @return direction
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * sets <int> direction </int> value
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    /**
     * returns <int> xToGo </int> value
     * @return xToGo
     */
    public int getxToGo() {
        return xToGo;
    }

    /**
     * sets <int> xToGo </int> value
     * @param xToGo 
     */
    public void setxToGo(int xToGo) {
        this.xToGo = xToGo;
    }

    /**
     * returns <int> yToGo </int> value
     * @return yToGo
     */
    public int getyToGo() {
        return yToGo;
    }

    /**
     * sets <int> yToGo </int> value
     * @param yToGo 
     */
    public void setyToGo(int yToGo) {
        this.yToGo = yToGo;
    }

    /**
     * returns <int> xDistance </int> value
     * @return xDistance
     */
    public int getxDistance() {
        return xDistance;
    }

    /**
     * sets <int> xDistance </int> value
     * @param xDistance 
     */
    public void setxDistance(int xDistance) {
        this.xDistance = xDistance;
    }

    /**
     * returns <int> yDistance </int> value
     * @return yDistance
     */
    public int getyDistance() {
        return yDistance;
    }

    /**
     * sets <int> yDistance </int> value
     * @param yDistance 
     */
    public void setyDistance(int yDistance) {
        this.yDistance = yDistance;
    }
    
    /**
     * returns <int> pixelsToMoveX </int> value
     * @return pixelsToMoveX
     */
    public int getPixelsToMoveX() {
        return pixelsToMoveX;
    }

    /**
     * sets <int> pixelsToMoveX </int> value
     * @param pixelsToMoveX 
     */
    public void setPixelsToMoveX(int pixelsToMoveX) {
        this.pixelsToMoveX = pixelsToMoveX;
    }

    /**
     * returns <int> pixelsToMoveY </int> value
     * @return pixelsToMoveY
     */
    public int getPixelsToMoveY() {
        return pixelsToMoveY;
    }

    /**
     * sets <int> pixelsToMoveY </int> value
     * @param pixelsToMoveY 
     */
    public void setPixelsToMoveY(int pixelsToMoveY) {
        this.pixelsToMoveY = pixelsToMoveY;
    }
    
    @Override
    public void tick() {
        //changes the players direction depending on the key pressed
        if(game.getKeyManager().isLeft()){
            setDirection(-1);
        }
        else if(game.getKeyManager().isRight()){
            setDirection(1);
        }
        
        //if jump is active this makes the character jump
        //if the beat is between 1-3 it performs a normal jump

        if(game.isJump() && game.getBeat() < 4){
            //sets x and y values to valid unit value
            setX(getxToGo());
            setY(game.getHeight() - getHeight());
            //sets x and y coordanates to reach before the next beat
            //in the case of Y it just sets the max height it can reach
            setxToGo(getX() + (game.getUnit() * 2 * getDirection()));
            setyToGo(getY() - game.getUnit());
            //sets the distance to travel in x and y
            setxDistance(getxToGo() - getX());
            setyDistance(getY() - getyToGo());
            //increases the beat by 1
            game.setBeat(game.getBeat() + 1);
        }        
        //if it is in 4 it performs a super jump (after 4 it resets to 1)
        else if(game.isJump() && game.getBeat() == 4){
            //sets x and y values to valid unit value
            setX(getxToGo());
            setY(game.getHeight() - getHeight());
            //sets x and y coordanates to reach before the next beat
            //in the case of Y it just sets the max height it can reach
            setxToGo(getX() + (game.getUnit() * 3 * getDirection()));
            setyToGo(getY() - (game.getUnit() * 2));
            //sets the distance to travel in x and y
            setxDistance(getxToGo() - getX());
            setyDistance(getY() - getyToGo());
            //resets the beat back to 1
            game.setBeat(1);
        }
        game.setJump(false);
        //moves the player between beats
        setX(getX() + getPixelsToMoveX());
        if(game.getTimeCounter() < game.getTimeBetweenBeat() / 2){
            setY(getY() - getPixelsToMoveY());
        }
        else{
            setY(getY() + getPixelsToMoveY());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
