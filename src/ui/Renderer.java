package ui;

import tetris.Game;

public class Renderer extends Thread {
	
	private Window window;
	private Game game;
	
	public Renderer(Window window, Game game) {
		this.window = window;
		this.game = game;
	}
	
	public void run() {
		while(true) {
			window.leftPanel.position(game.board);
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
