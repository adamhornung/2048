import java.util.HashMap;
import java.util.Map;


public class GameTile {
	private int number;
	private enum Tiles { ZERO(0), TWO(2), FOUR(4), EIGHT(8), SIXTEEN(16), THIRTYTWO(32), SIXTYFOUR(64), ONETWENTYEIGHT(128),
		TWOFIFTYSIX(256), FIVETWELVE(512), TENTWENTYFOUR(1024), TWENTYFORTYEIGHT(2048), UNKNOWN(-1);
		
		private int value;
		
		Tiles(int value) {
			this.value = value;
		}
		
		private static final Map<Integer, Tiles> map = new HashMap<Integer, Tiles>();
		static {
			for(Tiles tile: Tiles.values()) {
				map.put(tile.value, tile);
			}
		}
		
		public static Tiles valueOf(int value) {
			return map.get(value);
		}
	}
	private Tiles tile;
	private int size;
	private static final int START_SIZE = 10;
	private static final int SIZE_INCREASE = 10;
	
	public GameTile() {
		number = 0;
		tile = Tiles.ZERO;
		size = START_SIZE;
	}
	
	GameTile(int size) {
		number = 0;
		tile = Tiles.ZERO;
		this.size = size;
	}
	
	public void updateSize() {
		if(size < 100)
			size += SIZE_INCREASE;
	}
	
	public void setZero() {
		number = 0;
		tile = Tiles.ZERO;
	}
	
	public int getValue() {
		return number;
	}
	
	public int getTile() {
		return tile.ordinal();
	}
	
	public int getSize() {
		return size;
	}
	
	public void setValue(int number) {
		this.number = number;
		tile = Tiles.valueOf(this.number);
	}
	
	public void doubleValue() {
		if(number == 0)
			number = 1;
		number *= 2;
		tile = Tiles.values()[tile.ordinal()+1];
		size = START_SIZE;
	}
	
	
}