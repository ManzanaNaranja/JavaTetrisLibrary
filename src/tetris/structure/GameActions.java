package tetris.structure;

import tetris.Move;

public interface GameActions {
	public boolean move(Move m);
	public void undo();
	public void reset();
}
