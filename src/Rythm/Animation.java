package Rythm;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed;			    // Speed of which the animation loops
    private int index;			    // Index of array of frames
    private long lastTime;		    // last registered time
    private long timer;			    // time passed
    private BufferedImage[] frames;	    // BufferedImage array of frames

    /**
     * <b>Animation</b> constructor with specified frames and speed
     *
     * @param frames is the array of sprites
     * @param speed is the speed the animation will run as
     */
    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * <code>speed</code> Setter
     *
     * @param speed to modify
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * <code>speed</code> Getter
     *
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets current frame of the animation
     *
     * @return frame in frames[index]
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    /**
     * Tick update
     */
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;	    // accumulate timer
        lastTime = System.currentTimeMillis();		    // updates lastTime
        if (timer > speed) {	    // When timer surpasses speed, go to next frame index
            timer = 0;
            index++;
            if (index >= frames.length) {    // if index goes out of bounds, start back at 0
                index = 0;
            }
        }
    }
}
