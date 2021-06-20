package tetris;

import ai.Brain;

public class Game {
	
	public PieceInstance currentPiece;
	public Board board;	
	private PieceInstanceBag bag;
	private boolean gameOver = false;
	
	private int score = 0;
	private int linesCleared = 0;
	
	
	public Game() {
		this.reset();
	}
	
	public void reset() {
		this.initilizeGame();
		board.place(currentPiece, currentPiece.position.x, currentPiece.position.y);
	}
	
	private void initilizeGame() {
		bag = new PieceInstanceBag();
		this.currentPiece = bag.pick();
		this.board = new Board();
	}
	
	public void moveRight() {
		if(gameOver == true) return;
		board.undo();
		boolean placement = board.place(currentPiece, ++currentPiece.position.x, currentPiece.position.y);
		if(placement == false) {
			board.place(currentPiece, --currentPiece.position.x, currentPiece.position.y);
		}
	}
	
	public void rotate() {
		if(gameOver == true) return;
		board.undo();
		currentPiece.rotate();
		boolean placement = board.place(currentPiece, currentPiece.position.x, currentPiece.position.y);
		if(placement == false) {
			currentPiece.unrotate();
			board.place(currentPiece, currentPiece.position.x, currentPiece.position.y);
		}
	}
	
	public void moveLeft() {
		if(gameOver == true) return;
		board.undo();
		boolean placement = board.place(currentPiece, --currentPiece.position.x, currentPiece.position.y);
		if(placement == false) {
			board.place(currentPiece, ++currentPiece.position.x, currentPiece.position.y);
		}
	}
	
	public void moveDown() {
		
		if(gameOver == true) return;
		board.undo();
		boolean result = board.place(currentPiece, currentPiece.position.x, ++currentPiece.position.y);
		
		if(result == false) {
			boolean upplacement = board.place(currentPiece, currentPiece.position.x, --currentPiece.position.y);
			if(upplacement == false) {
				this.setGameOver();
			} else {
				this.finalizePlacement();
			}
			
		}
	}
	
	public void drop() {
		if(gameOver == true) return;
		boolean result = false;
		do {
			board.undo();
			result = board.place(currentPiece, currentPiece.position.x, ++currentPiece.position.y);
		} while(result);
		board.undo();
		board.place(currentPiece, currentPiece.position.x, --currentPiece.position.y);
		this.finalizePlacement();
	}
	public void move(Brain.Move m) {
		boolean placement = this.board.place(m);
		if(placement == false) {
			this.setGameOver();
		} else {
			this.finalizePlacement();
		}
	}
	
	public void finalizePlacement() {
		if(gameOver == true) return;
		int lines = board.clearLines();
		this.linesCleared += lines;
		updateScore(lines);
		
		board.commit();
		this.currentPiece = bag.pick();	
		boolean newPlacement = board.place(currentPiece, currentPiece.position.x, currentPiece.position.y);
		if(newPlacement == false) {
			this.setGameOver();
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
	
	private void setGameOver() {
		board.setAllPieces(Piece.L);
		board.commit();
		this.gameOver = true;
	}	
}
