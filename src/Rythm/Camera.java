package Rythm;

/**
 * Class that simulates a camera that follows the player moving through the level
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class Camera {

    private float x, y;
    
    private Game game; // Used to get information from the game
    /**
     * Initializes a new Camera instance
     * @param x x position of the camera
     * @param y y position of the camera
     * @param game Game instance needed for game information
     */
    public Camera(float x, float y, Game game){
        this.x = x;
        this.y = y;
        this.game = game;
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

    /**
     * Normal functionality during the game
     * @param player The player instance to follow
     */
    public void tick(Player player) {

        //This will keep the player to appear always on the center of the screen
        setX(-player.getX() + game.getWidth()/2);
    }
}
