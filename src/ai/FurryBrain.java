package ai;

import ai.Brain.Move;
import tetris.Board;
import tetris.PieceInstance;

public class FurryBrain implements Brain{

	@Override
	public Move bestMove(Board board, PieceInstance piece) {
		double bestScore = 1e20;
		int bestX = 0;
		int bestY = 0;
		PieceInstance bestPiece = null;

		board.commit();
		for(Brain.Move m : board.moves(piece)) {
			board.place(piece, m.x,m.y);
			board.clearLines();
			double score = evaluateBoard(board);
			if(score < bestScore) {
				bestScore = score;
				bestX = m.x;
				bestY = m.y;
				bestPiece = m.piece;
			}
			board.undo();
		}
		System.out.println(bestScore);
		return new Brain.Move(bestPiece, bestX, bestY, bestScore);
		
	}

	private double evaluateBoard(Board board) {
		 int holes = getHoles(board);
		    int maxHeight = getMaxHeight(board);
		    double avgHeight = getAverageHeight(board);
		    return maxHeight * 80 + holes;
	}
	
	private int getMaxHeight(Board board) {
	    int maxHeight = -1;
	    int[] heights = board.heights();
	    for(int i = 0; i < heights.length; i++) {
	        if(heights[i] > maxHeight) {
	          maxHeight = heights[i];
	        }
	    }
	    return maxHeight;
	}
	
	 private double getAverageHeight(Board board) {
		    double sum = 0;
		    int[] heights = board.heights();
		    for(int i = 0; i < heights.length; i++) {
		      sum += heights[i];
		    } 
		    double avg = sum / heights.length;
		    return avg;
		    
	 }
	 
	 private int getHoles(Board board) {
		    int holes = 0;
		    int[] heights = board.heights();
		    
		    for(int x = 0; x < Board.COLS; x++) {
		      for(int y = Board.ROWS-1; y >= Board.ROWS - heights[x]; y--) {
		        if(board.memory[y][x] == 0) {
		          holes++;
		        }
		      }
		    }
		    return holes;
	 }
	  
}
