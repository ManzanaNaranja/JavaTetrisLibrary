package ai.ga;

import java.util.Arrays;

import ai.furry.FurryBrain;
import tetris.Move;
import tetris.Tetris;
import tetris.TetrisEventListener;

public class Individual {
	public double[] dna;
	public static int length = 6;
	public double fitness = -1;
	public static int moveLimit = 500;
	private double score = 0;
	
	public Individual() {
		dna = new double[length];
		for(int i = 0; i < length; i++) {
			dna[i] = Math.random() * 2 - 1;
		}
	}
	
	public Individual(double[] dna) {
		this.dna = dna;
	}
	
	public void calculateFitness() {
		Tetris g = new Tetris();
		int moves = 0;
		TetrisEventListener t = new TetrisEventListener() {

			@Override
			public void linesCleared(int lines) {
				if(lines == 4) score += 99000;
				if(lines == 3) score -= 1;
				if(lines == 2) score -= 10;
				if(lines == 1) score -= 100;
				
			}
			
		};
		g.addEventListener(t);
		
		while(g.game_over() == false && moves < Individual.moveLimit) {
			FurryBrain f = new FurryBrain(this.dna);
			Move m = f.bestMove(g);
			if(m != null) {
				g.move(m, "allowend");
				moves++;
			} else {
				g.drop();
			}
		}
		
	
		this.fitness = score + moves;
	}
	
	public void clearFitness() {
		this.fitness = -1;
		this.score = 0;
	}
	
	public void mutation() {
		int r = (int) (Math.random() * Individual.length);
		double val = dna[r] + Math.signum(Math.random() - 0.5) * .0001;
		dna[r] = Math.max(-1, Math.min(val, 1));
	}
	
	public void crossover(Individual other) {
		int r1 = (int) (Math.random() * Individual.length);
		double[] temp = this.dna;
		int start;
		int end;
		if(Math.random() < 0.5) {
			start = 0;
			end = r1;
		} else {
			start = r1;
			end = Individual.length;
		}	
		for(int i = start; i < end; i++) {
			this.dna[i] = other.dna[i];
		}
		for(int i = start; i < end; i++) {
			other.dna[i] = temp[i];
		}		
	}
	
	public String toString() {
		return Arrays.toString(this.dna);
	}
	
}
