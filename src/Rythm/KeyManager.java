/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Usuario1
 */
public class KeyManager implements KeyListener {

    private boolean keys[];  // to store all the flags for every key
    private boolean left;    // flag to move left the player
    private boolean right;   // flag to move right the player
    private boolean reset;    // flag to save
    private boolean space;   // flag to shoot 


    /**
     * returns left
     *
     * @return left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * returns right
     *
     * @return right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * returns reset
     * 
     * @return 
     */
    public boolean isReset() {
        return reset;
    }
    
    /**
     * returns space
     *
     * @return space
     */
    public boolean isSpace() {
        return space;
    }

    /**
     * Constructor. Initializes new KeyManager
     */
    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // If space or R is pressed, ignore until release
        // Else, turn the array position to true
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_R) {
            keys[e.getKeyCode()] = false;
        } else {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        // If space or R, set to true
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_R) {
            keys[e.getKeyCode()] = true;
        } else {
            keys[e.getKeyCode()] = false;
        }
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        reset = keys[KeyEvent.VK_R];
        
        //Space and R only work for one tick, then it's false
        if (space) {
            keys[KeyEvent.VK_SPACE] = false;
        }
        if (reset) {
            keys[KeyEvent.VK_R] = false;
        }
    }
}
