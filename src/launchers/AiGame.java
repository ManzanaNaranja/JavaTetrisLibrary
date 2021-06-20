package launchers;

import ai.Brain;
import ai.FurryBrain;
import engine.Job;
import engine.Loop;
import tetris.Game;
import ui.Renderer;
import ui.Window;
import ui.input.KeyManager;

public class AiGame extends Loop{
	Game game;
	Window window;
	KeyManager keyManager;
	
	Job gravity = new Job() {
		@Override
		public void dothis() {
			FurryBrain b = new FurryBrain();
			Brain.Move m = null;
			
			game.moveDown();
			if(game.currentPiece.position.y == 0) {
				game.board.undo();
				m = b.bestMove(game);
				game.currentPiece.rotation = m.piece.rotation;
				game.currentPiece.position.x = m.x;
				game.board.place(game.currentPiece, game.currentPiece.position.x, game.currentPiece.position.y);
			}	
			if(m != null) window.rightPanel.setEval(m.score);
			window.leftPanel.position(game.board);
			window.rightPanel.setScore(game.getScore());
			window.rightPanel.setLinesCleared(game.getLinesCleared());		
		}
	};
	
	public AiGame() throws InterruptedException {
		initialize();
		this.addJob(gravity, 10);
		this.start();
	}
	
	public void initialize() {
		game = new Game();
		window = new Window(700, 700);
		keyManager = new KeyManager();
		window.contentPane.addKeyListener(keyManager);
		window.contentPane.grabFocus();
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
