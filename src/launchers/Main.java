package launchers;

import ai.FurryBrain;
import tetris.Move;
import tetris.Tetris;

public class Main {

	public static void main(String[] args) {
		Tetris g = new Tetris();
		while(g.game_over() == false) {
			FurryBrain f = new FurryBrain();
			Move m = f.bestMove(g);
			g.move(m);
			System.out.println(g.lines_cleared());
		}
		

	}

}
