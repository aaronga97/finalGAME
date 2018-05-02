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
    public static BufferedImage startscreen;
    public static BufferedImage enemy;      // to store the enemy image
    public static BufferedImage gameOver;   // to store the game over screen
    public static BufferedImage player[];     // to store the player image
    public static BufferedImage sprites;    // to store the animation
    public static SoundClip trackOne;           // to store track for the tutorial
    public static BufferedImage lazer[];
    public static BufferedImage lazerX;
    public static BufferedImage starX;
    public static BufferedImage star[];
    public static SoundClip hit;
    public static SoundClip playerhit;
    public static SoundClip gameover;
    public static SoundClip lazerSound;
    public static BufferedImage endX;
    public static BufferedImage end[];
    public static BufferedImage movingstarX;
    public static BufferedImage movingstar[];
    public static BufferedImage movingstar2X;
    public static BufferedImage movingstar2[];
    /**
     * initializing the images and sounds of the game
     */
    public static void init() {
        //get the sprites from the picture
        sprites = ImageLoader.loadImage("/images/player.png");
        lazerX = ImageLoader.loadImage("/images/lazer.png");
        starX = ImageLoader.loadImage("/images/star.png");
        endX = ImageLoader.loadImage("/images/end.png");
        movingstarX = ImageLoader.loadImage("/images/movingstar.png");
        movingstar2X = ImageLoader.loadImage("/images/movingstar2.png");
        //create array of images before animations
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        SpriteSheet spritesheet2 = new SpriteSheet(lazerX);
        SpriteSheet spritesheet3 = new SpriteSheet(starX);
        SpriteSheet spritesheet4 = new SpriteSheet(endX);
        SpriteSheet spritesheet5 = new SpriteSheet(movingstarX);
        SpriteSheet spritesheet6 = new SpriteSheet(movingstar2X);
        player = new BufferedImage[17];
        lazer = new BufferedImage[2];
        star = new BufferedImage[15];
        end = new BufferedImage[3];
        movingstar = new BufferedImage[19];
        movingstar2 = new BufferedImage[19];
        //get the player animation
        for(int iX=0;iX<17;iX++){
            player[iX] = spritesheet.crop(iX*19,0,19,19);
        }
        for(int i=0;i<2;i++){
            lazer[i]= spritesheet2.crop(i*4,0,4,2);
        }
        for(int i=0;i<15;i++){
            star[i]=spritesheet3.crop(i*10, 0, 10, 10);
        }
        
        for(int i=0;i<3;i++){
            end[i]=spritesheet4.crop(i*30,0,30,32);
        }
        
        for(int i=0;i<19;i++){
            movingstar[i]=spritesheet5.crop(30*i, 0, 30, 15);
            movingstar2[i]=spritesheet6.crop(30*i, 0, 30, 15);
        }
        
        // Images
        background = ImageLoader.loadImage("/Images/background.jpg");
        startscreen = ImageLoader.loadImage("/Images/startscreen.png");
        gameOver = ImageLoader.loadImage("/Images/gameover.jpg");
        //Sounds
        trackOne = new SoundClip("/Audio/Transmission.wav");
        hit = new SoundClip("/Audio/leg.wav");
        playerhit = new SoundClip("/Audio/get.wav");
        gameover = new SoundClip("/Audio/gameover.wav");
        lazerSound = new SoundClip("/Audio/lazer.wav");
        trackOne.setLooping(true);
    }
}