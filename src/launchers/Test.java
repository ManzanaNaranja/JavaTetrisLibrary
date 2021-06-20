package launchers;

import ai.Brain;
import ai.FurryBrain;
import tetris.Game;

public class Test {
	
	Game game;
	
	public Test() throws InterruptedException {
		game = new Game();
		FurryBrain f = new FurryBrain();
		while(game.isOver() == false) {
			game.board.undo();
			game.move(f.bestMove(game));
			Thread.sleep(30);
		}
		System.out.println(game.board);
		System.out.println("Game Over");
		System.out.println("Score: " + game.getScore());
		System.out.println("Lines Cleared: " + game.getLinesCleared());
	}

	public static void main(String[] args) {
		try {
			new Test();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
