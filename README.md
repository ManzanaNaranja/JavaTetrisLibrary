# JavaTetrisLibrary
Includes optional UI and keyinput handling

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

## visualize all moves
```java 
while(game.isOver() == false) {
  Brain.Move[] moves = game.moves();
  for(Brain.Move m : moves) {
    game.board.place(m);
    Thread.sleep(100);
    game.board.undo();
  }
  game.move(moves[(int)(Math.random() * moves.length)]);
  Thread.sleep(300);	
}
```

![gif](./images/iteration.gif)

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

## TODO
- fix rendering problems
- debug ai evaulation function methods
- allow AI to work without having to do a board.undo()
  

