# JavaTetrisLibrary
Java Tetris library. Comes with an optional UI for rendering the game.

## Examples

## Create a Game

```
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
