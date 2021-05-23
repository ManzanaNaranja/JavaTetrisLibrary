package ai;

import ai.Brain.Move;
import tetris.Board;
import tetris.Game;
import tetris.PieceInstance;

public class FurryBrain implements Brain{

	@Override
	public Brain.Move bestMove(Game game) {
		double bestScore = 10000000;
		Brain.Move bestMove = null;
		for(Brain.Move move : game.moves()) {
			game.board.place(move);
			double eval = this.evaluateBoard(game.board);
			if(eval < bestScore) {
				bestScore = eval;
				bestMove = move;
			}
			game.board.undo();
		}
		return bestMove;
		
	}

	public double evaluateBoard(Board board) {
		 int holes = getHoles(board);
		 int maxHeight = getMaxHeight(board);
		 double avgHeight = getAverageHeight(board);
		 return 1.25 * holes + maxHeight * 40 + avgHeight * 8;
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
