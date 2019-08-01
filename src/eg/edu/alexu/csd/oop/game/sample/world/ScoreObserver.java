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
// Observer Design Pattern.................
public class ScoreObserver{
       
    public static String PlayerRank(int score){
        if (score<=12)
         return "Normal Player";
        if(score<=24)
         return "Good Player";    
        return "Excellant Player";
}
}
