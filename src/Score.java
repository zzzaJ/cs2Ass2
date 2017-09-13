public class Score {
	private int missedWords;
	private int caughtWords;
	private int gameScore;
	
	Score() {
		missedWords=0;
		caughtWords=0;
		gameScore=0;
	}
		
	// all getters and setters must be synchronized
	
	public synchronized int getMissed() {
		return missedWords;
	}

	public synchronized int getCaught() {
		return caughtWords;
	}
	
	public synchronized int getTotal() {
		return (getMissed()+getCaught());
	}

	public synchronized int getScore() {
		return gameScore;
	}
	
	public  void missedWord() {
		missedWords++;
	}

	public void caughtWord(int length) {
		caughtWords++;
		gameScore+=length;
	}

	public void resetScore() {
		caughtWords=0;
		missedWords=0;
		gameScore=0;
	}
}
