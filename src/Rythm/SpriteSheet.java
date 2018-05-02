package Rythm;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;	// sprite sheet

    /**
     * <b>SpriteSheet</b> constructor
     *
     * @param sheet is the spreadsheet
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Crop a sprite from sprite sheet
     *
     * @param x is the <code>x</code> position
     * @param y is the <code>y</code> position
     * @param width is the width of the sprite
     * @param height is the height of the sprite
     * @return sprite from sprite sheet
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
