package tetris;

import java.util.ArrayList;

import ai.Brain;
import ai.Brain.Move;
import helpers.AUtils;

public class Board {
	public static final int ROW_START = 2;
	public static final int ROWS = 22;
	public static final int COLS = 10;
	public int[][] memory;
	public int[][] backupMemory;
	public Board() {
		this.reset();
	}
	
	public void reset() {
		this.memory = new int[Board.ROWS][Board.COLS];
		this.backupMemory = new int[Board.ROWS][Board.COLS];
	}
	
	public String toString() {
		String res = "";
		for(int[] row : memory) {
			for(int item : row) {
				res += item;
			}
			res += "\n";
		}
		return res;
	}
	
	public boolean place(PieceInstance p, int x, int y) {
		if(this.occupied(p, x, y)) return false;
		int row = 0;
		int col = 0;
		for(int bit = 0x8000;  bit > 0; bit = bit >> 1) {
			if((p.getBlocks() & bit) != 0) {
				this.memory[y + row][x + col] = p.getPiece().getValue();
			}
			if(++col == 4) {
				col = 0;
				row++;
			}
		}
		return true;
		
	}
	
	public int[] heights() {
		 return heights(Board.ROWS-1);
	}
	
	public int[] heights(int maxHeight) {
	    int[] heights = new int[Board.COLS];
	    for(int x = 0; x < Board.COLS; x++) {
	      heights[x] = getHeightOfColumn(x, maxHeight);
	    }
		return heights;
	}
	
	private int getHeightOfColumn(int x, int maxHeight) {
	    int height = Board.ROWS;
	    
	    for(int i = 0; i < Board.ROWS; i++) {
	    	if(this.memory[i][x] == 0) height--;
	    	else break;
	    }
	    
	    return height;   
	}
	
	public int clearLines() {
		int lines = 0;
		for(int i = 0; i < memory.length; i++) {
			for(int j = 0; j < memory[0].length; j++) {
				if(memory[i][j] == 0) break; // don't need to clear this line;
				else if(j == memory[0].length - 1) { // reached end 
					clearLine(i);
					slideBlocksDown(i);
					lines++;
				}
			}
		}
		
		return lines;
	}
	
	private void clearLine(int row) {
		for(int i = 0; i < memory[0].length; i++) {
			memory[row][i] = 0;
		}
	}
	
	private void slideBlocksDown(int row) { // row to shift to
		for(int i = row-1; i >= 0; i--) {
			for(int j = 0; j < memory[0].length; j++) {
				memory[i+1][j] = memory[i][j];
			}
		}
	}
	
	private int getBlock(int x, int y) {
		return this.memory[y][x];
	}	
	
	
	
	public boolean occupied(PieceInstance p, int x, int y) {
		int row = 0;
		int col = 0;
		var result = false;
		for(int bit = 0x8000;  bit > 0; bit = bit >> 1) {
			if((p.getBlocks() & bit) != 0) {
				int ypos = y+row;
				int xpos = x+col;
				if(xpos < 0 || xpos >= Board.COLS || ypos < 0 || ypos >= Board.ROWS || getBlock(xpos, ypos) != 0) {
					result = true;
				}
			}
			if(++col == 4) {
				col = 0;
				row++;
			}
		}
		return result;
	}
	
	public Brain.Move[] moves(PieceInstance p) {
		ArrayList<Brain.Move> moves = new ArrayList<>();
		int xBound = Board.COLS - p.getWidth()+1;
		int shift = p.getGap();
		for(int spin = 0; spin < 4; spin++) {
			xBound = Board.COLS - p.getWidth()+1;
			shift = p.getGap();
			for(int x = 0-shift; x < xBound-shift; x++) {
				int y = this.dropHeight(p, x);
				moves.add(new Brain.Move((PieceInstance) p.clone(),x,y));
				
			}
			p.rotate();
	}
		
		Brain.Move[] m = new Brain.Move[moves.size()];
		for(int i = 0; i < m.length; i++) {
			m[i] = moves.get(i);
		}
		return m;
	}
	
	public void commit() {
		this.backupMemory = AUtils.deepCopy(memory);
	}
	
	public void undo() {
		this.memory = AUtils.deepCopy(backupMemory);
	}
	
	public void setAllPieces(Piece p) {
		for(int i = 0; i < this.memory.length; i++) {
			for(int j = 0; j < this.memory[0].length; j++) {
				if(this.memory[i][j] != 0) {
					this.memory[i][j] = p.getValue();
				}
			}
		}
	}

	public int dropHeight(PieceInstance p, int x) {
		int y = 0;
		boolean res = true;
		while(res == true) {
			res = this.place(p, x, ++y);
			this.undo();
		}
		return --y;
	}

	public boolean place(Move m) {
		return this.place(m.piece, m.x, m.y);		
	}
}
