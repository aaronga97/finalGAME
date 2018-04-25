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
    private BufferStrategy bh;
    private Graphics g;             // to paint objects
    private Graphics h;             //to paint objects
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
    private Platform platform;
    private ArrayList<Enemy> enemies; // to store enemies
    private ArrayList<Enemy> poweredEnemies; // to store enemies
    private Enemy enemy;            //to test enemy addition

    private ArrayList<Proyectile> proyectiles;
    private boolean canShoot;
    private int shootCounter;
    private int score;              //Keeps track of player score
    private int enemyNumbers = 30;  
    private SoundClip testTrack;
    private ArrayList<Platform> level;
    private Lava lava;
    Camera cam;
    private End end;

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
        testTrack = new SoundClip("/Audio/movNaranja.wav");
        testTrack.setLooping(true);
        //testTrack.play();
        
        keyManager = new KeyManager();
        score = 0;
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

   public void level1(){
       //nivel 1
        for(int iX=0;iX<20;iX++){
            level.add(new Platform(500+500*iX,515,400,20));
        }
        lava = new Lava(550,520,10000,20);
        player.setX(0);
        //falta endgoal
   }
   
   public void clearLevel(){
        Iterator itr = level.iterator();
        while (itr.hasNext()) {
            Platform p = (Platform) itr.next();
            //if the enemy is out of the screen delete it
            level.remove(p);
            itr = level.iterator();
        }
        
        //Delete enemy and bullet if they intersect
        Iterator itr2 = enemies.iterator();
            //Itera todos los enemigos
            while(itr2.hasNext()){
                Enemy ene = (Enemy) itr2.next();
                //Si interesecta borra los 2, y reseta ambos iteradores dsps de borrar y add 10 to score
                enemies.remove(ene);
                itr2 = enemies.iterator();
            }
        end.setX(-5000);
        end.setY(5000);
   }

   
    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();

        //Initialize new camera in the corner.
        cam = new Camera(0, 0);
        //bar = new Bar(getWidth()/2 - 20 - getUnit() - (int) getCam().getX(), getHeight() - getHeight()/8, 20, 60, this);
        //Assets.backgroundMusic.play();


        //Create enemies array list
        enemies = new ArrayList<Enemy>();
        for(int i = 0; i < enemyNumbers; ++i){
            //Generate enemies randomly inside a range of 1k pixels for each enemy
            int ex = (int) (Math.random() * 1000 + i * 1000);
            enemies.add(new Enemy(ex, getHeight() - getHeight()/4 - 90, 64, 64, this, 64));
            if(i % 3 == 0){
                ex = (int) (Math.random() * 1000 + i * 1000);
                enemies.add(new Enemy(ex, getHeight() - getHeight()/4 - 90, 64, 64, this, 64*4));
            }
        }
        

        player = new Player(0, getHeight() - getHeight()/4 - 64, 64, 64, this);


        //tutorial 1
        level = new ArrayList<Platform>();
        lava = new Lava(0,0,0,0);
        level.add(new Platform(500, 500, 3000, 40));
        end = new End(3400,400,100,100,0);
        //bar = new Bar(getWidth()/2 - 20 - getUnit() - (int) getCam().getX(), getHeight() - 30 - (getHeight()/8), 20, 60, this);
        
        bar = new Bar(getWidth()/2 - 20 - getUnit(), getHeight() - 30 - (getHeight()/8), 20, 60, this);
        
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
    
    /**
    * Make enemy chase player && change zombie direction animation
    **/
    /*
    public void makeEnemyChase(Player p, Enemy ene){
        int speedFollow = 3;
        
        //Si el enemigo está a la mitad de distancia del jugador (en la pantalla masomenos), siguelo
        if(player.getX() - ene.getX() <= getWidth()/2){
            if(player.getX() > ene.getX()) ene.setX(ene.getX() + speedFollow);
            else if (player.getX() < ene.getX()) ene.setX(ene.getX() - speedFollow); 
            else if(player.getY() > ene.getY()) ene.setY(ene.getY() + speedFollow);
            else if (player.getY() < ene.getY()) ene.setY(ene.getY() - speedFollow);
        }
    }
*/
    private void tick() {
        keyManager.tick();
        player.tick();

        //tick enemies
        for(Enemy e : enemies){
            //e.tick();
           //  makeEnemyChase(player, e);
        }
        cam.tick(player);
        bar.tick();
        
        //checks all platforms for collisions
        for(Platform p : level){
            if(player.intersects(p)){
                if(!player.isOnPlataform()){ 
                    if(player.getX() + player.getWidth() > p.getX() + player.getDistanceX() 
                           && 
                            player.getY() + player.getHeight() <= p.getY() + 8) {
                        player.setOnPlataform(true);
                        player.setTempFloor(player.getY() + player.getHeight());
                    }
                    else{
                        player.setDirection(player.getDirection() * -1);
                        player.setDistanceX(player.getDistanceX()*player.getDirection());    
                    }
                } 
            }

        }
        // if jump was set to true on the previous tick, make it false
        if(isJump()) {
            setJump(false);
        }
        //if the player touches the lava
        if(player.intersects(lava)){
            player.setDirection(1);
            player.setDistanceX(0);
            player.setDistanceY(0);
            player.setX(0);
            player.setY(getHeight() - getHeight()/4);
            setBeat(1);
        }
        
        if(player.intersects(end)){
            int levelNum = end.getLevel();
            if(levelNum==0){
                clearLevel();
                level1();
            }
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
            
        //BULLETS
        //creates bullet if necessary, only one in screen
        if (keyManager.isSpace()) {
            proyectiles.add(new Proyectile(player.getX(),
            player.getY()+player.getHeight()/2, 20, 10,player.getDirection(), this));
        }
        //tick every bullet
        Iterator itr = proyectiles.iterator();
        while (itr.hasNext()) {
            Proyectile p = (Proyectile) itr.next();
            p.tick();
            //if the enemy is out of the screen delete it
            if(p.getX() >= player.getX()+650 || p.getX() <= player.getX()-650){
                proyectiles.remove(p);
                itr = proyectiles.iterator();
            }  
            
            //Delete enemy and bullet if they intersect
            Iterator itr2 = enemies.iterator();
            //Itera todos los enemigos
            while(itr2.hasNext()){
                Enemy ene = (Enemy) itr2.next();
                //Si interesecta borra los 2, y reseta ambos iteradores dsps de borrar y add 10 to score
                if(p.intersects(ene)){
                    proyectiles.remove(p);
                    enemies.remove(ene);
                    itr = proyectiles.iterator();
                    itr2 = enemies.iterator();
                    score += 10;
                }
            }
        }
        
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
       // bh = display.getCanvas2().getBufferStrategy();
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
            //h = bh.getDrawGraphics();
            //Turn g to g2d inorder to use translate function for camera
            Graphics2D g2d = (Graphics2D) g;
            //////////////////////////////////////////////////////////////////

            ////DRAW HERE
            //Everything in between these 2 functions will be affected by camera
            //h.drawRect(20, 20, 20, 20);
           // h.fillRect(20, 20, 20, 20);
            g2d.translate(cam.getX(), cam.getY()); //Begin of cam            
                g.drawImage(Assets.background, -700, 0, width*10, height, null);
                player.render(g);
                lava.render(g);
                end.render(g);
                for(Enemy e : enemies)
                    e.render(g);

                Iterator itr = proyectiles.iterator();
                while (itr.hasNext()) {
                    Proyectile bullet = (Proyectile) itr.next();
                    bullet.render(g);
                }
                bar.render(g);
                itr = level.iterator();
                while (itr.hasNext()) {
                    Platform level = (Platform) itr.next();
                    level.render(g);
                }
                g.drawRect(getWidth()/2 - 20 - getUnit() - (int) getCam().getX(), getHeight() - getHeight()/8-35,unit*2+30,70);
                g.drawRect(getWidth()/2 - 20 - getUnit() - (int) getCam().getX()+getUnit()*2-10, getHeight() - getHeight()/8-35,40,70);
                
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
