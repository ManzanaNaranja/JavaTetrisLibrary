package ui.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import tetris.Board;
import tetris.piece.Piece;
import ui.settings.Settings;

public class LeftPanel extends JPanel{
	
	private int[][] board;
	
	public LeftPanel() {
		setBackground(Color.black);
		setBounds(10, 10, 270, 540);
	}
	
	public void position(int[][] board) {
		this.board = board;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(this.board == null) return;
		super.paintComponent(g);
		
		for(int y =  Board.ROW_START; y < Board.ROWS; y++) {
			for(int x = 0; x < Board.COLS; x++) {
				if(board[y][x] != 0)
					paintHelper(g, Piece.getPieceByValue(board[y][x]).getColor(), Settings.BORDER_COLOR, x, y);
			}
		}
		
	}
	

	private void paintHelper(Graphics g, Color startColor, Color endColor, int x, int y) {
		g.setColor(startColor);
		g.fillRect(Settings.LEFT + x * Settings.SIZE, (Settings.TOP) + y * Settings.SIZE, Settings.SIZE, Settings.SIZE);
		g.setColor(endColor);
	}
}
