package launchers;

import ai.furry.FurryBrain;
import ai.ga.Individual;
import engine.Job;
import engine.Loop;
import helpers.AVector;
import tetris.Move;
import tetris.Tetris;
import tetris.TetrisEventListener;
import ui.Window;
import ui.input.KeyManager;


public class UIgame extends Loop{
	Tetris game;
	Window window;
	KeyManager keyManager;
	
	Move m = null;
	
	Job gravity = new Job() {
		@Override
		public void dothis() {
			game.down();
			if(game.current_piece().position.y == 1) {
				FurryBrain f = new FurryBrain(new double[] {-0.5030623668323717, -0.41506546289981094, 0.7475622718654609, 0.9261506530111168, 0.9771274781279766, 0.002196530678791442});
				Move m = f.bestMove(game);
				game.current_piece().position.y = 1;
				if(m == null) game.drop();
				else {
					game.current_piece().rotation = m.getRotation();
					game.current_piece().position.x = m.getX();
					
				}
			
			}
	     	highlightDroppedPiece();
			window.leftPanel.position(game.board());					
			window.rightPanel.setLinesCleared(game.lines_cleared());	
		}	
	};
	
	TetrisEventListener t = new TetrisEventListener() {

		@Override
		public void linesCleared(int lines) {
			System.out.println(lines);
			
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
		this.addJob(gravity, 5);
		this.addJob(input, 17);
		this.start();	
		
	}	
	
	private void initialize() {
		game = new Tetris();
		game.addEventListener(t);
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
