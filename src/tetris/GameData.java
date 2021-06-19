package tetris;

import helpers.AUtils;
import tetris.piece.Piece;
import tetris.piece.PieceInstance;

public class GameData {
	private final int[][] memory;
	private final Piece current, next;
	
	public GameData(int[][] memory, Piece currentPiece, Piece nextPiece) {
		this.memory = AUtils.deepCopy(memory);
		this.current = currentPiece;
		this.next = nextPiece;
	}
	
	public int[][] getBoard() {
		return this.memory;
	}
	
	public PieceInstance getCurrentPiece() {
		return new PieceInstance(current);
	}
	
	public PieceInstance getNextPiece() {
		return new PieceInstance(next);
	}
}
