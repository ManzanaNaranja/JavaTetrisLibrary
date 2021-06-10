# JavaTetrisLibrary
Java library used to keep track of the Tetris board. 

[link](https://github.com/ManzanaNaranja/JavaTetrisLibrary/releases/download/0.0.0/tetrislib.jar)

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

## use provided ai

```java
FurryBrain b = new FurryBrain();
while(game.isOver() == false) {
  game.moveDown();
  if(game.currentPiece.position.y == 0) {
    game.board.undo();
    Brain.Move m = b.bestMove(game);
    game.currentPiece.rotation = m.piece.rotation;
    game.currentPiece.position.x = m.x;
    window.leftPanel.position(game.board);
  }
  Thread.sleep(30);
  window.rightPanel.setScore(game.getScore());
  window.rightPanel.setLinesCleared(game.getLinesCleared());		
}
```
![gif](./images/aiplaying.gif)

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
- add downloadable .jar files
  

