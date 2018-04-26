/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

/**
 *
 * @author A4RON
 */
public class Camera {

    private float x;
    private float y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * sets <float> x </float> value
     *
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * returns <float> x </float> value
     *
     * @param x
     */
    public float getX() {
        return x;
    }

    /**
     * sets <float> y </float> value
     *
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * returns <float> y </float> value
     *
     * @param y
     */
    public float getY() {
        return y;
    }

    public void tick(Player player) {

        setX(-player.getX() + 1280 / 2); //1280/2 = gamewidth/2

    }
}
