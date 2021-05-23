package launchers;

import ai.Brain;
import ai.FurryBrain;
import tetris.Game;
import ui.Renderer;
import ui.Window;
import ui.input.KeyManager;

public class AiGame {
	Game game;
	Window window;
	Renderer renderer;
	
	public AiGame() throws InterruptedException {
		game = new Game();
		window = new Window(700, 700);
		renderer = new Renderer(window,game);
		window.contentPane.addKeyListener(new KeyManager(game));
		window.contentPane.grabFocus();
		renderer.start();
		FurryBrain b = new FurryBrain();
		Brain.Move m = null;
		
		while(game.isOver() == false) {
			game.moveDown();
			if(game.currentPiece.position.y == 0) {
				game.board.undo();
				m = b.bestMove(game);
				game.currentPiece.rotation = m.piece.rotation;
				game.currentPiece.position.x = m.x;
				 window.leftPanel.position(game.board);
			}	
			Thread.sleep(10);
			if(m != null) window.rightPanel.setEval(m.score);
			window.rightPanel.setScore(game.getScore());
			window.rightPanel.setLinesCleared(game.getLinesCleared());			
		}	
	}


	public static void main(String[] args) {
		try {
			new AiGame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
