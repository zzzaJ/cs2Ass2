
import static WordApp.score;
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
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
