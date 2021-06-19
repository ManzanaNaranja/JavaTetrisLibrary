package tetris.structure;

public interface GameActions {
	public boolean move(String m);
	public void undo();
	public void reset();
}
