package ai.geneticAlgo;

import ai.FurryBrain;
import tetris.Game;

public class Individual {
	double fitness = 0;
	double[] genes = new double[6];
	
	public Individual() {
		for(int i = 0; i < genes.length; i++) {
			genes[i] = Math.random() * 2 - 1;
		}
	}
	
	public void calculateFitness() {
		Game game = new Game();
		FurryBrain b = new FurryBrain();
		
		while(game.isOver() == false) {
			game.board.undo();
			game.move(b.bestMove(game));
		}
		System.out.println("Score: " + game.getLinesCleared());
		this.fitness = game.getLinesCleared();
	}
	
	public Individual crossover(Individual other) {
		for(int i = 0; i < this.genes.length; i++) {
			if(Math.random() < .5) {
				this.genes[i] = other.genes[i];
			}
		}	
		return this;
	}
	
	public void mutate(double rate) {
		for(int i = 0; i < this.genes.length; i++) {
			if(Math.random() < rate) {
				this.genes[i] = Math.random() * 2 - 1;
			}
		}
	}
	
	
}
