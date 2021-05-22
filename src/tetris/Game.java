package tetris;

import ai.Brain;

public class Game {
	
	public PieceInstance currentPiece;
	public Board board;
	
	private PieceInstanceBag bag;
	private int x = 4, y = 0;
	private boolean gameOver = false;
	
	private int score = 0;
	private int linesCleared = 0;
	
	
	public Game() {
		this.reset();
	}
	
	public void reset() {
		this.initilizeGame();
		board.place(currentPiece, x, y);
	}
	
	private void initilizeGame() {
		bag = new PieceInstanceBag();
		this.currentPiece = bag.pick();
		this.board = new Board();
	}
	
	public void moveRight() {
		if(gameOver == true) return;
		board.undo();
		boolean placement = board.place(currentPiece, ++x, y);
		if(placement == false) {
			board.place(currentPiece, --x, y);
		}
	}
	
	public void rotate() {
		if(gameOver == true) return;
		board.undo();
		currentPiece.rotate();
		boolean placement = board.place(currentPiece, x, y);
		if(placement == false) {
			currentPiece.unrotate();
			board.place(currentPiece, x, y);
		}
	}
	
	public void moveLeft() {
		if(gameOver == true) return;
		board.undo();
		boolean placement = board.place(currentPiece, --x, y);
		if(placement == false) {
			board.place(currentPiece, ++x, y);
		}
	}
	
	public void moveDown() {
		if(gameOver == true) return;
		board.undo();
		boolean result = board.place(currentPiece, x, ++y);
		if(result == false) {
			board.place(currentPiece, x, --y);
			this.finalizePlacement();
		}
	}
	
	public void drop() {
		if(gameOver == true) return;
		boolean result = false;
		do {
			board.undo();
			result = board.place(currentPiece, x, ++y);
		} while(result);
		board.undo();
		board.place(currentPiece, x, --y);
		this.finalizePlacement();
	}
	
	public void finalizePlacement() {
		if(gameOver == true) return;
		int lines = board.clearLines();
		this.linesCleared += lines;
		updateScore(lines);
		
		board.commit();
		this.currentPiece = bag.pick();	
		x = 4;
		y = 0;
		boolean newPlacement = board.place(currentPiece, x, y);
		if(newPlacement == false) {
			this.gameOver = true;
			board.setAllPieces(Piece.Dead);
		}
	}
	
	public Brain.Move[] moves() {
		return board.moves(currentPiece);
	}
	
	private void updateScore(int lines) {
		switch(lines) {
			case 1: 
				this.score += 40;
				break;
			case 2: 
				this.score += 100;
				break;
			case 3: 
				this.score += 300;
				break;
			case 4: 
				this.score += 1200;
				
		}
	}
	
	public int getScore() {
		return score;
	}

	public int getLinesCleared() {
		return linesCleared;
	}
	
	public boolean isOver() {
		return this.gameOver;
	}
	
	
	
}
