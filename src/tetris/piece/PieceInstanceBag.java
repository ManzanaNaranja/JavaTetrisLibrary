package tetris.piece;

import java.util.ArrayList;
import java.util.Collections;

public class PieceInstanceBag {
	private ArrayList<PieceInstance> contents;
	public PieceInstanceBag() {
		contents = new ArrayList<PieceInstance>();
		this.refill();
		this.shuffle();
	}
	
	public void refill() {
		contents.clear();
		Piece[] pieces = Piece.values();
		for(int i = 0; i < pieces.length; i++) {
			for(int j = 0; j < 2; j++) {
				contents.add(new PieceInstance(pieces[i]));
			}
		}
	}
	
	public PieceInstance pick() {
		PieceInstance picked =  contents.remove(0);
		if(contents.size() == 0) {
			this.refill();
			this.shuffle();
		}
		return picked;
	}
	
	public void shuffle() {
		Collections.shuffle(contents);
	}
	
	public void setnext(PieceInstance p) {
		contents.set(0, p);
	}

	public PieceInstance getnext() {
		if(contents.size() == 0) {
			this.refill();
			this.shuffle();
		}
		return contents.get(0);
		
	}
}
