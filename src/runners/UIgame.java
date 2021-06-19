package runners;

import engine.Job;
import engine.Loop;
import tetris.Game;
import ui.Window;
import ui.input.KeyManager;

// High Score: 6180 (300ms)

public class UIgame extends Loop{
	Game game;
	Window window;
	KeyManager keyManager;
	
	Job gravity = new Job() {
		@Override
		public void dothis() {
			game.down();
			window.leftPanel.position(game.board());					
//			window.rightPanel.setScore(game.getScore());
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
		
			if(KeyManager.JS) game.undo(); // game.down();
			
			if(KeyManager.JSpace) game.drop();
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

	public static void main(String[] args) {
		new UIgame();
	}
}
