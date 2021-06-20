package launchers;

import tetris.Game;

public class Main {

	public static void main(String[] args) {
		Game g = new Game();
	//	g.right();
//		for(int i = 0; i < 200; i++) {
//			g.right();
//			g.down();
//		}
		g.drop();
		g.ascii();

	}

}
