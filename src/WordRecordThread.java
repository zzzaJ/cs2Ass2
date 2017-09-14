import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class WordRecordThread implements Runnable {
    /*
    TO DO
    
    update score when relevent event occurs, rather than when enter is hit
    number of words to fall is not working. currently count is incorrect implementation
    
    you only need as many threads as there are words on the screen at a time..
    
    
    */
    
    
    private WordRecord[] words;
    private int index;
    private Score score;
    private int noWords;
    private WordPanel w;
    private Boolean counted;
    
    public WordRecordThread(WordRecord[] wrds, int indx, Score scr, int noWords, WordPanel ww){
        
        words = wrds;
        index = indx;
        score = scr;
        this.noWords = noWords;
        w = ww;
        counted = false;
        
    }

    public synchronized int getIndex(){
        return index;
    } 
    
    public synchronized Score getScore(){
        return score;
    }
    
    
    @Override
    public void run() {
        
        while(!WordApp.done){ //checking if the game is done
            
            if(!counted){
                counted = true;
                WordApp.stopDropInt--;
                System.out.println(WordApp.stopDropInt);
            }
            
            if(WordApp.totalWords == WordApp.count){ // not working at the moment
                WordApp.done = true;
                break;
            }
            
            if(words[index].getY()==480){ //need to check if word y val == 480 to check if on red block.
                
                                
                words[index].resetWord();
                WordApp.stopDropInt--;
                System.out.println(WordApp.stopDropInt);
                getScore().missedWord();
                WordApp.tbu = true;
                WordApp.count++;
                
            }
            
            if(words[index].getMatched()){ //checking if the word was matched
                
                WordApp.stopDropInt--;
                System.out.println(WordApp.stopDropInt);
                getScore().caughtWord(words[index].getWord().length());
                WordApp.tbu = true;
                WordApp.count++;
                words[index].resetWord();
                
            }

            if(0 < WordApp.stopDropInt || words[index].dropped()){
                
                try {
                
                words[index].drop(5); //arbitrary value of 5

                
                Thread.sleep(words[index].getSpeed());
                //w.paintComponent(w.getGraphics());

                } catch (InterruptedException ex) {

                    Logger.getLogger(WordRecordThread.class.getName()).log(Level.SEVERE, null, ex);

                }
                
            }
            

            
        }
        
        if(!WordApp.stopped){
                WordApp.stopped = true;
                WordApp.startClicked = false;
                JOptionPane.showMessageDialog(w, "Game over! Final score was caught: " + score.getCaught() + "  missed: " + score.getMissed() + " total: " + score.getTotal());
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
        
    public synchronized void incNoWords(){
        noWords++;
    }
    
    public synchronized int getNoWords(){
        return noWords;
    }
    
}
