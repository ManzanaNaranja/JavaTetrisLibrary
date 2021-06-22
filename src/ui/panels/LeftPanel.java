package ui.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import tetris.Board;
import tetris.piece.Piece;
import ui.colors.PieceColors;
import ui.settings.Settings;

public class LeftPanel extends JPanel{
	
	private int[][] board;
	private int[][] highlight = new int[22][10];
	PieceColors colors = new PieceColors();
	
	public LeftPanel() {
		setBackground(Color.black);
		setBounds(10, 10, 270, 540);
	}
	
	public void position(int[][] board) {
		this.board = board;
		this.repaint();
	}
	
	public void addHighlight(int x, int y, int num) {
		highlight[y][x] = num;
	}
	
	public void clearHighlight() {
		highlight = new int[22][10];
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(this.board == null) return;
		super.paintComponent(g);
		
		for(int y =  Board.ROW_START; y < Board.ROWS; y++) {
			for(int x = 0; x < Board.COLS; x++) {
				if(board[y][x] != 0)
					paintHelper(g, colors.get(board[y][x]), Settings.BORDER_COLOR, x, y);
				if(highlight[y][x] != 0) {
					paintHelper(g, colors.get(board[y][x]), Settings.BORDER_COLOR, x, y);
				}
				
			}
		}
		
	}
	

	private void paintHelper(Graphics g, Color startColor, Color endColor, int x, int y) {
		g.setColor(startColor);
		g.fillRect(Settings.LEFT + x * Settings.SIZE, (Settings.TOP) + y * Settings.SIZE, Settings.SIZE, Settings.SIZE);
		g.setColor(endColor);
	}
}
