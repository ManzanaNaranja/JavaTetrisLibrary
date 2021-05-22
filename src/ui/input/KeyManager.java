package ui.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tetris.Game;


public class KeyManager extends Thread implements KeyListener{
	
	private boolean[] keys, justPressed, cantPress;
	public static boolean W, A, S,D,Space;
	public static boolean JW,JA,JS,JD, JSpace;
	private Game game;
	public KeyManager(Game game) {
		this.game = game;
		keys = new boolean[256];
		justPressed = new boolean[256];
		cantPress = new boolean[256];
		this.start();
	}

	public void tick() {
		
		for(int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			} else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true; 
			}
		}
		W = keys[KeyEvent.VK_W];
		A = keys[KeyEvent.VK_A];
		S = keys[KeyEvent.VK_S];
		D = keys[KeyEvent.VK_D];
		Space = keys[KeyEvent.VK_SPACE];
		
		JW = justPressed[KeyEvent.VK_W];
		JA = justPressed[KeyEvent.VK_A];
		JS = justPressed[KeyEvent.VK_S];
		JD = justPressed[KeyEvent.VK_D];
		JSpace = justPressed[KeyEvent.VK_SPACE];
	}
	
	boolean justPressed(int i) {
		return justPressed[i] == false && keys[i] == true;
	}
	
	boolean alreadyPressed(int i) {
		return justPressed[i] == true;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
		
	}
	
	public void run() {
		while(true) {
			this.tick();
			if(KeyManager.JSpace) game.drop();
			if(KeyManager.JD) game.moveRight();
			if(KeyManager.JA) game.moveLeft();
			if(KeyManager.JW) game.rotate();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
