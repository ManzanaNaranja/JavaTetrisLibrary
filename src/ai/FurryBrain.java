package ai;

import ai.Brain.Move;
import tetris.Board;
import tetris.Game;
import tetris.PieceInstance;

public class FurryBrain implements Brain{
	
	private double w1 = -0.22568649650722883;
	private double w2 = 0.08679520494876472;
	private double w3 = 0.6152727732730796;
	private double w4 = -0.15842464424735841;
	private double w5 = 0.15452215909537684;
	private double w6 = 0.021586109522043928;

	@Override
	public Brain.Move bestMove(Game game) {
		double bestScore = 10000000;
		Brain.Move bestMove = null;
		for(Brain.Move move : game.moves()) {
			game.board.place(move);
			double eval = this.evaluateBoard(game);
			if(eval < bestScore) {
				bestScore = eval;
				bestMove = move;
				bestMove.score = bestScore;
			}
			game.board.undo();
		}
		return bestMove;
		
	}

	public double evaluateBoard(Game game) {
		 Board board = game.board;
		 int lines = game.board.clearLines();
		 int holes = getHoles(board);
		 int maxHeight = getMaxHeight(board);
		 int relativeHeight = this.relativeHeight(board);
		 int cumulativeHeight = this.cumulativeHeights(board);
		 double avgHeight = getAverageHeight(board);
		 double roughness = this.roughness(board);
		  return w1 * lines + w2 * maxHeight + w3 * cumulativeHeight + w4 * relativeHeight + w5 * holes + w6 * roughness;
	}
	
	public int relativeHeight(Board board) {
		return getMaxHeight(board) - getMinHeight(board);
	}
	
	public int getMaxHeight(Board board) {
	    int maxHeight = -1;
	    int[] heights = board.heights();
	    for(int i = 0; i < heights.length; i++) {
	        if(heights[i] > maxHeight) {
	        	maxHeight = heights[i];
	        }
	    }
	    return maxHeight;
	}
	
	public int getMinHeight(Board board) {
	    int minHeight = 99999999;
	    int[] heights = board.heights();
	    for(int i = 0; i < heights.length; i++) {
	        if(heights[i] < minHeight) {
	        	minHeight = heights[i];
	        }
	    }
	    return minHeight;
	}
	
	public int cumulativeHeights(Board board) {
		int sum = 0;
		int[] heights = board.heights();
	    for(int i = 0; i < heights.length; i++) {
	        sum += heights[i];
	    }
	    return sum;
	}
	
	
	public double getAverageHeight(Board board) {
		    double sum = 0;
		    int[] heights = board.heights();
		    for(int i = 0; i < heights.length; i++) {
		      sum += heights[i];
		    } 
		    double avg = sum / heights.length;
		    return avg;
		    
	 }
	 
	public double roughness(Board board) {
		int sum = 0;
		int[] heights = board.heights();
		for(int i = 1; i < heights.length-1; i++) {
			int d1 = Math.abs(heights[i-1] - heights[i]);
			int d2 = Math.abs(heights[i+1] - heights[i]);
			sum += d1;
			sum += d2;
		}
		return Math.pow(sum, 1.5);
		
	}
	public int getHoles(Board board) {
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
