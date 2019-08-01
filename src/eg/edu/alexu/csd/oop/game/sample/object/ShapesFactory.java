package eg.edu.alexu.csd.oop.game.sample.object;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
public class ShapesFactory {
    private Class class1;
    private Class class2;
    public ShapesFactory() throws ClassNotFoundException, MalformedURLException, URISyntaxException
    {
        //Dynamic Linkage Design  Pattern ..............
        class1 = Class.forName("eg.edu.alexu.csd.oop.game.sample.Shapes.Shape1");
        class2 = Class.forName("eg.edu.alexu.csd.oop.game.sample.Shapes.Shape2");
    }
    
    public Shapes makeNewShape(int i,int posX, int posY,int hardness) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException
    {
        switch(i){
            case 1:
             Shapes shape1 = (Shapes) class1.newInstance();
             shape1.createShape(posX, posY,false, hardness);
             return shape1;
            case 2:
             Shapes shape2 = (Shapes) class2.newInstance();
             shape2.createShape(posX, posY,false, hardness);
             return shape2;
            default:
               return null; 
   }}
        
}
