package ui;

import tetris.Game;
import ui.input.KeyManager;

public class Main {
	Game game;
	Window window;
	Renderer renderer;
	
	public Main() {
		game = new Game();
		window = new Window(700, 700);
		renderer = new Renderer(window,game);
		
		window.contentPane.addKeyListener(new KeyManager(game));
		window.contentPane.grabFocus();
		renderer.start();
		
		while(game.isOver() == false) {
			game.moveDown();
			try {
				Thread.sleep(300);
				window.rightPanel.setScore(game.getScore());
				window.rightPanel.setLinesCleared(game.getLinesCleared());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}


	public static void main(String[] args) {
		new Main();
	}

}
