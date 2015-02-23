import java.util.Random;

public class Board {
	public static final int BOARD_SIZE = 4;
	public enum Commands { NONE, INIT, MOVE_UP, MOVE_DOWN, MOVE_RIGHT, MOVE_LEFT, QUIT };
	
	private GameTile[][] tileArray = new GameTile[BOARD_SIZE][BOARD_SIZE];
	private static boolean randomFours = false;
	private int score;
	
	public Board() {
		score = 0;
		
		// set each space on the board to a zero tile
		for(int i = 0; i < BOARD_SIZE; i++)
			for(int j = 0; j < BOARD_SIZE; j++)
				tileArray[i][j] = new GameTile();
	}
	
	public GameTile getTile(int row, int column) {
		return tileArray[row][column];
	}
	
	public void setRandomFours(boolean r4) {
		randomFours = r4;
	}
	public boolean getRandomFours() {
		return randomFours;
	}
	
	public boolean checkLose() {
		// check for empty spaces
		for(int i = 0; i < BOARD_SIZE; i++)
			for(int j = 0; j < BOARD_SIZE; j++)
				if(tileArray[i][j].getValue() == 0)
					return false;
		
		// check horizontal
		for(int i = 0; i < BOARD_SIZE; i++)
			for(int j = 0; j < BOARD_SIZE - 1; j++)
				if(tileArray[i][j].getValue() == tileArray[i][j+1].getValue())
					return false;
		
		// check vertical
		for(int j = 0; j < BOARD_SIZE; j++)
			for(int i = 0; i < BOARD_SIZE - 1; i++)
				if(tileArray[i][j].getValue() == tileArray[i+1][j].getValue())
					return false;
		
		return true;
	}
	
	public int getScore() {
		return score;
	}
	
	public void execute(Commands command) {
		if(!Game.paused) {
			int moveScore = -1;
			
			switch(command) {
				case INIT:
					initialize();
					// fakeBoard();
					score = 0;
					break;
				case MOVE_UP:	moveScore = moveUp(); break;
				case MOVE_DOWN: moveScore = moveDown(); break;
				case MOVE_LEFT: moveScore = moveLeft(); break;
				case MOVE_RIGHT: moveScore = moveRight(); break;
				default:
			}
			
			if(moveScore >= 0) {
				Random rand = new Random();
				if(randomFours && rand.nextInt(2) == 1)
					newPiece(4);
				else
					newPiece(2);
				
				score += moveScore;
			}
			else
				moveScore = 0;
		}
	}
	
	public void initialize() {
		for(int i = 0; i < BOARD_SIZE; i++)
			for(int j = 0; j< BOARD_SIZE; j++)
				tileArray[i][j].setZero();
		
		newPiece(2);
		newPiece(2);
		newPiece(4);
	}
	
	private void newPiece(int number) {
		int[] empties = new int[BOARD_SIZE*BOARD_SIZE];
		int counter = 0;
		
		for(int i = 0; i < BOARD_SIZE; i++)
			for(int j = 0; j< BOARD_SIZE; j++)
				if(tileArray[i][j].getValue() == 0) {
					empties[counter] = i * BOARD_SIZE + j;
					counter++;
				}

		if(counter != 0) {
			Random rand = new Random();
			int n = empties[rand.nextInt(counter)];
			tileArray[n / BOARD_SIZE][n % BOARD_SIZE] = new GameTile();// set game tile size here
			tileArray[n / BOARD_SIZE][n % BOARD_SIZE].setValue(number);
		}
	}
	
	public void updateTileSizes() {
		for(int i = 0; i < BOARD_SIZE; i++)
			for(int j = 0; j< BOARD_SIZE; j++)
				tileArray[i][j].updateSize();
	}
	
	private int moveUp() {
		int score = -1;
		for (int j = 0; j < BOARD_SIZE; j++) {
			// move tiles up
			for (int i = 0; i < BOARD_SIZE - 1; i++) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = i + 1; c < BOARD_SIZE; c++) {
						if (tileArray[c][j].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[c][j].getValue());
							tileArray[c][j].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}

			// combine equal and adjacent tiles
			for (int i = 0; i < BOARD_SIZE - 1; i++) {
				if (tileArray[i][j].getValue() == tileArray[i + 1][j].getValue() && tileArray[i][j].getValue() != 0) {
					tileArray[i][j].doubleValue();
					tileArray[i + 1][j].setZero();
					if (score < 0)
						score = tileArray[i][j].getValue();
					else
						score += tileArray[i][j].getValue();
				}
			}

			// move tiles up
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = i + 1; c < BOARD_SIZE; c++) {
						if (tileArray[c][j].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[c][j].getValue());
							tileArray[c][j].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}
		}

		return score;
	}
	
	private int moveDown() {
		int score = -1;
		for (int j = 0; j < BOARD_SIZE; j++) {
			// move tiles down
			for (int i = BOARD_SIZE - 1; i > 0; i--) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = i - 1; c >= 0; c--) {
						if (tileArray[c][j].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[c][j].getValue());
							tileArray[c][j].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}

			// combine adjacent tiles
			for (int i = BOARD_SIZE - 1; i > 0; i--) {
				if (tileArray[i][j].getValue() == tileArray[i - 1][j].getValue() && tileArray[i][j].getValue() != 0) {
					tileArray[i][j].doubleValue();
					tileArray[i - 1][j].setZero();
					if (score < 0)
						score = tileArray[i][j].getValue();
					else
						score += tileArray[i][j].getValue();
				}
			}

			// move tiles down
			for (int i = BOARD_SIZE - 1; i > 0; i--) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = i - 1; c >= 0; c--) {
						if (tileArray[c][j].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[c][j].getValue());
							tileArray[c][j].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}
		}

		return score;
	}
	
	private int moveLeft() {
		int score = -1;
		for (int i = 0; i < BOARD_SIZE; i++) {
			// move tiles up
			for (int j = 0; j < BOARD_SIZE - 1; j++) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = j + 1; c < BOARD_SIZE; c++) {
						if (tileArray[i][c].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[i][c].getValue());
							tileArray[i][c].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}

			// combine equal and adjacent tiles
			for (int j = 0; j < BOARD_SIZE - 1; j++) {
				if (tileArray[i][j].getValue() == tileArray[i][j + 1].getValue() && tileArray[i][j].getValue() != 0) {
					tileArray[i][j].doubleValue();
					tileArray[i][j + 1].setZero();
					if (score < 0)
						score = tileArray[i][j].getValue();
					else
						score += tileArray[i][j].getValue();
				}
			}

			// move tiles up
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = j + 1; c < BOARD_SIZE; c++) {
						if (tileArray[i][c].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[i][c].getValue());
							tileArray[i][c].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}
		}

		return score;
	}
	
	private int moveRight() {
		int score = -1;
		for (int i = 0; i < BOARD_SIZE; i++) {
			// move tiles down
			for (int j = BOARD_SIZE - 1; j > 0; j--) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = j - 1; c >= 0; c--) {
						if (tileArray[i][c].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[i][c].getValue());
							tileArray[i][c].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}

			// combine adjacent tiles
			for (int j = BOARD_SIZE - 1; j > 0; j--) {
				if (tileArray[i][j].getValue() == tileArray[i][j - 1].getValue() && tileArray[i][j].getValue() != 0) {
					tileArray[i][j].doubleValue();
					tileArray[i][j - 1].setZero();
					if (score < 0)
						score = tileArray[i][j].getValue();
					else
						score += tileArray[i][j].getValue();
				}
			}

			// move tiles down
			for (int j = BOARD_SIZE - 1; j > 0; j--) {
				if (tileArray[i][j].getValue() == 0) {
					for (int c = j - 1; c >= 0; c--) {
						if (tileArray[i][c].getValue() != 0) {
							tileArray[i][j].setValue(tileArray[i][c].getValue());
							tileArray[i][c].setZero();
							if (score < 0)
								score = 0;
							break;
						}
					}
				}
			}
		}

		return score;
	}
	
	public void show() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++)
				System.out.print("  " + tileArray[i][j].getValue());
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}
