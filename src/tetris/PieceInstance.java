package tetris;

import java.awt.Point;

import helpers.ANumbers;

public class PieceInstance implements Cloneable {
	private Piece piece;
	private int rotation;
	private Point position;
	
	public PieceInstance(Piece piece) {
		this.piece = piece;
		this.rotation = 0;
		position = new Point(0, 0);
	}
	
	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public int getBlocks() {
		return this.piece.getBlocks()[rotation];
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public int getGap() {
		int col = 0;
		int lowestXVal = 100;
		for(int bit = 0x8000;  bit > 0; bit = bit >> 1) {
			if((this.getBlocks() & bit) != 0) {
				lowestXVal = Math.min(lowestXVal, col);
			}
			if(++col == 4) {
				col = 0;
			}
		}
		return lowestXVal;
	}
	
	public void rotate() {
		this.rotation = ++rotation % 4;
	}
	
	public int getWidth() {
		int minIndex = 999;
		int maxIndex = -999;
		for(int bit = 0x8000;  bit > 0; bit = bit >> 4) {
			int index = 0;
			for(int x = bit; x > (bit >> 4); x = x >> 1) {
				if((this.getBlocks() & x) != 0) {
					minIndex = Math.min(minIndex, index);
					maxIndex = Math.max(maxIndex, index);
				}
				index++;
			}
		}
		return maxIndex - minIndex + 1;
	}
	
	public void unrotate() {
		this.rotation--;  if(rotation < 0) rotation = 3;
	}
	
	public String toString() {
		return this.piece.toString(this.rotation);
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		PieceInstance p = new PieceInstance(Piece.I);
		p.rotate();
		System.out.println(p.piece.toString(p.rotation));
		System.out.println(p.getWidth());
		System.out.println(p.getGap());
	
	}
	
}
