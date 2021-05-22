package ui;

import ai.Brain;
import tetris.Game;
import ui.input.KeyManager;

public class Main {
	Game game;
	Window window;
	Renderer renderer;
	
	public Main() throws InterruptedException {
		game = new Game();
		window = new Window(700, 700);
		renderer = new Renderer(window,game);
		window.contentPane.addKeyListener(new KeyManager(game));
		window.contentPane.grabFocus();
		renderer.start();
		
		while(game.isOver() == false) {
		//	window.leftPanel.position(game.board);
			Brain.Move[] moves = game.moves();
			for(Brain.Move m : moves) {
				game.board.place(m);
			//	window.leftPanel.position(game.board);
				Thread.sleep(100);
				game.board.undo();
				//window.leftPanel.position(game.board);
			}
			game.move(moves[(int)(Math.random() * moves.length)]);
			//window.leftPanel.position(game.board);
			Thread.sleep(100);
		}
		
//		while(game.isOver() == false) {
			

			
			
			
//			game.moveDown();
//			Thread.sleep(300);
//			window.rightPanel.setScore(game.getScore());
//			window.rightPanel.setLinesCleared(game.getLinesCleared());	
//		}	
	}


	public static void main(String[] args) {
		try {
			new Main();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
