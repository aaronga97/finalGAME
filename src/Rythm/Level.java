/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.util.ArrayList;

/**
 *
 * @author ricky
 */
public class Level {
    public static ArrayList<Platform> tutorialPlatforms;
    public static ArrayList<Platform> levelOnePlatforms;
    public static ArrayList<Platform> levelTwoPlatforms;
    public static ArrayList<Platform> levelThreePlatforms;
    
    public static Lava levelOneLava;
    public static Lava levelTwoLava;
    public static Lava levelThreeLava;
    
    public static End tutorialEnd;
    public static End levelOneEnd;
    public static End levelTwoEnd;
    public static End levelThreeEnd;
    
    public static ArrayList<Enemy> tutorialEnemies;
    public static ArrayList<Enemy> levelOneEnemies;
    public static ArrayList<Enemy> levelTwoEnemies;
    public static ArrayList<Enemy> levelThreeEnemies;
    
    public static void init(Game game) {
        //Tutorial
        tutorialPlatforms = new ArrayList<Platform>();
        tutorialPlatforms.add(new Platform(500, 500, 3000, 40));

        tutorialEnd = new End(3400, 400, 100, 100, 0);
        
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
        for (int iX = 0; iX < 10; iX++) {
            levelOnePlatforms.add(new Platform(500 + 500 * iX, 515, 400, 20));//add the platforms
        }
        levelOneLava = new Lava(550, 520, 10000, 20);//add lava in the floor
        levelOneEnd = new End(5000, 400, 100, 100, 1);//set the end goal
        
        //Level 2
        levelTwoPlatforms = new ArrayList<Platform>();
        for (int iX = 0; iX < 10; iX++) {
            levelTwoPlatforms.add(new Platform(500 + 500 * iX, 515 - 40 * iX, 450, 20));
        }
        levelTwoPlatforms.add(new Platform(5200, 500, 1000, 40));
        levelTwoEnd = new End(5500, 400, 100, 100, 2);
        
        //Level 3
        levelThreePlatforms = new ArrayList<Platform>();
        for(int iX = 0; iX < 3; iX++){
            levelThreePlatforms.add(new Platform(500 + 500 * iX, 515-40*iX, 450,20));    
        }
        levelThreePlatforms.add(new Platform(2100, 515 -80, 570, 20));
        for(int iX=5;iX<8;iX++){
            levelThreePlatforms.add(new Platform(500+480*iX,515 - 80,400,20));
        }
        
        levelThreePlatforms.add(new Platform(500+490*8,515 - 40,450,20));
        levelThreePlatforms.add(new Platform(500+500*9,500,620,20));
        levelThreeEnd = new End(5500, 400, 100, 100, 2);
    }
}
