/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rythm;

import java.awt.image.BufferedImage;

/**
 *
 * @author Usuario1
 */
public class Assets {
    public static BufferedImage background; // to store background image
    
    public static BufferedImage player;     // to store the player image
    public static BufferedImage enemy; // to store the enemy image
    
    public static SoundClip backgroundMusic;
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/background.jpg");
        
        //player = ImageLoader.loadImage("/images/chef.png");
        //enemy = ImageLoader.loadImage("/images/client.png");   
        //backgroundMusic = new SoundClip("/Sounds/music.wav");
        //backgroundMusic.setLooping(true);
    }
}
