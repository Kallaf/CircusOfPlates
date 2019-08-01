package eg.edu.alexu.csd.oop.game.sample.object;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.sample.world.PlateWorld;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player implements GameObject {

    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean horizontalOnly;
    private static Player instance;
// private constructor to prevent more than one instance
    private Player() {
    }

    public Player(int posX, int posY, String path, boolean horizontalOnly) {
        this.x = posX;
        this.y = posY;
        this.horizontalOnly = horizontalOnly;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(PlateWorld.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//Singelton Design Pattern .......................
   // to always return same instance
    public static Player getInstance(int posX, int posY, String path, boolean horizontalOnly) {
        if (instance == null) {
            instance = new Player(posX, posY, path, horizontalOnly);
        }
        return instance;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (horizontalOnly) {
            return;
        }
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
