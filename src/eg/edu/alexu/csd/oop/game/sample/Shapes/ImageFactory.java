package eg.edu.alexu.csd.oop.game.sample.Shapes;

import static eg.edu.alexu.csd.oop.game.sample.object.Shapes.SPRITE_HEIGHT;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


// Fly Weight Design Pattern
public abstract class ImageFactory {
    private static BufferedImage shape1image1 = makeImage1(Color.BLUE);
    private static BufferedImage shape1image2 = makeImage1(Color.RED);
    private static BufferedImage shape1image3 = makeImage1(Color.GREEN);
    private static BufferedImage shape1image4 = makeImage1(Color.PINK);
    private static BufferedImage shape1image5 = makeImage1(Color.YELLOW);
    private static BufferedImage shape2image1 = makeImage2(Color.BLUE);
    private static BufferedImage shape2image2 = makeImage2(Color.RED);
    private static BufferedImage shape2image3 = makeImage2(Color.GREEN);
    private static BufferedImage shape2image4 = makeImage2(Color.PINK);
    private static BufferedImage shape2image5 = makeImage2(Color.YELLOW);
    
    private static BufferedImage makeImage1(Color color)
    {
        BufferedImage image = new BufferedImage(20, SPRITE_HEIGHT,	BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(color);
		g2.setBackground(color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawOval(0, 0,20,20);
                g2.fillOval(0, 0,20,20);
		g2.dispose();
                return image;
    }
    
    private static BufferedImage makeImage2(Color color)
    {
        BufferedImage image = new BufferedImage(20, SPRITE_HEIGHT,	BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(color);
		g2.setBackground(color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(20));
		g2.drawLine(0, 0,20, 0);
		g2.dispose();
                return image;
    }
    
    static BufferedImage getShape1(int i)
    {
        switch (i) {
            case 0:
              return shape1image1;
            case 1:
               return shape1image2;
            case 3:
                return shape1image3;
            case 4:
                return shape1image4;    
            default:
                return shape1image5;
        }
    }
    
    static BufferedImage getShape2(int i)
    {
        switch (i) {
            case 0:
              return shape2image1;
            case 1:
               return shape2image2;
            case 3:
                return shape2image3;
            case 4:
                return shape2image4;    
            default:
                return shape2image5;
        }
    }
    
}
