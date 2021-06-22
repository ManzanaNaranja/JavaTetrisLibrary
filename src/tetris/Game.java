package tetris;

import java.util.ArrayList;

import helpers.AUtils;
import tetris.piece.Bag;
import tetris.piece.PieceInstance;
import tetris.structure.GameActions;
import tetris.structure.GameInfo;
import tetris.structure.PlayerActions;

public class Game implements GameActions, PlayerActions, GameInfo{
	
	public Bag bag;
	private PieceInstance currentPiece;
	private BoardHistory boardHistory;
	private Board board;
	private int linesCleared = 0;
	private boolean gameOver = false;
	
	public Game() {
		this.reset();
	}

	@Override
	public boolean game_over() {
		return this.gameOver;
	}

	@Override
	public int[][] board() {
		return board.getMemory();
	}
	
	public int[][] clean_board() {
		return board.getBackupMemory();
	}


	@Override
	public void ascii() {
		System.out.println(this.toString());		
	}
	
	public PieceInstance current_piece() {
		return this.currentPiece;
	}

	@Override
	public PieceInstance next_piece() { 
		return bag.view_next();
	}
	
	private PieceInstance pick_next_piece() {
		return bag.pick();
	}

	@Override
	public int get(int x, int y) {
		return board.getBlock(x, y);
	}

	@Override
	public Move[] moves() {
		board.undo();
		int xx = currentPiece.position.x;
		int yy = currentPiece.position.y;
		ArrayList<Move> moves = new ArrayList<Move>();
		int xBound = Board.COLS - currentPiece.getWidth()+1;
		int shift = currentPiece.getGap();
		for(int spin = 0; spin < 4; spin++) {
			xBound = Board.COLS - currentPiece.getWidth()+1;
			shift = currentPiece.getGap();
			for(int x = 0-shift; x < xBound-shift; x++) {
				int y = board.dropHeight(currentPiece, x);
				moves.add(new Move(x, y, currentPiece.rotation, currentPiece.getPiece()));
				
			}
			currentPiece.rotate();
		}
		Move[] m = new Move[moves.size()];
		for(int i = 0; i < m.length; i++) {
			m[i] = moves.get(i);
		}
		
		this.board.place(currentPiece,xx,yy);
		return m;
	}

	@Override
	public int lines_cleared() {
		return this.linesCleared;
	}

	@Override
	public void right() {
		if(gameOver == true) return;
		board.undo();
		boolean placement = board.place(currentPiece, ++currentPiece.position.x, currentPiece.position.y);
		if(placement == false) {
			board.place(currentPiece, --currentPiece.position.x, currentPiece.position.y);
		}	
	}

	@Override
	public void left() {
		if(gameOver == true) return;
		board.undo();
		boolean placement = board.place(currentPiece, --currentPiece.position.x, currentPiece.position.y);
		if(placement == false) {
			board.place(currentPiece, ++currentPiece.position.x, currentPiece.position.y);
		}
	}

	@Override
	public void down() {
		if(this.game_over() == true) return;
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

	@Override
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

	@Override
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

	@Override
	public int move(Move m) {
		if(gameOver == true) return -1;
		Move[] moves = this.moves();
		for(int i = 0; i < moves.length; i++) {
			if(m.equals(moves[i])) break;
			else if(i == moves.length-1) return -1;
		}
		board.undo();
		if(board.place(m) == false) return -1;
		return this.finalizePlacement();
	}
	
	

	@Override
	public void undo() { 
		if(boardHistory.undo() == null) return;
		this.bag.undo();
		this.currentPiece = bag.view_prev();
		this.board = new Board(AUtils.deepCopy(boardHistory.current_state()));

	}

	@Override
	public void reset() {
		this.initilizeGame();
		board.place(currentPiece, currentPiece.position.x, currentPiece.position.y);
	}
	
	public String toString() {
		return this.board.toString();
	}
	
	private void initilizeGame() {
		boardHistory = new BoardHistory();
		bag = new Bag();
		this.currentPiece = this.pick_next_piece();
		this.board = new Board();
		boardHistory.add(this.board.getMemory());
	}
	
	private int finalizePlacement() {
		if(this.game_over() == true) return -1;
		int lines = board.clearLines();
		this.linesCleared += lines;
		
		board.commit();
		this.currentPiece = this.pick_next_piece();	
		boardHistory.add(this.board.getMemory());
		boolean newPlacement = board.place(currentPiece, currentPiece.position.x, currentPiece.position.y);
		if(newPlacement == false) {
			this.setGameOver();
		}
		return lines;
	}
	
	private void setGameOver() {
		board.commit();
		this.gameOver = true;
		System.out.println("GAME_OVER");
	}
}
