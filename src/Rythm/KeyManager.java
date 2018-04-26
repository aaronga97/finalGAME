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
    private boolean load;    // flag to load game
    private boolean left;    // flag to move left the player
    private boolean pause;   // flag to pause
    private boolean right;   // flag to move right the player
    private boolean save;    // flag to save
    private boolean space;   // flag to shoot 

    public boolean isLoad() {
        return load;
    }

    /**
     * returns left
     *
     * @return left
     */
    public boolean isLeft() {
        return left;
    }

    public boolean isPause() {
        return pause;
    }

    /**
     * returns right
     *
     * @return right
     */
    public boolean isRight() {
        return right;
    }

    public boolean isSave() {
        return save;
    }

    /**
     * returns space
     *
     * @return space
     */
    public boolean isSpace() {
        return space;
    }

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_L) {
            keys[e.getKeyCode()] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {

        } else {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_L) {
            keys[e.getKeyCode()] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
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
        pause = keys[KeyEvent.VK_P];
        save = keys[KeyEvent.VK_S];
        load = keys[KeyEvent.VK_L];
        if (space) {
            keys[KeyEvent.VK_SPACE] = false;
        }
        if (save) {
            keys[KeyEvent.VK_S] = false;
        }
        if (load) {
            keys[KeyEvent.VK_L] = false;
        }

    }
}
