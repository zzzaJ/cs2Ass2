import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
                private Score score;

		
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added 
                   synchronized(this){
		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());  //y-offset for skeleton so that you can see the words	
		    }
                }
		   
		  }
                

		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;	
                        score = new Score(); // added score
		}
		
		public void run() {
                    
                    
                    for(int i = 0; i < words.length; i++){
                        
                        Thread wrt = new Thread(new WordRecordThread(words, i, score, noWords, this));
                        wrt.start();
                        
                    }
                    
//                    while(!done){
//                        try {
//                            synchronized(this){
//                            this.wait(100);
//                            this.paintComponent(this.getGraphics());
//                            }} catch (InterruptedException ex) {
//                            Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        
//                    }
                    
                    //call method of another thread class, which will work with each word (drop it, score it, etc)
                    
                    // main -> WordPanel thread -> multiple word managing threads?
                    // or main starts wordpanel threads that are for each word
                    
                    // potential solution : One word panel thread -> multiple wordPanelManager threads each started with a specfic wordRecord object,
                    // which then manages a specific word, closing when word is scored, reaches bottom : starts when wordsOnScreen < actual words on screen (words with y < minY)
                    // model view satisfied... word record object gets passed along with an integer, determining which word will be accessable by the thread, 
                    // then only WordRecord object need be safe.
                    
                    // could possibly create array of wordrecord controlling threads, starting them as the words are completed... 

                    //add in code to animate this
		}

	}
                



