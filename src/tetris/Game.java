package tetris;

import java.util.ArrayList;
import java.util.Stack;

import helpers.AUtils;
import tetris.piece.Piece;
import tetris.piece.PieceInstance;
import tetris.piece.PieceInstanceBag;
import tetris.structure.GameActions;
import tetris.structure.GameInfo;
import tetris.structure.PlayerActions;

public class Game implements GameActions, PlayerActions, GameInfo{
	
	private PieceInstanceBag bag;
	private PieceInstance currentPiece;
	private Stack<PieceInstance> nextPieces;
	private Board board;
	private int linesCleared = 0;
	private boolean gameOver = false;
	private History history;
	
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

	@Override
	public void ascii() {
		System.out.println(this.toString());		
	}

	@Override
	public PieceInstance next_piece() { 
		if(nextPieces.isEmpty() == false) {
			return this.nextPieces.peek();
		}
		return bag.getnext();
	}
	
	private PieceInstance pick_next_piece() {
		if(nextPieces.isEmpty() == false) {
			return this.nextPieces.pop();
		}
		return bag.pick();
	}

	@Override
	public int get(int x, int y) {
		return board.getBlock(x, y);
	}
	
	@Override
	public Move[] moves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		int xBound = Board.COLS - this.currentPiece.getWidth()+1;
		int shift = currentPiece.getGap();
		for(int spin = 0; spin < 4; spin++) {
			xBound = Board.COLS - p.getWidth()+1;
			shift = currentPiece.getGap();
			for(int x = 0-shift; x < xBound-shift; x++) {
				int y = this.dropHeight(currentPiece, x);
				moves.add(new Move((PieceInstance) p.clone(),x,y));

			}
			p.rotate();
	}

		Brain.Move[] m = new Brain.Move[moves.size()];
		for(int i = 0; i < m.length; i++) {
			m[i] = moves.get(i);
		}
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
	public boolean move(String m) {
		return false;
	}

	@Override
	public void undo() { 
		if(history.undo() == null) return;
		GameData d = history.getLast();
		this.board = new Board(AUtils.deepCopy(d.getBoard()));
		this.currentPiece = d.getCurrentPiece();
		this.nextPieces.clear();
		this.nextPieces.addAll(history.getPieceHistory());
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
		nextPieces = new Stack<PieceInstance>();
		history = new History();
		bag = new PieceInstanceBag();
		this.currentPiece = this.pick_next_piece();
		this.board = new Board();
		history.add(new GameData(this.board.getMemory(), this.currentPiece.getPiece(), this.next_piece().getPiece()));
	}
	
	private void finalizePlacement() {
		if(this.game_over() == true) return;
		int lines = board.clearLines();
		this.linesCleared += lines;
		
		board.commit();
		this.currentPiece = this.pick_next_piece();	
		history.add(new GameData(this.board.getMemory(), this.currentPiece.getPiece(), this.next_piece().getPiece()));
		boolean newPlacement = board.place(currentPiece, currentPiece.position.x, currentPiece.position.y);
		if(newPlacement == false) {
			this.setGameOver();
		}
	}
	
	private void setGameOver() {
		board.commit();
		this.gameOver = true;
		System.out.println("GAME_OVER");
	}
}
