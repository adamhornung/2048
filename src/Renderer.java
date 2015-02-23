import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Renderer extends Canvas {
	private static final long serialVersionUID = 1L;
	private Board gameBoard;
	private final int TILE_SIZE = 129;
	private final int TILE_SPACER = 20;
	private static int scale;

	private JFrame frame;
	public final static int SCREEN_WIDTH = 800;
	public final static int SCREEN_HEIGHT = 615;// 606
	public final Dimension gameSize = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT+53);
	public final String TITLE = "2048";
	
	private Image backGroundImage;
	private Image tileSheetImage;
	private Image whiteBackGroundImage;

	public Renderer(Board board, int s) {
		gameBoard = board;
		scale = s;
		
		loadImages();
		
		frame = new JFrame();

		setPreferredSize(gameSize);
		

		MenuBar menuBar = new MenuBar();
		frame.setJMenuBar(menuBar.createMenuBar());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		
		
		
		new EventHandler(this, board);
		this.requestFocus();
		
		frame.setSize(gameSize);
	}
	
	public void setScale(int s) {
		scale = s;
		frame.setSize(new Dimension(SCREEN_WIDTH * scale / 4, (SCREEN_HEIGHT * scale / 4) + 53));
	}
	
	public int getScale() {
		return scale;
	}
	
	public void render() {		
		gameBoard.updateTileSizes();
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0, getWidth(), getHeight());

		// Render Board
		g.drawImage(backGroundImage,0,0,616 * scale / 4,616 * scale / 4,this);
		
		// Render each tile
		for(int i = 0; i < Board.BOARD_SIZE; i++)
			for(int j = 0; j < Board.BOARD_SIZE; j++)
				renderTile(gameBoard.getTile(i, j), i, j, g);
				
		
		// Render the score
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(gameBoard.getScore()), 650 * scale / 4, 100 * scale / 4);
		
		// Render white overlay when player loses
		if(gameBoard.checkLose()) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g.drawImage(whiteBackGroundImage, 0, 0, this);
		}
		
		g.dispose();
		bs.show();
	}
	
	public void destroy() {
		frame.dispose();
	}
	
	private void renderTile(GameTile tile, int row, int column, Graphics g) {
		int tileNumber = tile.getTile();
		
		int tx1 = TILE_SPACER + (TILE_SPACER + TILE_SIZE) * column + ((100 - tile.getSize()) * TILE_SIZE / 200);
		int tx2 = tx1+TILE_SIZE - ((100 - tile.getSize()) * TILE_SIZE / 200)*2;
		int ty1 = TILE_SPACER + (TILE_SPACER + TILE_SIZE) * row + ((100 - tile.getSize()) * TILE_SIZE / 200);
		int ty2 = ty1+TILE_SIZE - ((100 - tile.getSize()) * TILE_SIZE / 200)*2;
		int ix1 = 0;
		int ix2 = TILE_SIZE;
		int iy1 = TILE_SIZE*tileNumber;
		int iy2 = TILE_SIZE*(tileNumber+1);
		
		tx1 = (tx1 * scale) / 4;
		tx2 = (tx2 * scale) / 4;
		ty1 = (ty1 * scale) / 4;
		ty2 = (ty2 * scale) / 4;
		
		g.drawImage(tileSheetImage, tx1, ty1, tx2, ty2,	ix1, iy1, ix2, iy2, this);
	}
	
	private void loadImages() {
		//	backGroundImage = Toolkit.getDefaultToolkit().getImage("images/board.png");
		//	tileSheetImage = Toolkit.getDefaultToolkit().getImage("images/tiles.png");
		//	whiteBackGroundImage = Toolkit.getDefaultToolkit().getImage("images/whiteboard.png");
		InputStream input1 = this.getClass().getClassLoader().getResourceAsStream("board.png");
		InputStream input2 = getClass().getResourceAsStream("tiles.png");
		InputStream input3 = getClass().getResourceAsStream("whiteboard.png");
		try {
			backGroundImage = ImageIO.read(input1);
			tileSheetImage = ImageIO.read(input2);
			whiteBackGroundImage = ImageIO.read(input3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}