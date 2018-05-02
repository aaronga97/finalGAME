package Rythm;

import java.util.ArrayList;

/**
 * Loads all the needed instances to load levels
 *
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class Level {

    public static ArrayList<Platform> tutorialPlatforms;    //Platforms used on the tutorial stage
    public static ArrayList<Platform> levelOnePlatforms;    //Platforms used on the first stage
    public static ArrayList<Platform> levelTwoPlatforms;    //Platforms used on the second stage
    public static ArrayList<Platform> levelThreePlatforms;  //Platforms used on the third stage

    public static Lava levelOneLava;    // Lava instance for level one

    public static End tutorialEnd;  // End instance for tutorial level
    public static End levelOneEnd;  // End instance for first level
    public static End levelTwoEnd;  // End instance for second level
    public static End levelThreeEnd; // End instance for third level

    public static ArrayList<Enemy> tutorialEnemies; //Enemies used on tutorial level
    public static ArrayList<Enemy> levelOneEnemies; //Enemies used on first level
    public static ArrayList<Enemy> levelTwoEnemies; //Enemies used on second level
    public static ArrayList<Enemy> levelThreeEnemies;   //Enemies used on third level

    /**
     * initializes all instances required to create a level
     *
     * @param game Game instance to get information from the game window
     */
    public static void init(Game game) {

        //Tutorial
        tutorialPlatforms = new ArrayList<Platform>();
        tutorialPlatforms.add(new Platform(500, 500, 3000, 40));

        tutorialEnd = new End(3400, 400, 100, 100);
        tutorialEnemies = new ArrayList<Enemy>();

        for (int i = 1; i < 30; ++i) {
            //Generate enemies randomly inside a minimum separated range of 1k pixels between each enemy
            int ex = (int) (Math.random() * 750 + i * 750);
            tutorialEnemies.add(new Enemy(ex, game.getHeight() - game.getHeight() / 4 - 90, 64, 64, game, 64));
            if (i % 3 == 0) {
                ex = (int) (Math.random() * 750 + i * 750);
                tutorialEnemies.add(new Enemy(ex, game.getHeight() - game.getHeight() / 4 - 115, 64, 64, game, 64 * 2));
            }
        }

        //Level 1
        levelOnePlatforms = new ArrayList<Platform>();
        levelOneEnemies = new ArrayList<Enemy>();
        
        for (int iX = 0; iX < 10; iX++) {
            int ex = 500 + 500 * iX;
            int wai = 515;
            Enemy e = game.createEnemy(ex + 250, game.getHeight() - game.getHeight() / 4 - 90, iX);

            levelOnePlatforms.add(new Platform(ex, wai, 400, 20));//add the platforms
            levelOneEnemies.add(e);
        }
        levelOneLava = new Lava(550, 520, 10000, 20);//add lava in the floor

        levelOneEnd = new End(5000, 400, 100, 100);//set the end goal

        //Level 2
        levelTwoPlatforms = new ArrayList<Platform>();
        levelTwoEnemies = new ArrayList<Enemy>();
        for (int iX = 0; iX < 10; iX++) {
            int ex = 500 + 500 * iX;
            int wai = 515 - 40 * iX;
            levelTwoPlatforms.add(new Platform(ex, wai, 450, 20));
            levelTwoEnemies.add(game.createEnemy(ex + 250, wai - 75, iX));
        }
        levelTwoPlatforms.add(new Platform(5200, 500, 1000, 40));

        levelTwoEnd = new End(5500, 400, 100, 100);

        //Level 3
        levelThreePlatforms = new ArrayList<Platform>();
        levelThreeEnemies = new ArrayList<Enemy>();

        for (int iX = 0; iX < 3; iX++) {
            int ex = 500 + 500 * iX;
            int wai = 515 - 40 * iX;
            levelThreePlatforms.add(new Platform(ex, wai, 450, 20));
            levelThreeEnemies.add(game.createEnemy(ex + 250, wai - 75, iX));
        }
        levelThreePlatforms.add(new Platform(2100, 515 - 80, 570, 20));

        for (int iX = 5; iX < 8; iX++) {
            int ex = 500 + 480 * iX;
            int wai = 515 - 80;
            levelThreePlatforms.add(new Platform(ex, wai, 400, 20));
            levelThreeEnemies.add(game.createEnemy(ex + 250, wai - 75, iX));
        }

        levelThreePlatforms.add(new Platform(500 + 490 * 8, 515 - 40, 450, 20));
        levelThreePlatforms.add(new Platform(500 + 500 * 9, 500, 620, 20));

        levelThreeEnd = new End(5500, 400, 100, 100);
    }
}
