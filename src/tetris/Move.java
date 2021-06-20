package tetris;

import tetris.piece.Piece;

public final class Move {
	private int x, y, rotation;
	private Piece piece;
	
	public Move(int x, int y, int rotation, Piece piece) {
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.piece = piece;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRotation() {
		return rotation;
	}

	public Piece getPiece() {
		return piece;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof Move)) return false;
		Move o = (Move) other;
		return o.rotation == this.rotation && o.x == this.x && o.y == this.y && o.piece == this.piece;
	}
}
