# JavaTetrisLibrary
Java Tetris library. Comes with an optional UI for rendering the game.

## Examples

## random moves

```java
	Game game = new Game();
	while(game.isOver() == false) {
	        Move[] moves = game.moves();
		Move m = moves[(int)(Math.random() * moves.length)];
		game.move(m);
	}
	System.out.println(game.board);

```

## Create a Game with UI

```java
import tetris.Game;
import ui.input.KeyManager;

public class Main {
	Game game;
	Window window;
	Renderer renderer;
	
	public Main() throws InterruptedException {
		game = new Game();
		window = new Window(700, 700);
		renderer = new Renderer(window,game);
		window.contentPane.addKeyListener(new KeyManager(game));
		window.contentPane.grabFocus();
		renderer.start();
		
		while(game.isOver() == false) {
			game.moveDown();
			Thread.sleep(300);
			window.rightPanel.setScore(game.getScore());
			window.rightPanel.setLinesCleared(game.getLinesCleared());	
		}	
	}
}
  ```
  

