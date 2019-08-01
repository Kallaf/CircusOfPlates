package eg.edu.alexu.csd.oop.game.sample.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.sample.object.BallObject;
import eg.edu.alexu.csd.oop.game.sample.object.BarObject;
import eg.edu.alexu.csd.oop.game.sample.object.Hardness;
import eg.edu.alexu.csd.oop.game.sample.object.Player;
import eg.edu.alexu.csd.oop.game.sample.object.Shapes;
import eg.edu.alexu.csd.oop.game.sample.object.ShapesFactory;
import iterator.Iterator;
import iterator.namerespository;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlateWorld implements World,GameStrategy{
    // for iterator dp    
    namerespository rp = new namerespository();
       
        
    	private static int MAX_TIME = (int) (1 * 60 * 1000);	// 1 minute
	protected int score = 0;
        private boolean gameStarted = false;
        private boolean gameNotEnded = true;
	private long startTime;
        private boolean timeout =false;
        private int hardness=1;
	private final int width;
	private final int height;
        private ShapesFactory shapesFactory ;
        private HighScoresSS highScoresSS;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
        private final List<GameObject> leftHandArray = new LinkedList<GameObject>();
        private final List<GameObject> rigthHandArray = new LinkedList<GameObject>();
	public PlateWorld(int screenWidth, int screenHeight) throws ClassNotFoundException, MalformedURLException, URISyntaxException, Exception {
            shapesFactory = new ShapesFactory();
            highScoresSS = new HighScoresSS(hardness);
		width = screenWidth;
		height = screenHeight;
                constant.add(new Hardness(0, (int)(height/3-50),"easy.jpg"));
                constant.add(new Hardness((int)(width/3+50), (int)(height/3-50),"medium.jpg"));
                constant.add(new Hardness((int)(width/3)*2+90, (int)(height/3-50),"hard.jpg"));
                control.add(new BallObject(width/3, (int)(height*0.8),Color.YELLOW));
	}
	private boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}
	@Override 
	public boolean refresh() {
                if(!gameStarted) {
                    for(int i=0;i<3;i++)
                    {
                       if(intersect(control.get(0),constant.get(i)))
                       {
                           hardness=i+1;
                           try {
                               highScoresSS.setHardness(hardness);
                           } catch (Exception ex) {
                               Logger.getLogger(PlateWorld.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           gameStarted=true;
                       }
                    }
                    if(gameStarted)
                    {
               control.remove(0);
                constant.remove(2);
                constant.remove(1);
                constant.remove(0);
                startTime = System.currentTimeMillis();        
                //constant objects 
                constant.add(new BarObject(0,height/7, width/3+80, true, Color.MAGENTA));
                constant.add(new BarObject(width-(width/3+70),height/7, width/3+80, true, Color.MAGENTA));
                constant.add(new BarObject(0,height/3, width/3+30, true, Color.MAGENTA));
                constant.add(new BarObject(width-(width/3+20),height/3, width/3+30, true, Color.MAGENTA));
		// control objects 
		control.add(Player.getInstance(width/3, (int)(height*0.8),"player.png",true));
                
                control.add(new BarObject(control.get(0).getX(),control.get(0).getY()-3,20, true, Color.WHITE));
                control.add(new BarObject(control.get(0).getX()+75,control.get(0).getY()-3,20, true, Color.WHITE));
                    }
               }  
               else {
                    try {
                        timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
                        long timer =  (System.currentTimeMillis() - startTime);
                        //factory Design pattern
                        if(timer%60==0)moving.add(shapesFactory.makeNewShape((int)(Math.random()*2)+1,0,height/3-10,hardness));
                        if(timer%62==0)moving.add(shapesFactory.makeNewShape((int)(Math.random()*2)+1,0,height/7-10,hardness));
                        if(timer%64==0)moving.add(shapesFactory.makeNewShape((int)(Math.random()*2)+1,width,height/3-10,hardness));
                        if(timer%66==0)moving.add(shapesFactory.makeNewShape((int)(Math.random()*2)+1,width,height/7-10,hardness));
                        
                        BarObject LeftHand = (BarObject) control.get(1);
                        BarObject RightHand = (BarObject) control.get(2);
                        int moveingSize=moving.size();
                        for(int i = 0;i<moveingSize;i++){
                            Shapes o= (Shapes) moving.get(i);
                            
                            if(o.getY()<=height/7-10)
                            {if(o.getX()<(width/3+80))o.setX((o.getX() + hardness));
                            else if(o.getY()<=height/7-10&&o.getX()>width-(width/3+80))o.setX((o.getX() - hardness));
                            else o.setY((o.getY() + hardness));
                            }
                            else
                            {if(o.getX()<(width/3+30))o.setX((o.getX() + hardness));
                            else if(o.getY()<=height/3-10&&o.getX()>width-(width/3+20))o.setX((o.getX() - hardness));
                            else o.setY((o.getY() + hardness));
                            }
                            
                            
                            
                            if(!timeout&&intersect(o, LeftHand)&&leftHandArray.isEmpty()){
                                control.add(o.copyShape(LeftHand.getX()));
                                leftHandArray.add(control.get(control.size()-1));
                                moving.remove(o);
                                moveingSize--;
                            }else if(!leftHandArray.isEmpty())
                            {if(!timeout&&intersect(o,leftHandArray.get(leftHandArray.size()-1))){
                                control.add(o.copyShape(leftHandArray.get(leftHandArray.size()-1).getX()));
                                leftHandArray.add(control.get(control.size()-1));
                                moving.remove(o);
                                moveingSize--;}}
                            
                            if(!timeout&&intersect(o, RightHand)&&rigthHandArray.isEmpty()){
                                control.add(o.copyShape(RightHand.getX()));
                                rigthHandArray.add(control.get(control.size()-1));
                                moving.remove(o);
                                moveingSize--;
                            }
                            else if(!rigthHandArray.isEmpty())
                            {if(!timeout&&intersect(o,rigthHandArray.get(rigthHandArray.size()-1))){
                                control.add(o.copyShape(rigthHandArray.get(rigthHandArray.size()-1).getX()));
                                rigthHandArray.add(control.get(control.size()-1));
                                moving.remove(o);
                                moveingSize--;
                            }}
                            if(o.getY()==getHeight()-30){
                                moving.remove(o);
                                moveingSize--;
                            }
                        }
                        if(!timeout)
                        {
                            Shapes topRock1 = null,bftop1 = null,bfbf1 = null;
                            // iterator.................
                            if(leftHandArray.size() >=3)
                            {
                                rp.setColors(leftHandArray);
                                for(Iterator ite = rp.getIterator(); ite.hasNext();)
                                {
                                    topRock1=(Shapes) ite.next();
                                    if(ite.hasNext())
                                    { bftop1=(Shapes) ite.next();}
                                    
                                    if(ite.hasNext())
                                    {  bfbf1 = (Shapes) ite.next();}
                                    
                                }
                            }
                            if(topRock1 != null)
                                if((topRock1.getColor().equals(bftop1.getColor()))&&(topRock1.getColor().equals(bfbf1.getColor())))
                                {
                                    control.remove(topRock1);
                                    leftHandArray.remove(topRock1);
                                    control.remove(bftop1);
                                    leftHandArray.remove(bftop1);
                                    control.remove(bfbf1);
                                    leftHandArray.remove(bfbf1);
                                    score++;
                                    
                                    JFXPanel j = new JFXPanel();
                                    final MediaPlayer mediaPlayer =new MediaPlayer(new Media(new File("cartoon015.wav").toURI().toString()));
                                    mediaPlayer.play();
                                }
                            Shapes topRock2 = null,bftop2 = null,bfbf2 = null;
                            //iterator .............................
                            if(rigthHandArray.size() >=3)
                            {    rp.setColors(rigthHandArray);
                            for(Iterator ite = rp.getIterator(); ite.hasNext();)
                            {
                                topRock2=(Shapes) ite.next();
                                if(ite.hasNext())
                                { bftop2=(Shapes) ite.next();}
                                
                                if(ite.hasNext())
                                {  bfbf2 = (Shapes) ite.next();}
                                
                            }
                            
                            }
                            if(topRock2 != null)
                                if((topRock2.getColor().equals(bftop2.getColor()))&&(topRock2.getColor().equals(bfbf2.getColor())))
                                {
                                    control.remove(topRock2);
                                    rigthHandArray.remove(topRock2);
                                    control.remove(bftop2);
                                    rigthHandArray.remove(bftop2);
                                    control.remove(bfbf2);
                                    rigthHandArray.remove(bfbf2);
                                    score++;
                                    JFXPanel j = new JFXPanel();
                                    final MediaPlayer mediaPlayer =new MediaPlayer(new Media(new File("cartoon015.wav").toURI().toString()));
                                    mediaPlayer.play();
                                }
                        }
                        if(timeout&&gameNotEnded)
                        {
                            highScoresSS.save(calculateScore(score,hardness));
                            gameNotEnded=false;
                        }   } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IOException ex) {
                        Logger.getLogger(PlateWorld.class.getName()).log(Level.SEVERE, null, ex);
                    }
	}
        return !timeout;
        }
	@Override public int getSpeed() 		{	return 10;	}
	@Override public int getControlSpeed() 	{	return 20;	}
	@Override public List<GameObject> getConstantObjects() 	{	return constant;	}
	@Override public List<GameObject> getMovableObjects() 	{	return moving;		}
	@Override public List<GameObject> getControlableObjects() {	return control;		}
	@Override public int getWidth() {	return width;  }
	@Override public int getHeight() { return height; }
	@Override 
	public String getStatus() {
		return "Score=" +calculateScore(score,hardness)+"   |   Player Rank="+ScoreObserver.PlayerRank(calculateScore(score,hardness)) +"   |   HighestScore="+highScoresSS.MaxScore() + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
	}

    @Override
    public int calculateScore(int score, int hardness) {
        switch(hardness)
        {
            case 1:
                return score;
            case 2:
                return (int) (score * 1.3);
            default:
                return (int) (score * 1.6);
        }
    }
}
