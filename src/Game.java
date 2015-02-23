import java.util.concurrent.TimeUnit;


public class Game {

	public static boolean paused;
	public static Board gameBoard;
	public static Renderer gameFrame;
	
	public static int scale = 4;

	public static void main(String[] args) {
		run();
	}

	private static void run() {
		gameBoard = new Board();
		gameFrame = new Renderer(gameBoard, scale);
		boolean done = false;
		long startTicks = 0;
		
		gameBoard.execute(Board.Commands.INIT);
		
		while(!done) {
			startTicks = System.currentTimeMillis();
			
			gameFrame.render();
			
			try {
				TimeUnit.MILLISECONDS.sleep(1000/60 - (System.currentTimeMillis() - startTicks));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void reset() {
		gameBoard.execute(Board.Commands.INIT);
	}
}