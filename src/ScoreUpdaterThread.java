import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 * ScoreUpdaterThread class.
 * Manages the updating of the score during the game, when notified to do so.
 * Implements runnable thus can be started (as a thread).
 * 
 * @author Dino Bossi
 */
public class ScoreUpdaterThread implements Runnable{
    
    JLabel caught;
    JLabel missed;
    JLabel scr;
    Score score;
/**
 * ScoreUpdaterThread constructor.
 * 
 * Constructor takes label parameters that it will be updating, as well as the score.
 * 
 * @param caught Caught JLabel showing caught score
 * @param missed Missed JLabel showing missed score
 * @param scr Scr JLabel showing total score
 * @param score The game's score object 
 */
    public ScoreUpdaterThread(JLabel caught, JLabel missed, JLabel scr, Score score){
        
        this.caught = caught;
        this.missed = missed;
        this.scr = scr;
        this.score = score;
        
    }
    
    
    @Override
    /**
     * Run Method.
     * 
     * Method that is run when the thread is started.
     * Manages the updating of the score on the screen when required
     */
    public void run() {
        
        while(!WordApp.done){ // loop until the game is done
            
            if(WordApp.tbu.get()){ //if the score is to be updated
                synchronized(this){ //synchronize 
                    caught.setText("Caught: " + score.getCaught() + "    "); //update caught score
                    missed.setText("Missed:" + score.getMissed()+ "    "); //update missed score
                    scr.setText("Score:" + score.getScore()+ "    "); //update total score
                }
        
            }
        }
        
                      
        
    }
    
    
    
}
