package tetris;

public interface TetrisEventListener {
	public void linesCleared(int lines);
	public void finalizedPiecePlacement();
}
