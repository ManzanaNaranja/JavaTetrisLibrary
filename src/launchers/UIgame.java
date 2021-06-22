package launchers;

import ai.FurryBrain;
import engine.Job;
import engine.Loop;
import helpers.AVector;
import tetris.Game;
import tetris.Move;
import ui.Window;
import ui.input.KeyManager;

// High Score: 6180 (300ms)

public class UIgame extends Loop{
	Game game;
	Window window;
	KeyManager keyManager;
	
	Move m = null;
	
	Job gravity = new Job() {
		@Override
		public void dothis() {
			game.down();
	     	highlightDroppedPiece();
			window.leftPanel.position(game.board());					
			window.rightPanel.setLinesCleared(game.lines_cleared());	
		}	
	};

	
	Job input = new Job() {
		@Override
		public void dothis() {
			keyManager.tick();
			if(KeyManager.JW) game.rotate(); 
			if(KeyManager.JA) game.left();
			if(KeyManager.JD) game.right();
			if(KeyManager.JS) game.undo();
			if(KeyManager.JSpace) game.drop();
			highlightDroppedPiece();
			window.leftPanel.position(game.board());
		}
	};
	
	public UIgame() {
		initialize();
		this.addJob(gravity, 300);
		this.addJob(input, 17);
		this.start();	
		
	}	
	
	private void initialize() {
		game = new Game();
		window = new Window(700, 700);
		keyManager = new KeyManager();
		window.contentPane.addKeyListener(keyManager);
		window.contentPane.grabFocus();
	}
	
	private void highlightMove(Move m) {
		window.leftPanel.clearHighlight();
		AVector[] pts = m.getPoints();
		for(AVector p : pts) {
			window.leftPanel.addHighlight((int)p.x, (int)p.y, 8);
		}
	}
	
	private void highlightDroppedPiece() {
		window.leftPanel.clearHighlight();
		Move[] moves = game.moves();
		for(Move mve : moves) {
			if(mve.getX() == game.current_piece().position.x && mve.getRotation() == game.current_piece().rotation) {
				AVector[] pts = mve.getPoints();
				for(AVector p : pts) {
					window.leftPanel.addHighlight((int)p.x, (int)p.y, 8);
				}
			}
		}
	}

	public static void main(String[] args) {
		new UIgame();
	}
}
