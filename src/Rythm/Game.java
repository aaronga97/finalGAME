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
import java.util.Set;

/**
 * <<<<<<< 1647fadbd9a75cf19a93c416916e8d6079148149
 *
 * @author Ricardo Lozano,Napoleon Lazo, Jose Adame, Aaron Garcia ======= Game
 * manager Here, everything that happens on the game is managed
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano >>>>>>> Added comments and javadoc to most classes
 */
public class Game implements Runnable {
    private boolean canShoot;       //to see if he can shoot
    private boolean jump;           // checks if there is a change in beat
    private double bpm;             // the beats per minute
    private double scoreHelper;     //Helps add the score
    private double timeBetweenBeat; // keeps how many seconds are between beats
    private int beat;               // keeps track of the current beat (1-4)
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
    private int whichLevel;              //Tells you in which level we are
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
    private ArrayList<Platform> platforms; //to change the level
    private Player player;          // to use a player
    private ArrayList<Proyectile> proyectiles; //to shoot multiple times 
    private Platform rightBorder;   // the right border of the game zone
    private String title;           // title of the window
    private Thread thread;          // thread to create the game 
    private ArrayList<StaticStar> stars;
    private StaticStar star;
    private MovingStar mstar;

    private MouseManager MouseManager;  //to manage mouse
    private boolean startclick;
    
    //To know if we are in menu or in game already
    private enum STATE{
      MENU,
      GAME
    };
    
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
        //this is a test comment ignore me
        keyManager = new KeyManager();
        MouseManager = new MouseManager();
        score = 0;
        scoreHelper = 0;
        running = -1;
        canShoot = true;
        shootCounter = 0;
        lives = 3;
        unit = 64; //nuestro estandard unit of measurements
        bpm = 120;//cuantos beats por minuto se tocan, termino musical
        beat = 1;
        whichLevel = 0;
        
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
     *
     * @param beat
     */
    public void setBeat(int beat) {
        this.beat = beat;
    }

    /**
     * returns <int> beat </int> value
     *
     * @return beat
     */
    public int getBeat() {
        return beat;
    }

    /**
     * sets <int> scoreHelper </int> value
     * 
     * @param scoreHelper 
     */
    public void setScoreHelper(double scoreHelper) {
        this.scoreHelper = scoreHelper;
    }

    /**
     * returns the value of scoreHelper
     * 
     * @return an <code> int </code> with the value of scoreHelper
     */
    public double getScoreHelper() {
        return scoreHelper;
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
     * sets <int> lives </int> value
     * 
     * @param lives 
     */
    public void setLives(int lives) {
        this.lives = lives;
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
    
    /**
     * To get the camera
     * @return 
     */
    public Camera getCam() {
        return cam;
    }
    /**
     * To get all the enemies
     * @return 
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * To get the MouseManager
     * @return 
     */
    public MouseManager getMouseManager(){
        return MouseManager;
     }
    /**
     * To get the keyManager
     * @return 
     */
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
    * Clears the platforms and enemies from the screen to load the next level
    */
   public void clearLevel(){
        platforms.clear();
        enemies.clear();
    }
   
   /**
    * Displays the game over screen and shows the highscore
    */
   public void displayGameOver(){
        for(int i = 0; i < 3; i++){
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
            if(getLives() < 0){
                Assets.gameover.play();
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.drawImage(Assets.gameOver, 0, -100, getWidth(), getHeight(), null);
            }
            else {
                g.drawImage(Assets.youWin, 0, 0, getWidth(), getHeight(), null);
            }   
            g.setColor(Color.yellow);
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
    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();        
        Assets.trackOne.play();
        Level.init(this);

        //Mouse being mouse
        display.getJframe().addMouseListener(MouseManager);
        display.getJframe().addMouseMotionListener(MouseManager);
        display.getCanvas().addMouseListener(MouseManager);
        display.getCanvas().addMouseMotionListener(MouseManager);
        
        Level.init(this);

        //Initialize new camera in the corner.
        cam = new Camera(0, 0, this);
        //bar = new Bar(getWidth()/2 - 20 - getUnit() - (int) getCam().getX(), getHeight() - getHeight()/8, 20, 60, this);

        //make random placement on stars
        stars = new ArrayList<StaticStar>();
        Random rand= new Random();
        for(int iX = 0;iX<25;iX++){
            int starX = rand.nextInt((getWidth()*10-0)+1)+0;
            int starY = rand.nextInt((getHeight()/2-0)+1)+0;
            stars.add(new StaticStar(starX,starY,40,40));
        }
        
        

        //create borders to stop the player for getting out of bounds
        leftBorder = new Platform(0 - 600, 0, 10, getHeight() - getHeight() / 4);
        rightBorder = new Platform(12000, 0, 10, getHeight() - getHeight() / 4);
        //adds the player
        player = new Player(0, getHeight() - getHeight() / 4 - 64, 64, 64, this);

        
        

        //start the moving star
        mstar = new MovingStar(0,150,60,30,1);
        
        //Load Tutorial Level
        enemies = new ArrayList<>(Level.tutorialEnemies);
        platforms = new ArrayList<>(Level.tutorialPlatforms);
        lava = new Lava(0,0,0,0);

        end = new End(Level.tutorialEnd);

        //adds the timing bar
        bar = new Bar(getWidth() / 2 - 20 - getUnit(), getHeight() - 30 - (getHeight() / 8), 20, 60, this);

        //creates the proyectiles list
        proyectiles = new ArrayList<Proyectile>();

        display.getJframe().addKeyListener(keyManager);
    }
    
    /**
     * Displays menu until someone clicks mouse
     */
    public void menu(){
        
        while(!(MouseManager.isIzquierdo())){
            bs = display.getCanvas().getBufferStrategy();
            if (bs == null) display.getCanvas().createBufferStrategy(3);
            else {
                g = bs.getDrawGraphics();

                g.drawImage(Assets.startscreen, 0, 0, width, height, null);

                bs.show();
                g.dispose();
            }
        }
        
    }
    
    @Override
    public void run() {
        init();
        menu();
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
    public void makeEnemyChase(Player p, Enemy ene) {
        int speedFollow = 1;

        //Si el enemigo está a la mitad de distancia del jugador (en la pantalla masomenos), siguelo
        if (player.getX() - ene.getX() <= getWidth() / 2) {
            if (player.getX() > ene.getX()) {
                ene.setX(ene.getX() + speedFollow);
            } else if (player.getX() < ene.getX()) {
                ene.setX(ene.getX() - speedFollow);
            } else if (player.getY() > ene.getY()) {
                ene.setY(ene.getY() + speedFollow);
            } else if (player.getY() < ene.getY()) {
                ene.setY(ene.getY() - speedFollow);
            }
        }
    }

    /**
     * Increment score while the player is alive
     */
    public void incrementScore() {
        setScoreHelper(getScoreHelper() + .1);
        setScore((int) Math.floor(scoreHelper));
    }

    /**
     * Resets player to the beginning of level
     */
    public void damagePlayer() {
        setLives(getLives() - 1);
        if (getLives() >= 0) {
            if(getScore() - 300 < 0){
                setScoreHelper(0);
                setScore(0);
            }
            else{
                setScoreHelper((int) getScoreHelper() - 300);
                setScore((int) getScoreHelper());
            }
            player.setDirection(1);
            player.setDistanceX(0);
            player.setDistanceY(0);
            player.setX(0);
            player.setY(getHeight() - getHeight() / 4);
            setBeat(1);
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
    /**
     * Resets the game for the player to play
     */
    public void resetGame() {
        player = new Player(0, getHeight() - getHeight() / 4 - 64, 64, 64, this);
        clearLevel();
        //Load Tutorial Level
        //Load Tutorial Level
        enemies = new ArrayList<>(Level.tutorialEnemies);
        platforms = new ArrayList<>(Level.tutorialPlatforms);
        lava = new Lava(0,0,0,0);

        end = new End(Level.tutorialEnd);
        
        setScore(0);
        setScoreHelper(0);
        setLives(3);
        
    }
     /**
     * Function that creates enemy for desired level
     */
    public Enemy createEnemy(int ex, int wai, int iX){
        if(iX%2==0)return (new Enemy(ex, wai, 64, 64, this, 64));
        else return (new Enemy(ex, wai-15, 64, 64, this, 64*2));
    }
    /**
     * Run this code each tick of the game
     */
    private void tick() {
        keyManager.tick();
        player.tick();
        mstar.tick();
        end.tick();
        incrementScore();

        //tick enemies to chase player, and check if player enemy collide
        for (Enemy e : enemies) {
            e.tick();


            if (player.intersects(e)) {
                damagePlayer();
                Assets.playerhit.play();
            }

            if(whichLevel == 0) {
                makeEnemyChase(player, e);
            }
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

        for (Platform p : platforms) {
            
            if (player.intersects(p)) {
                if (!player.isOnPlataform()) {
                    if (player.getX() + player.getWidth() > p.getX() + player.getDistanceX()
                            && player.getX() <= p.getX() + p.getWidth() - 8) {
                        if (player.getY() + player.getHeight() > p.getY()) {
                            player.setDistanceX(player.getDistanceX() / 2);
                            player.setY(p.getY() - player.getHeight() + 2);
                            
                        }
                        player.setOnPlataform(true);
                        player.setExtraFall(false);
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
            damagePlayer();
            Assets.playerhit.play();
        }

        //if the player ends the level, touches the end

        if(player.intersects(end)){
            

            System.out.println(whichLevel);
            switch (whichLevel) {
                case 0:
                    clearLevel();
                    enemies = new ArrayList<>(Level.levelOneEnemies);
                    platforms = new ArrayList<Platform>(Level.levelOnePlatforms);
                    end = new End(Level.levelOneEnd);

                    whichLevel++;
                    lava = Level.levelOneLava;
                    break;

                case 1:
                    clearLevel();
                    enemies = new ArrayList<>(Level.levelTwoEnemies);
                    platforms = new ArrayList<Platform>(Level.levelTwoPlatforms);
                    end = new End(Level.levelTwoEnd);

                    whichLevel++;
                    break;

                case 2:
                    clearLevel();
                    enemies = new ArrayList<>(Level.levelThreeEnemies);
                    platforms = new ArrayList<Platform>(Level.levelThreePlatforms);
                    end = new End(Level.levelThreeEnd);
                    whichLevel++;
                    break;
                case 3: 
                    setRunning(1);
            }
            
            player.setX(0);
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
        
        //check if shooting star is outside the screen
        if(mstar.getX() >= player.getX() + 650){
            Random rand= new Random();
            int mstarY = rand.nextInt((getHeight()/2-0)+1)+0;
            mstar.setY(mstarY);
            if(mstar.getDirection()==1){
            mstar.setDirection(mstar.getDirection()*-1);
            }
           
        }
        if(mstar.getX() <= player.getX() - 650){
            Random rand= new Random();
            int mstarY = rand.nextInt((getHeight()/2-0)+1)+0;
            mstar.setY(mstarY);
            if(mstar.getDirection()!=1){
                mstar.setDirection(mstar.getDirection()*-1);
            }
        }

        // RESET Feature
        // Resets the entire game
        if(keyManager.isReset()) {
            resetGame();
        }
        
        //BULLETS
        //creates bullet if necessary, only one in screen
        if (keyManager.isSpace()) {
            int offset;
            if (player.getDirection() == 1) {
                offset = player.getWidth() / 2;
            } else {
                offset = -player.getWidth() / 2;
            }

            proyectiles.add(new Proyectile(player.getX()+offset+20,
            player.getY()+player.getHeight()/2, 20, 10,player.getDirection(), this));
            Assets.lazerSound.play();
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
                    setScoreHelper(getScoreHelper() + 100);
                    Assets.hit.play(); 
               }
            }
        }
        //check if the mouse is clicked
        if(MouseManager.isIzquierdo()){
            startclick = true;
        }
    }
    /**
     * Render the game
     */
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
            mstar.render(g);
            for (Enemy e : enemies) {
                e.render(g);
            }

            //Score related
            int tmp = player.getX();
            String s = "Score: "+Integer.toString(getScore());
            Font font = new Font("Serif", Font.BOLD, 32);
            g.setFont(font);

            g.drawString(s, tmp+450, getHeight()-(getHeight()-50));
            
            //Lives realted
            s = "Lives: " + Integer.toString(lives);
            g.drawString(s, tmp - 600, getHeight() - (getHeight() - 50));

            Iterator itr = proyectiles.iterator();
            while (itr.hasNext()) {
                Proyectile bullet = (Proyectile) itr.next();
                bullet.render(g);
            }
            bar.render(g);
            itr = platforms.iterator();
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
