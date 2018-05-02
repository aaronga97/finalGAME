package Rythm;

import java.awt.image.BufferedImage;

/**
 * In this class, resources, such as sprites and audio, are loaded
 * @author Aarón García
 * @author José Napoleón Lazo
 * @author José Roberto Adame
 * @author Ricardo Lozano
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static SoundClip backgroundMusic;
    public static BufferedImage enemy;      // to store the enemy image
    public static BufferedImage gameOver;   // to store the game over screen
    public static BufferedImage player[];     // to store the player image
    public static BufferedImage sprites;    // to store the animation
    public static SoundClip trackOne;           // to store track for the tutorial
    
    /**
     * initializing the images and sounds of the game
     */
    public static void init() {
        //get the sprites from the picture
        sprites = ImageLoader.loadImage("/images/player.png");
        //create array of images before animations
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        player = new BufferedImage[17];
        //get tehe player animation
        for(int iX=0;iX<17;iX++){
            player[iX] = spritesheet.crop(iX*19,0,19,19);
        }
        // Images
        background = ImageLoader.loadImage("/images/background.jpg"); 
        gameOver = ImageLoader.loadImage("/images/gameover.jpg");
        
        //Sounds
        trackOne = new SoundClip("/Audio/movNaranja.wav");
        trackOne.setLooping(true);
    }
}
