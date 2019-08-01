/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.oop.game.sample.world; 
/**
 *
 * @author Mahmo
 */
//state design pattern && snapshot Design Pattern ..................
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class HighScoresSS {
    
    /**
     *
     */
   private String[] scores;
    private int hardness;
    
    public  HighScoresSS(int hardness) throws Exception{
        this.hardness = hardness;
       this.scores= read(hardness).split(",");
    }
    
    public void setHardness(int hardness) throws Exception
    {
        this.hardness = hardness;
        this.scores= read(hardness).split(",");
    }
    
    public int MaxScore()
    {
        int max=Integer.parseInt(scores[0]);
        for(int i=0;i<scores.length;i++)
        {
            if(Integer.parseInt(scores[i])>max)
                max = Integer.parseInt(scores[i]);
        }
        return max;
    }
    public void save(int newScore) throws FileNotFoundException, IOException {
File fout;
switch(hardness)
{
    case 1:
        fout= new File("easyHighScores.txt");
        break;
      case 2:
        fout= new File("mediumHighScores.txt");
        break; 
      default:
        fout= new File("hardHighScores.txt");
        break;
}
PrintWriter w = new PrintWriter(fout);
w.print(String.valueOf(newScore));
for(int i=0;i<scores.length;i++)
{
    w.print(","+scores[i]);
}
w.close();
}
    public String read(int hardness) throws FileNotFoundException, IOException, ClassNotFoundException {
        String input;
File fin;
switch(hardness)
{
    case 1:
        fin= new File("easyHighScores.txt");
        break;
      case 2:
        fin= new File("mediumHighScores.txt");
        break; 
      default:
        fin= new File("hardHighScores.txt");
        break;
}
Scanner s = new Scanner(fin);
input=s.next();
return input;
}
    
    
    
    
}