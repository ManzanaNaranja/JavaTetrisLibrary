package ai;

import tetris.Board;
import tetris.PieceInstance;

public interface Brain {
	public static class Move {
		public int x, y;
		public PieceInstance piece;
		public double score;
		
		public Move(PieceInstance piece, int x, int y) {
			this(piece,x,y,0);
		}

		public Move(PieceInstance piece, int x, int y, double score) {
			this.piece = piece;
			this.x = x;
			this.y = y;		
			this.score = score;
		}
	}
	
	public Brain.Move bestMove(Board board, PieceInstance piece); 
	
}
