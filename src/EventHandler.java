import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EventHandler implements KeyListener{
	static long lastPress = 0;
	static long keyDelay = 500;
	Board gameBoard;
	Renderer frame;
	
	public EventHandler(Renderer renderer, Board board) {
		gameBoard = board;
		this.frame = renderer;
		frame.addKeyListener(this);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();		
		
		if(keyCode == KeyEvent.VK_DOWN)
			gameBoard.execute(Board.Commands.MOVE_DOWN);
		if(keyCode == KeyEvent.VK_UP)
			gameBoard.execute(Board.Commands.MOVE_UP);
		if(keyCode == KeyEvent.VK_LEFT)
			gameBoard.execute(Board.Commands.MOVE_LEFT);
		if(keyCode == KeyEvent.VK_RIGHT)
			gameBoard.execute(Board.Commands.MOVE_RIGHT);
	}

	public void keyTyped(KeyEvent e) {
	}
}
