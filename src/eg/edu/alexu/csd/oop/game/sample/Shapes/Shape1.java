package eg.edu.alexu.csd.oop.game.sample.Shapes;

import eg.edu.alexu.csd.oop.game.sample.object.Shapes;
import java.awt.Color;

public class Shape1 extends Shapes {
    
    @Override
    public Shapes  createShape(int posX, int posY, boolean horizontalOnly,int hardness){
		this.x = posX;
		this.y = posY;
		this.width = 20;
                this.Hardness=hardness;
                // Observer Design Pattern
                int colorNO=(int)(Math.random()*(2+hardness));
            switch (colorNO) {
            case 0:
                color=Color.BLUE;
                break;
            case 1:
                color=Color.RED;
                break;
            case 3:
                color=Color.GREEN;
                break;
            case 4:
                color=Color.PINK;
                break;    
            default:
                color=Color.YELLOW;
                break;
        }
               spriteImages[0]=ImageFactory.getShape1(colorNO);
		this.horizontalOnly = horizontalOnly;
		this.visible = true; 
                return this;
	}


    @Override
    public Shapes copyShape(int x) {
       Shapes shape = new Shape1();
       shape.createShape(x, getY(),true,getHardness());
       shape.setSpriteImages(getSpriteImages());
       shape.setColor(getColor());
       return shape;
    }
    
    
}
