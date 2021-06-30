package launchers;

import tetris.Tetris;

public class Main {

	public static void main(String[] args) {
		Tetris g = new Tetris();
	//	g.right();
//		for(int i = 0; i < 200; i++) {
//			g.right();
//			g.down();
//		}
		
		System.out.println(g.bag);
		for(int i = 0; i < 100; i++) {
			g.drop();
			g.undo();
		}
		g.drop();
		for(int i = 0; i < 100; i++) {
			g.drop();
			g.undo();
		}
		g.drop();
		for(int i = 0; i < 100; i++) {
			g.drop();
			g.undo();
		}
		g.drop();g.drop();g.drop();g.drop();g.drop();
		g.ascii();
		System.out.println(g.bag);

	}

}
