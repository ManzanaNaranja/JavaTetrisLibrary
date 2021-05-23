package ui.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import tetris.Board;
import tetris.Piece;
import ui.settings.Settings;

public class LeftPanel extends JPanel{
	
	private Board board;
	
	public LeftPanel() {
		setBackground(Color.black);
		setBounds(10, 10, 270, 540);
	}
	
	public void position(Board board) {
		this.board = board;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Settings.STAR_COLOR);
		
		for(int y = Board.ROW_START; y < Board.ROWS; y++) {
			for(int x = 0; x < Board.COLS; x++) {
				if(board.memory[y][x] == 0) {
					paintHelper(g, Piece.getPieceByValue(board.memory[y][x]).getColor(), Settings.BORDER_COLOR, x, y);
				}
				
			}
		}

		for(int y = 1 +  Board.ROW_START; y < Board.ROWS; y++) {
			for(int x = 1; x < Board.COLS; x++) {
				star(g,Settings.LEFT + x * Settings.SIZE, Settings.TOP + y * Settings.SIZE);			
			}
		}
		
		for(int y =  Board.ROW_START; y < Board.ROWS; y++) {
			for(int x = 0; x < Board.COLS; x++) {
				if(board.memory[y][x] != 0)
					paintHelper(g, Piece.getPieceByValue(board.memory[y][x]).getColor(), Settings.BORDER_COLOR, x, y);
			}
		}
		
		
		
	}
	
	private void star(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		int length = 3;
		g2d.drawLine(x, y, x, y+length);
		g2d.drawLine(x, y, x, y-length);
		g2d.drawLine(x, y, x+length, y);
		g2d.drawLine(x, y, x-length, y);
		
	}

	private void paintHelper(Graphics g, Color startColor, Color endColor, int x, int y) {
		g.setColor(startColor);
		g.fillRect(Settings.LEFT + x * Settings.SIZE, (Settings.TOP) + y * Settings.SIZE, Settings.SIZE, Settings.SIZE);
		g.setColor(endColor);
	}
}
