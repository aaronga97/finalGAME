package Rythm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Usuario1
 */
public class Player extends Item {

    private boolean extraFall;   // Boolean to trigger a fall higher than current 'floor'
    private boolean init;        //tells if the player is making the first jump
    private boolean onPlataform; //tells if the player is touching a plataform
    private Animation animation;    // To handle animation of player

    private int direction;      //direction of the player
    private int distanceToFloor; //distance from the player to the floor
    private int distanceX;      //distance to travel in X in one beat
    private int distanceY;      //distance to travel in Y in one beat
    private int floor;           //location of the floor of the level
    private int tempFloor;       // to store the height of the platform the player is on, originally it's equal to floor

    private Game game;      // to access informaction from the game

    /**
     * Initializes new Player instance
     * @param x X postion
     * @param y Y position
     * @param width Width value
     * @param height height value
     * @param game Game instance to access game information
     */
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        direction = 1;  // Moves to the right
        distanceX = 0;  //Starts without moving on X
        distanceY = game.getHeight() - getHeight(); //Starts without moving on X 
        floor = y + height; // floor equals starting Y position
        tempFloor = floor;  //tempFloor starts the same as floor
        onPlataform = false;
        extraFall = false;
        this.animation = new Animation(Assets.player, 40);
    }

    /**
     * sets <boolean> extraFall </boolean> value
     *
     * @param extraFall
     */
    public void setExtraFall(boolean extraFall) {
        this.extraFall = extraFall;
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
     * returns <boolean> init </boolean> value
     *
     * @return init
     */
    public boolean isInit() {
        return init;
    }

    /**
     * sets <boolean> onPlataform </boolean> value
     *
     * @param onPlataform
     */
    public void setOnPlataform(boolean onPlataform) {
        this.onPlataform = onPlataform;
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
     * sets <int> direction </int> value
     *
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
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
     * sets <int> distanceX </int> value
     *
     * @param distanceX
     */
    public void setDistanceX(int distanceX) {
        this.distanceX = distanceX;
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
     * sets <int> distanceY </int> value
     *
     * @param distanceY
     */
    public void setDistanceY(int distanceY) {
        this.distanceY = distanceY;
    }

    /**
     * returns <int> distanceY </int> value
     *
     * @return distanceY
     */
    public int getDistanceY() {
        return distanceY;
    }

    public void setTempFloor(int tempFloor) {
        this.tempFloor = tempFloor;
    }

    public int getTempFloor() {
        return tempFloor;
    }

    /**
     * returns <int> floor </int> value
     *
     * @return
     */
    public int getFloor() {
        return floor;
    }

    @Override
    public void tick() {
        //changes the players direction depending on the key pressed
        if (game.getKeyManager().isLeft()) {
            setDirection(-1);
        } else if (game.getKeyManager().isRight()) {
            setDirection(1);
        }

        //If there is a beat the player jumps
        if (game.isJump() && !extraFall) {

            //if jump is active this makes the character jump
            //if the beat is between 1-3 it performs a normal jump
            // the beat that matches with 4 is a "special jump", which is larger
            if (game.getBeat() % 4 != 0) {
                setDistanceX((game.getUnit() * 2 * getDirection()) / (int) game.getTimeBetweenBeat());
                setDistanceY((2 * game.getUnit()) / (int) game.getTimeBetweenBeat());
            } else {
                setDistanceX((game.getUnit() * 5 * getDirection()) / (int) game.getTimeBetweenBeat());
                setDistanceY((4 * game.getUnit()) / (int) game.getTimeBetweenBeat());
            }
        }

        //moves the player on X between beats
        setX(getX() + getDistanceX());

        // if the player falls on a platform after a jump, it will not jump until next beat, so that rythm is not lost
        if (isOnPlataform() && !game.isJump()) {
            setOnPlataform(false);

            setTempFloor(getY() + getHeight() - 1);
        } 

        // Normally, on a beat the player goes up and down
        // if the player falls from a platform, the player enters an "extra fall"
        // this means the player wont go up until it lands
        else if (game.getTimeCounter() < game.getTimeBetweenBeat() / 2 && !extraFall) {
            setY(getY() - getDistanceY());
        } else {
            
            setY(getY() + (getDistanceY()));

            // when the player falls beyond the platform it was standing (tempFloor), extraFall is activated
            if (getY() + getHeight() > getTempFloor()) {
                extraFall = true;
                setTempFloor(floor);
            }

            //when it lands, extraFall ends and a new floor is set
            if (getY() + getHeight() >= floor) {
                extraFall = false;
                setY(floor - height);
            }
        }
        this.animation.tick(); // Animation plays
    }

    /**
     * Renders player
     * @param g Graphics instance to handle graphics
     */
    @Override
    public void render(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.red);
        g2D.setStroke(new BasicStroke(3F));
        g2D.drawRect(getX(), getY(), getWidth(), getHeight());
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);

    }
}
