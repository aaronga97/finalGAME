/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
<<<<<<< 1647fadbd9a75cf19a93c416916e8d6079148149
 *
 * @author Ricardo Lozano,Napoleon Lazo, Jose Adame, Aaron Garcia
=======
 * Game manager
 * Here, everything that happens on the game is managed
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
>>>>>>> Added comments and javadoc to most classes
 */
public class Game implements Runnable {

    private boolean canShoot;       //to see if he can shoot
    private boolean jump;           // checks if there is a change in beat

    private double bpm;             // the beats per minute
    private double scoreHelper;     //Helps add the score
    private double timeBetweenBeat; // keeps how many seconds are between beats

    private int beat;               // keeps track of the current beat (1-4)
    private int enemyNumbers = 30;  //to count the number of enemies
    private int height;             // height of the window
    private int lives;              //Player lives
    private int running;            // to set the game
    private int score;              //Keeps track of player score 
    private int shootCounter;       //to count the shooting
    private int timeCounter;        // keeps track of the seconds
    private int unit;               // the game's metric units
    private int width;              // width of the window
    
    private String highscore;       //stores the player's highscore

    private Bar bar;                // the beat bar that will help the user keep rythm visually
    private BufferStrategy bs;      // to have several buffers when displaying
    private BufferStrategy bh;
    private Camera cam;             //camera to follow player
    private Display display;        // to display in the game
    private ArrayList<Enemy> enemies; // to store enemies
    private End end;                 //to change the level
    private Enemy enemy;            //to test enemy addition
    private Files files;
    private Graphics g;             // to paint objects
    private Graphics h;             //to paint objects
    private KeyManager keyManager;  // to manage the keyboard
    private Lava lava;              //platform to make a challenge
    private Platform leftBorder;    // the left border of the game zone
    private ArrayList<Platform> level; //to change the level
    private Player player;          // to use a player
    private ArrayList<Enemy> poweredEnemies; // to store enemies
    private ArrayList<Proyectile> proyectiles; //to shoot multiple times 
    private Platform rightBorder;   // the right border of the game zone
    private String title;           // title of the window
    private Thread thread;          // thread to create the game 
    private ArrayList<StaticStar> stars;
    private StaticStar star;


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

        keyManager = new KeyManager();
        score = 0;
        scoreHelper = 0;
        running = -1;
        canShoot = true;
        shootCounter = 0;
        lives = 3;
        unit = 64; //nuestro estandard unit of measurements
        bpm = 120;//cuantos beats por minuto se tocan, termino musical
        beat = 1;
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
     * returns <boolean> jump </boolean> value
     *
     * @return jump
     */
    public boolean isJump() {
        return jump;
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
     * sets <int> beat </int> value
     * @param beat 
     */
    public void setBeat(int beat) {
        this.beat = beat;
    }

    /**
     * returns <int> beta </int> value
     * @return beat
     */
    public int getBeat() {
        return beat;
    }

    /**
     * sets <double> timeBetweenBeat </double> value
     *
     * @param timeBetweenBeat
     */
    public void setTimeBetweenBeat(double timeBetweenBeat) {
        this.timeBetweenBeat = timeBetweenBeat;
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
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the number of lives left
     * 
     * @return an <code>int</code> value with the number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * To set if the game is running or not
     * 
     * @param running
     */
    public void setRunning(int running) {
        this.running = running;
    }

    /**
     * To set the score of the player
     * 
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * To get the current score of the player
     * 
     * @return an <code> int </code> value with the number of the score
     */
    public int getScore() {
        return score;
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
     * returns <int> timeCounter </int> value
     *
     * @return timeCounter
     */
    public int getTimeCounter() {
        return timeCounter;
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
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To set the highscore of the player
     * 
     * @param highscore 
     */
    public void setHighscore(String highscore) {
        this.highscore = highscore;
    }

    /**
     * To get the highscore of the player
     * 
     * @return an <code> String </code> with the highscore of the player
     */
    public String getHighscore() {
        return highscore;
    }
    
    //return camera
    public Camera getCam() {
        return cam;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * returns <player> player </player> object
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

     /*
     * 
     * @return The ArrayList that contains all Proyectile instances
     */
    public ArrayList<Proyectile> getProyectiles() {
        return proyectiles;
    }
   
    /**
     * Creates platforms for level 1
     */ 
    public void level1(){       
        for(int iX=0;iX<10;iX++){
            level.add(new Platform(500+500*iX,515,400,20));//add the platforms
        }
        lava = new Lava(550,520,10000,20);//add lava in the floor
        end.setX(5000);//set the end goal
        end.setY(400);
        player.setX(0);//reset the player position
   }
   

    
   
   /**
     * Creates platforms for level 2
     */ 
   public void level2(){
       //nivel 2
       for(int iX=0;iX<10;iX++){
           level.add(new Platform(500+500*iX,515-40*iX,450,20));
       }
       level.add(new Platform(5200, 500, 1000, 40));
       end.setX(5500);
       end.setY(400);
       player.setX(0);
   }
   
   /**
     * Creates platforms for level 1
   */ 
   public void level3(){
       //nivel 3
        for(int iX = 0; iX < 3; iX++){
            level.add(new Platform(500 + 500 * iX, 515-40*iX, 450,20));    
        }
        level.add(new Platform(2100, 515 -80, 570, 20));
        for(int iX=5;iX<8;iX++){
            level.add(new Platform(500+480*iX,515 - 80,400,20));
        }
        level.add(new Platform(500+490*8,515 - 40,450,20));
        level.add(new Platform(500+500*9,500,620,20));
        end.setX(5500);
        end.setY(400);
        player.setX(0);
        
   }
  
   /**
    * Clears the platforms and enemies from the screen to load the next level
    */
   public void clearLevel(){
        Iterator itr = level.iterator();
        while (itr.hasNext()) {
            Platform p = (Platform) itr.next();
            level.remove(p);
            itr = level.iterator();
        }

        //Delete enemy 
        Iterator itr2 = enemies.iterator();
            //Itera todos los enemigos
            while(itr2.hasNext()){
                Enemy ene = (Enemy) itr2.next();
                enemies.remove(ene);
                itr2 = enemies.iterator();
            }
        end.setX(-5000);
        end.setY(5000);
    }
   
   //displays the game over screen
   public void displayGameOver(){
        for(int i = 0; i < 3; i++){
            if(getLives() == 0){
                String s = Integer.toString(getScore());
                Font font = new Font("Serif", Font.BOLD, 32);
                // Get the FontMetrics
                FontMetrics metrics = g.getFontMetrics(font);
                files.loadFile(this);
                // Determine the X coordinate for the text
                int x = (getWidth() - metrics.stringWidth("Highscore: " + getHighscore())) / 2;
                // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                int y = getHeight() - (getHeight() - metrics.getHeight()) / 4 + metrics.getAscent();
                g = bs.getDrawGraphics();
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.yellow);
                g.drawImage(Assets.gameOver, 0, -100, getWidth(), getHeight(), null);
                g.setFont(font);            
                g.drawString("Highscore: " + getHighscore(), x, y);
                if(Integer.parseInt(getHighscore()) > getScore()){
                    s = "Your Score: " + s;
                    x = (getWidth() - metrics.stringWidth(s)) / 2;
                    g.drawString(s, x, y + 40);
                }
                else{
                    s = "New Highscore: " + s;
                    x = (getWidth() - metrics.stringWidth(s)) / 2;
                    g.drawString(s, x, y + 40);
                }
                bs.show();
                g.dispose();
            }
        }
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        Assets.trackOne.play();
        
        //Initialize new camera in the corner.
        cam = new Camera(0, 0, this);
        //bar = new Bar(getWidth()/2 - 20 - getUnit() - (int) getCam().getX(), getHeight() - getHeight()/8, 20, 60, this);
        //Assets.backgroundMusic.play();

        //Create enemies array list
        enemies = new ArrayList<Enemy>();
        for (int i = 1; i < enemyNumbers; ++i) {
            //Generate enemies randomly inside a minimum separated range of 1k pixels between each enemy
            int ex = (int) (Math.random() * 750+ i * 750);
            enemies.add(new Enemy(ex, getHeight() - getHeight() / 4 - 90, 64, 64, this, 64));
            if (i % 3 == 0) {
                ex = (int) (Math.random() * 750 + i * 750);
                enemies.add(new Enemy(ex, getHeight() - getHeight()/4 - 115, 64, 64, this, 64*2));
            }
        }
        stars = new ArrayList<StaticStar>();
        Random rand= new Random();
        for(int iX = 0;iX<25;iX++){
            int starX = rand.nextInt((getWidth()*10-0)+1)+0;
            int starY = rand.nextInt((getHeight()/2-0)+1)+0;
            stars.add(new StaticStar(starX,starY,40,40));
        }

        
        //create borders to stop the player for getting out of bounds
        leftBorder = new Platform(0 - 600, 0 , 10, getHeight() - getHeight()/4);
        rightBorder = new Platform(12000, 0, 10, getHeight() - getHeight()/4);
        //adds the player
        player = new Player(0, getHeight() - getHeight()/4 - 64, 64, 64, this);

        //tutorial 1
        level = new ArrayList<Platform>();
        lava = new Lava(0, 0, 0, 0);
        level.add(new Platform(500, 500, 3000, 40));

        end = new End(3400,400,100,100,0);
        //adds the timing bar
        bar = new Bar(getWidth()/2 - 20 - getUnit(), getHeight() - 30 - (getHeight()/8), 20, 60, this);

        //creates the proyectiles list
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
        displayGameOver();
        Assets.trackOne.stop();
        if(Integer.parseInt(getHighscore()) < getScore()){
            files.saveFile(this);
        }
        stop();
    }

    /**
    * Make enemy chase player && change zombie direction animation
    *
    */
    public void makeEnemyChase(Player p, Enemy ene){
        int speedFollow = 1;
        
        //Si el enemigo está a la mitad de distancia del jugador (en la pantalla masomenos), siguelo
        if(player.getX() - ene.getX() <= getWidth()/2){
            if(player.getX() > ene.getX()) ene.setX(ene.getX() + speedFollow);
            else if (player.getX() < ene.getX()) ene.setX(ene.getX() - speedFollow); 
            else if(player.getY() > ene.getY()) ene.setY(ene.getY() + speedFollow);
            else if (player.getY() < ene.getY()) ene.setY(ene.getY() - speedFollow);
        }
    }
    
    /**
     * Increment score while the player is alive
     */
    public void incrementScore(){
        scoreHelper += .1;
        setScore((int)Math.floor(scoreHelper));
    }
    
    /**
     * Resets player to the beginning of level
     */
    public void resetPlayer(){
        if(getLives() != 0){
            player.setDirection(1);
            player.setDistanceX(0);
            player.setDistanceY(0);
            player.setX(0);
            player.setY(getHeight() - getHeight() / 4);
            setBeat(1);
            lives--;
        }
        else{
            setRunning(1);
        }
    }

    /**
     * Display enemy score jump
     */
    public void enemyScoreJump(Enemy e){
        bs = display.getCanvas().getBufferStrategy();
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

            //Score related
            int ex = e.getX(), wai = e.getY();
            String s = "+100";
            Font font = new Font("Serif", Font.BOLD, 32);
            g.setFont(font);
            g.setColor(new Color(198,226,255));
            g.drawString(s, ex, wai-20);

            g2d.translate(cam.getX(), cam.getY()); //End of cam
            //////////////////////////////////////////////////////////////////
            bs.show();
            g.dispose();
        }
    }

    private void tick() {
        keyManager.tick();
        player.tick();
        
        incrementScore();
        
        //tick enemies to chase player, and check if player enemy collide
        for(Enemy e : enemies){
            e.tick();
            makeEnemyChase(player, e);
            
            if(player.intersects(e)) resetPlayer();
        }

        cam.tick(player);
        bar.tick();

        //checks if player collides with borders
        if (player.intersects(leftBorder)) {
            player.setDirection(1);
            player.setDistanceX(player.getDistanceX() * -1);

        }

        if (player.intersects(rightBorder)) {
            player.setDirection(player.getDirection() * -1);
            player.setDistanceX(player.getDistanceX() * player.getDirection());
        }

        //checks bullet collision with borders 
        Iterator itrP = proyectiles.iterator();
        while (itrP.hasNext()) {
            Proyectile p = (Proyectile) itrP.next();
            //if the enemy is out of the screen delete it
            if (p.intersects(leftBorder) || p.intersects(rightBorder)) {
                proyectiles.remove(p);
                itrP = proyectiles.iterator();
            }
        }

        //checks all platforms for collisions
        for (Platform p : level) {
            if (player.intersects(p)) {
                if (!player.isOnPlataform()) {
                    if (player.getX() + player.getWidth() > p.getX() + player.getDistanceX()
                            && player.getX() <= p.getX() + p.getWidth() - 8){
                        if(player.getY() + player.getHeight() <= p.getY() + p.getHeight()){
                            player.setY(p.getY()-player.getHeight() + 2);
                        }
                        player.setOnPlataform(true);
                        player.setExtraFall(false);
                        player.setTempFloor(player.getY() + player.getHeight());
                    } else {
                        player.setDirection(player.getDirection() * -1);
                        player.setDistanceX(player.getDistanceX() * -1);
                    }
                }
            }

        }
        // if jump was set to true on the previous tick, make it false
        if (isJump()) {
            setJump(false);
        }
        //if the player touches the lava, decreas 1 live
        if (player.intersects(lava)) {
            resetPlayer();
        }
        
        //if the player ends the level, touches the end
        if(player.intersects(end)){
            int levelNum = end.getLevel();
            switch (levelNum){
                case 0: clearLevel();
                level1();
                end.setLevel(1);
                break;
                
                case 1: clearLevel();
                level2();
                end.setLevel(2);
                break;
                
                case 2: clearLevel();
                level3();
                end.setLevel(3);
                break;
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

            setBeat(getBeat() + 1);
            setTimeCounter(0);
        }

        //BULLETS
        //creates bullet if necessary, only one in screen
        if (keyManager.isSpace()) {
            int offset;
            if(player.getDirection()==1){
                offset = player.getWidth()/2;
            }else{
                offset = -player.getWidth()/2;
            }
            proyectiles.add(new Proyectile(player.getX()+offset+20,
            player.getY()+player.getHeight()/2, 20, 10,player.getDirection(), this));
        }
        //tick every bullet
        Iterator itr = proyectiles.iterator();
        while (itr.hasNext()) {
            Proyectile p = (Proyectile) itr.next();
            p.tick();
            //if the enemy is out of the screen delete it
            if (p.getX() >= player.getX() + 650 || p.getX() <= player.getX() - 650) {
                proyectiles.remove(p);
                itr = proyectiles.iterator();
            }

            //Delete enemy and bullet if they intersect
            Iterator itr2 = enemies.iterator();
            //Itera todos los enemigos
            while (itr2.hasNext()) {
                Enemy ene = (Enemy) itr2.next();
                //Si interesecta borra los 2, y reseta ambos iteradores dsps de borrar y add 10 to score
                if (p.intersects(ene)) {
                    proyectiles.remove(p);
                    enemyScoreJump(ene);
                    enemies.remove(ene);
                    itr = proyectiles.iterator();
                    itr2 = enemies.iterator();
                    scoreHelper += 100;
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
            g.drawImage(Assets.background, -700, 0, width * 10, height, null);
            player.render(g);
            leftBorder.render(g);
            rightBorder.render(g);
            lava.render(g);
            end.render(g);
            for (Enemy e : enemies) {
                e.render(g);
            }
            
            //Score related
            int tmp = player.getX();
            String s = Integer.toString(score);
            Font font = new Font("Serif", Font.BOLD, 32);
            g.setFont(font);
            g.drawString(s, tmp+500, getHeight()-(getHeight()-50));
            
            //Lives realted
            s = "Lives: " + Integer.toString(lives);
            g.drawString(s, tmp-600, getHeight()-(getHeight()-50));

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
            
            itr = stars.iterator();
            while(itr.hasNext()){
                StaticStar star = (StaticStar) itr.next();
                star.tick();
                star.render(g);
            }
            
            g.drawRect(getWidth() / 2 - 20 - getUnit() - (int) getCam().getX(), getHeight() - getHeight() / 8 - 35, unit * 2 + 30, 70);
            g.drawRect(getWidth() / 2 - 20 - getUnit() - (int) getCam().getX() + getUnit() * 2 - 10, getHeight() - getHeight() / 8 - 35, 40, 70);
            
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
