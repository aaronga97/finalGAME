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
    public static BufferedImage startscreen; // to store menu screen
    public static BufferedImage enemy;      // to store the enemy image
    public static BufferedImage gameOver;   // to store the game over screen
    public static BufferedImage player[];     // to store the player image
    public static BufferedImage sprites;    // to store the animation
    public static BufferedImage lazer[];    // to store lazer animation
    public static BufferedImage lazerX;     // to store lazer spritesheet      
    public static BufferedImage star[];     // to store star animation
    public static BufferedImage starX;      // to store star spritesheet
    public static BufferedImage endX;       // to store goal spritesheet
    public static BufferedImage end[];      // to store goal animation
    public static BufferedImage movingstarX;    // to store movingStar1 spritesheet
    public static BufferedImage movingstar[];   // to store movingStar1 animation
    public static BufferedImage movingstar2X;   // to store movingStar2 spritesheet
    public static BufferedImage movingstar2[];  // to store movingStar2 animation
    public static BufferedImage youWin;         // store Win screen
    
    public static SoundClip trackOne;           // to store track for the tutorial
    public static SoundClip hit;                // enemy hit sound
    public static SoundClip playerhit;          // player hit sound
    public static SoundClip gameover;           // game over sound
    public static SoundClip lazerSound;         // lazer sound for when shooting
    /**
     * initializing the images and sounds of the game
     */
    public static void init() {
        //load spritesheets
        sprites = ImageLoader.loadImage("/images/player.png");
        lazerX = ImageLoader.loadImage("/images/lazer.png");
        starX = ImageLoader.loadImage("/images/star.png");
        endX = ImageLoader.loadImage("/images/end.png");
        movingstarX = ImageLoader.loadImage("/images/movingstar.png");
        movingstar2X = ImageLoader.loadImage("/images/movingstar2.png");
        youWin = ImageLoader.loadImage("/images/YouWin.png");
        
        //split spritesheets into images
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        SpriteSheet spritesheet2 = new SpriteSheet(lazerX);
        SpriteSheet spritesheet3 = new SpriteSheet(starX);
        SpriteSheet spritesheet4 = new SpriteSheet(endX);
        SpriteSheet spritesheet5 = new SpriteSheet(movingstarX);
        SpriteSheet spritesheet6 = new SpriteSheet(movingstar2X);
        
        //creat animations
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
        
        // Load static images
        background = ImageLoader.loadImage("/Images/background.jpg");
        startscreen = ImageLoader.loadImage("/Images/startscreen.png");
        gameOver = ImageLoader.loadImage("/Images/gameover.jpg");
        
        //Load sounds
        trackOne = new SoundClip("/Audio/Transmission.wav");
        hit = new SoundClip("/Audio/leg.wav");
        playerhit = new SoundClip("/Audio/get.wav");
        gameover = new SoundClip("/Audio/gameover.wav");
        lazerSound = new SoundClip("/Audio/lazer.wav");
        trackOne.setLooping(true);
    }
}