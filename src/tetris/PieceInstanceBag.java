package tetris;

import java.util.ArrayList;

public class PieceInstanceBag {
	private ArrayList<PieceInstance> contents;
	public PieceInstanceBag() {
		contents = new ArrayList<PieceInstance>();
		this.refill();
	}
	
	public void refill() {
		Piece[] pieces = Piece.values();
		for(int i = 0; i < pieces.length-1; i++) {
			for(int j = 0; j < 4; j++) {
				contents.add(new PieceInstance(pieces[i]));
			}
		}
	}
	
	public PieceInstance pick() {
		int rand = (int)(Math.random() * (contents.size()-1)); // -1 because last block is empty block
		PieceInstance picked =  contents.remove(rand);
		if(contents.size() == 0) {
			this.refill();
		}
		return picked;
	}
}
