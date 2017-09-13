
import java.util.logging.Level;
import java.util.logging.Logger;


public class WordRecordThread implements Runnable {
    /*
    TO DO
    
    sleep thread after moving down
    check for matched words and update array
    ensure arrays are the same when checking if word is correct
    implement pause button
    fix run method
    maybe make array of threads for words
    they litterally give us the value to sleep the thread...
    
    you only need as many threads as there are words on the screen at a time..
    
    
    */
    
    
    private WordRecord[] words;
    private int index;
    private Score score;
    private int noWords;
    private int onScreen;
    private WordPanel w;
    
    public WordRecordThread(WordRecord[] wrds, int indx, Score scr, int noWords, WordPanel ww){
        
        words = wrds;
        index = indx;
        score = scr;
        this.noWords = noWords;
        w = ww;
        
    }

    public synchronized int getIndex(){
        return index;
    } 
    
    public synchronized Score getScore(){
        return score;
    }
    
    
    @Override
    public void run() {
        
        while(!WordPanel.done){ //checking if the game is done
            
            //need to check if word y val == 480 to check if on red block.
            
            try {
            words[index].drop(6); //arbitrary value of 5
            
            Thread.sleep(words[index].getSpeed());
            w.paintComponent(w.getGraphics());
            } catch (InterruptedException ex) {
                
                Logger.getLogger(WordRecordThread.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            
            
            
            
            
            
        }
        
        
//        while(!WordPanel.done && words[index].getY()!=777){ // if the game isnt done
//            if (getOnScreen() < getNoWords()){ // if the words on screen is less than required words on screen
//                
//                if(!words[index].dropped()){ //if the word isnt dropped 
//                    
//                    words[index].drop(words[index].getSpeed());
//                    
//                }
//                if(words[index].getY()==480){
//                    decOnScreen();
//                }
//          
//            
//            }
//           
//        }
    }
    
    public synchronized void incOnScreen(){
        onScreen++;
    }
    
    public synchronized void decOnScreen(){
        onScreen--;
    }
    
    public synchronized int getOnScreen(){
        return onScreen;
    }
    
    public synchronized void incNoWords(){
        noWords++;
    }
    
    public synchronized int getNoWords(){
        return noWords;
    }
    
}
