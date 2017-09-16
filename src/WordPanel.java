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
                private int totalWords;

		
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
                   synchronized(this){
		    for (int i=0;i<noWords;i++){	    		
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    }
                }
		   
		  }
                

		
		WordPanel(WordRecord[] words, int maxY, Score score) {
			this.words=words;
			noWords = words.length;
			done=false;
			this.maxY=maxY;	
                        this.score = score; // added score
		}
		
		public void run() {
                    
                    
                    for(int i = 0; i < words.length; i++){
                        
                        Thread wrt = new Thread(new WordRecordThread(words, i, score, noWords,this));
                        wrt.start();
                        
                    }
                    
                    
                    while(!done){
                        try {
                            synchronized(this){
                            this.wait(20);
                            this.paintComponent(this.getGraphics());
                            }} catch (InterruptedException ex) {
                            Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
		}

	}
                



