package Rythm;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class used to create enemy instances
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class Enemy extends Item {

    private boolean init;               //tells if the player is making the first jump
    private boolean onPlataform;        //tells if the player is touching a plataform

    private int direction;              //direction of the enemy
    private int distanceToFloor;        //distance from the enemy to the floor
    private int distanceX;              //distance to travel in X in one beat
    private int distanceY;              //distance to travel in Y in one beat
    private int floor;                  //location of the floor
    private int unit;                   //tells the enemy how much to jump

    private Game game;      // to get information from the game


    /**
     * Initializes a new enemy instance
     * @param x x position
     * @param y y position
     * @param width width of the enemy
     * @param height height of the enemy
     * @param game Game instance
     * @param unit the number of pixels the enemy will jump
     */
    public Enemy(int x, int y, int width, int height, Game game, int unit) {
        super(x, y, width, height);
        this.game = game;
        direction = 1;
        distanceX = 0;
        distanceY = game.getHeight() - getHeight();
        floor = y;
        init = false;
        onPlataform = false;
        this.unit = unit;
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
    public void setDirection(int iDirection) {
        this.direction = iDirection;
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

    /**
     * sets <int> distanceToFloor </int> value
     *
     * @param distanceToFloor
     */
    public void setDistanceToFloor(int distanceToFloor) {
        this.distanceToFloor = distanceToFloor;
    }

    /**
     * returns <int> distanceToFloor </int> value
     *
     * @return distanceToFloor
     */
    public int getDistanceToFloor() {
        return distanceToFloor;
    }

    @Override
    public void tick() {
        setX(getX());

        if (game.isJump()) {
            if (game.getBeat() % 4 != 0) {
                //The distance between jumps is calculated and then divided by the number of frames/ticks per jump to get the distance to move each tick
                setDistanceY((getY() - (getY() - unit)) / ((int) game.getTimeBetweenBeat() / 2));
            } else {
                //The distance between jumps is calculated and then divided by the number of frames/ticks per jump to get the distance to move each tick
                setDistanceY((getY() - (getY() - (unit * 2))) / ((int) game.getTimeBetweenBeat() / 2));
            }
            //calculate the distance that is between the player and the floor
            setDistanceToFloor((floor - (getY() - (getDistanceY() * ((int) game.getTimeBetweenBeat() / 2)))) / ((int) game.getTimeBetweenBeat() / 2));
        }

        if (!isInit()) {
            setDistanceY((getY() - (getY() - unit)) / ((int) game.getTimeBetweenBeat() / 2));
            setDistanceToFloor((floor - (getY() - (getDistanceY() * ((int) game.getTimeBetweenBeat() / 2)))) / ((int) game.getTimeBetweenBeat() / 2));
        }

        //here, half of the jump, the enemy goes up and the other half goes down
        if (game.getTimeCounter() < game.getTimeBetweenBeat() / 2) {
            setY(getY() - getDistanceY());
        } else {
            setOnPlataform(false);
            setY(getY() + getDistanceToFloor());
        }
    }

    @Override
    public void render(Graphics g) {
        if (unit == 64) {
            g.setColor(Color.yellow);
            g.drawRect(getX(), getY(), getWidth(), getHeight());
            //g.fillRect(getX(), getY(), 40, 40);
        }else
         if(unit == 64*2){
            g.setColor(Color.gray);
            g.drawRect(getX(), getY(), getWidth(), getHeight());
            //g.fillRect(getX(), getY(), 30, 30);
        }
    }
}
