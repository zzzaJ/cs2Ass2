import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * WordRecordThread Class which handles the dropping of words.
 * The class implements runnable and thus can have threads of WordRecordThread type created and started.
 * The threads manage the dropping of words in the game.
 * 
 * @author Dino Bossi
 */
public class WordRecordThread implements Runnable {
    
    private WordRecord[] words;
    private int index;
    private Score score;
    private int noWords;
    private WordPanel w;
    private Boolean counted;
    /**
     * WordRecordThread constructor.
     * Constructor for the wordrecordthread objects.
     * 
     * @param wrds WordRecord array to be used
     * @param indx Index of the specific wordRecord the thread handles
     * @param scr Score object, passed from the wordPanel
     * @param noWords Number of words to be on the screen at one time
     * @param ww Wordpanel object to allow the creation of a JOptionPane to notify the player that the game is finished
     */
    public WordRecordThread(WordRecord[] wrds, int indx, Score scr, int noWords, WordPanel ww){
        
        words = wrds;
        index = indx;
        score = scr;
        this.noWords = noWords;
        counted = false;
        w = ww;
        
    }

    /**
     * Synchronized method to return the score object
     * @return score object
     */
    public synchronized Score getScore(){
        return score;
    }
    
    
    @Override
    /**
     * Overridden Run method.
     * This method is run when the thread is started and handles the dropping of a specific word on the WordPanel.
     * Thread will run until game is done, then the first thread to register the game over condition will display the relevant information
     * to the player regarding their score.
     */
    public void run() {
        
        while(!WordApp.done){ //checking if the game is done
            
            if(!counted){ //counting the intial drop of the word (to keep track of total words dropped)
                counted = true;
                WordApp.stopDropInt.getAndDecrement();
            }
            
            if(WordApp.totalWords == WordApp.count.get()){ // counting the number of words dropped and determining if the game is over
                WordApp.done = true;
                break;
            }
            
            if(words[index].getY()==480){ //need to check if word y val == 480 to check if on red block.
                
                                
                words[index].resetWord();//resets word
                WordApp.stopDropInt.getAndDecrement();//decrements total words dropped counter
                getScore().missedWord();//increases the missed score
                WordApp.tbu.set(true);// notify score thread to update score
                WordApp.count.getAndIncrement();// increase count, since word is no longer in play
                
            }
            
            if(words[index].getMatched()){ //checking if the word was matched
                
                WordApp.stopDropInt.getAndDecrement();//decrements total words dropped counter
                getScore().caughtWord(words[index].getWord().length());//increases the caught score
                WordApp.tbu.set(true);// notify score thread to update score
                WordApp.count.getAndIncrement();// increase count, since word is no longer in play
                words[index].resetWord();//reset the word 
                
            }

            if(0 < WordApp.stopDropInt.get() || words[index].dropped()){ //if there are still words to be dropped or if the word has already been dropped 
                
                try {
                
                words[index].drop(5); //drop the word an arbitrary value of 5 places
                Thread.sleep(words[index].getSpeed()); //sleep the thread for the specified value

                } catch (InterruptedException ex) { //catch an error if thread cannot be slept

                    Logger.getLogger(WordRecordThread.class.getName()).log(Level.SEVERE, null, ex);

                }
                
            }
        }
        
        if(!WordApp.stopped.get()){ // if game has finished
                WordApp.stopped.set(true); //cahnge value to only allow one thread to display the score
                WordApp.startClicked.set(false); //allow start to be clicked again
                JOptionPane.showMessageDialog(w, "Game over! Final score was caught: " + score.getCaught() + "  missed: " + score.getMissed() + " total: " + score.getTotal());
                //display end of game message with score
        }
    }
        
}
