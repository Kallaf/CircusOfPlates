package eg.edu.alexu.csd.oop.game.sample.object;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Shapes implements GameObject{
    	public static final int SPRITE_HEIGHT = 5;
	protected static final int MAX_MSTATE = 1;
        protected Color color;
	// an array of sprite images that are drawn sequentially
	protected BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
        protected int Hardness;
	protected int x;
	protected int y;
	protected int width;
	protected boolean visible;
	protected boolean horizontalOnly;

	
        public abstract Shapes  createShape(int posX, int posY, boolean horizontalOnly,int hardness);
        // pool Design Pattern
        public abstract Shapes copyShape(int x);
        
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
		if(horizontalOnly)
			return;
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}
        public void setSpriteImages(BufferedImage[] spriteImages)
        {
           this.spriteImages = spriteImages; 
        }

	@Override
	public int getWidth(){
		return width;
	}

	@Override
	public int getHeight() {
		return SPRITE_HEIGHT;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
        
        public void setColor(Color color)
        {
            this.color=color;
        }
        
        public Color getColor()
        {
            return color;
        }

        public int getHardness()
        {
            return this.Hardness;
        }

    public void setHardness(int updatedHardness) {
        this.Hardness = updatedHardness;
    }

}
