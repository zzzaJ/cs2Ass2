
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class ScoreUpdaterThread implements Runnable{
    
    JLabel caught;
    JLabel missed;
    JLabel scr;
    Score score;

    public ScoreUpdaterThread(JLabel caught, JLabel missed, JLabel scr, Score score){
        
        this.caught = caught;
        this.missed = missed;
        this.scr = scr;
        this.score = score;
        
    }
    
    
    @Override
    public void run() {
        
        while(!WordApp.done){
            
            if(WordApp.tbu){
                synchronized(this){
                    caught.setText("Caught: " + score.getCaught() + "    ");
                    missed.setText("Missed:" + score.getMissed()+ "    ");
                    scr.setText("Score:" + score.getScore()+ "    ");
                }
        
            }
        }
        
                      
        
    }
    
    
    
}
