package ui;

import ai.Brain;
import ai.FurryBrain;
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
		FurryBrain b = new FurryBrain();
		
		while(game.isOver() == false) {
		
			game.moveDown();
		//	game.board.undo();
			// b.bestMove(game);
			
			if(game.currentPiece.position.y == 0) {
				game.board.undo();
				Brain.Move m = b.bestMove(game);
				game.currentPiece.rotation = m.piece.rotation;
				game.currentPiece.position.x = m.x;
				window.leftPanel.position(game.board);
			}
			
			
			
			Thread.sleep(30);
			window.rightPanel.setScore(game.getScore());
			window.rightPanel.setLinesCleared(game.getLinesCleared());	
		}	
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
