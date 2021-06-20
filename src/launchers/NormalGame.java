package launchers;

import java.awt.Color;
import java.awt.Graphics;

import ai.Brain;
import ai.FurryBrain;
import engine.Job;
import engine.Loop;
import tetris.Game;
import tetris.PieceInstance;
import ui.Renderer;
import ui.Window;
import ui.input.KeyManager;

// High Score: 6180 (300ms)

public class NormalGame extends Loop{
	Game game;
	Window window;
	KeyManager keyManager;
	
	Job gravity = new Job() {
		@Override
		public void dothis() {
			game.moveDown();
			window.leftPanel.position(game.board);					
			window.rightPanel.setScore(game.getScore());
			window.rightPanel.setLinesCleared(game.getLinesCleared());	
		}	
	};
	
	Job input = new Job() {
		@Override
		public void dothis() {
			keyManager.tick();
			if(KeyManager.JW) game.rotate(); 
			if(KeyManager.JA) game.moveLeft();
			if(KeyManager.JD) game.moveRight();
			if(KeyManager.JS) game.moveDown();
			if(KeyManager.JSpace) game.drop();
			window.leftPanel.position(game.board);
		}
	};
	
	public NormalGame() {
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
		new NormalGame();
	}
}
