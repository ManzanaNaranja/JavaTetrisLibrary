package tetris;

import java.util.LinkedList;
import java.util.Stack;

import tetris.piece.PieceInstance;

public class History {
	private LinkedList<GameData> data = new LinkedList<GameData>();
	private Stack<PieceInstance> pieces = new Stack<PieceInstance>();
	
	public void add(GameData d) {
		pieces.clear();
		data.add(d);
	}
	
	public GameData undo() {
		if(data.isEmpty() || data.size() <= 1) return null;
		if(pieces.size() == 0) {
			pieces.add(data.getLast().getNextPiece());
		}
		pieces.add(data.getLast().getCurrentPiece());
		return data.removeLast();
	}
	
	public GameData getLast() {
		return data.getLast();
	}
	
	public String toString() {
		String contents = "";
		for(GameData d : data) {
			contents += d.getCurrentPiece().getPiece() + "" + d.getNextPiece().getPiece() + " ";
		}
		return contents;
	}
	
	@SuppressWarnings("unchecked")
	public Stack<PieceInstance> getPieceHistory() {
		return (Stack<PieceInstance>) this.pieces.clone();
	}
}
