/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ricardo Lozano y Napoleon Lazo
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    private String title;           // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private int running;            // to set the game
    private Player player;          // to use a player    
    private int unit;               // the game's metric units
    private double bpm;             // the beats per minute
    private int timeCounter;        // keeps track of the seconds
    private int beat;               // keeps track of the current beat (1-4)
    private double timeBetweenBeat; // keeps how many seconds are between beats
    private boolean jump;           // checks if there is a change in beat
    private Bar bar;                // the beat bar that will help the user keep rythm visually
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Enemy> enemies; // to store enemies
    private ArrayList<Proyectile> proyectiles;
    private boolean canShoot;
    private int shootCounter;

    private SoundClip movNar;
    Camera cam;                     //camera that follows player

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        
        //1)Carga los clips de sonido
        //2)Activa la repetición del clip
        //3)Reproduce el clip
        //Checking script
        movNar = new SoundClip("/Audio/movNaranja.wav");
        movNar.setLooping(true);
        movNar.play();
        
        keyManager = new KeyManager();
        running = -1;
        canShoot = true;
        shootCounter = 0;
        unit = 64;
        bpm = 120;
        beat = 1;
    }
//test 
    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns <player> player </player> object
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * returns <int> unit </int> value
     *
     * @return unit
     */
    public int getUnit() {
        return unit;
    }

    /**
     * returns <boolean> jump </boolean> value
     *
     * @return jump
     */
    public boolean isJump() {
        return jump;
    }

    /**
     * sets <boolean> jump </boolean> value
     *
     * @param jump
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    /**
     * returns <double> bpm </double> value
     *
     * @return bpm
     */
    public double getBpm() {
        return bpm;
    }

    /**
     * returns <int> timeCounter </int> value
     *
     * @return timeCounter
     */
    public int getTimeCounter() {
        return timeCounter;
    }

    /**
     * sets <int> timeCounter </int> value
     *
     * @param timeCounter
     */
    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    /**
     * return <double> timeBetweenBeat </double> value
     *
     * @return timeBetweenBeat
     */
    public double getTimeBetweenBeat() {
        return timeBetweenBeat;
    }

    /**
     * sets <double> timeBetweenBeat </double> value
     *
     * @param timeBetweenBeat
     */
    public void setTimeBetweenBeat(double timeBetweenBeat) {
        this.timeBetweenBeat = timeBetweenBeat;
    }

    public int getBeat() {
        return beat;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }

    public Camera getCam() {
        return cam;
    }
    
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Proyectile> getProyectiles() {
        return proyectiles;
    }

   

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();

        //Initialize new camera in the corner.
        cam = new Camera(0, 0);
        bar = new Bar(getWidth()/2 - 20 - getUnit(), getHeight() - getHeight()/8, 20, 60, this);
        //Assets.backgroundMusic.play();

        player = new Player(getWidth() - getWidth(), getHeight() - getHeight()/4 - 20, 64, 64, this);

        bar = new Bar(getWidth()/2 - 20 - getUnit(), getHeight() - 30 - (getHeight()/8), 20, 60, this);
        
        enemies = new ArrayList<Enemy>();
        proyectiles = new ArrayList<Proyectile>();

        display.getJframe().addKeyListener(keyManager);

    }

    @Override
    public void run() {
        init();
        // frames per second
        double fps = 60;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();

        // number of jumps per second
        double jumpsPerSec = getBpm() / 60;
        // determines how many frames/ticks pass after each beat
        setTimeBetweenBeat(fps / jumpsPerSec);
        
        while (running == 0) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game 
            if (delta >= 1) {

                tick();
                render();
                delta--;
            }
        }
        render();
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {
        keyManager.tick();
        player.tick();
        cam.tick(player);
        bar.tick();

        // if jump was set to true on the previous tick, make it false
        if(isJump()) {
            setJump(false);
        }
        
        // a counter for ticks
        setTimeCounter(getTimeCounter() + 1);
        // when the counter gets to the number of ticks that should pass each beat
        // start a new "jumping sequence"
        // add 1 to the beat to identify between normal and special jump
        // restart the counter
        if (getTimeCounter() == getTimeBetweenBeat()) {
            setJump(true);

            player.setInit(true);
            setBeat(getBeat() + 1);
            setTimeCounter(0);
        }
        
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            //Turn g to g2d inorder to use translate function for camera
            Graphics2D g2d = (Graphics2D) g;
            //////////////////////////////////////////////////////////////////

            ////DRAW HERE
            //Everything in between these 2 functions will be affected by camera

            g2d.translate(cam.getX(), cam.getY()); //Begin of cam            
                g.drawImage(Assets.background, -700, 0, width*10, height, null);
                player.render(g);
                bar.render(g);
            
            g2d.translate(cam.getX(), cam.getY()); //End of cam

            //////////////////////////////////////////////////////////////////
            bs.show();
            g.dispose();
        }
    }

    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (running < 0) {
            running = 0;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running >= 0) {
            running = -1;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
