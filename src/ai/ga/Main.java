package ai.ga;

import ai.furry.FurryBrain;
import tetris.Move;
import tetris.Tetris;

public class Main {

	public static void main(String[] args) {
		
		Tetris t = new Tetris();
		FurryBrain f = new FurryBrain(new Individual().dna);
		while(t.game_over() == false) {
			Move m = f.bestMove(t);
			if(m != null) {
				t.move(m);
			} else {
				break;
			}
			t.ascii();
		}
		
		
		
		
		
		
		
		
		
//		Population p = new Population(2);
//		System.out.println(p);
//		p.calculateFitness();
//		System.out.println(p);
		
//		for(int i = 0; i < 10; i++) {
//		
//			p.tournamentSelection();
//			System.out.println(p.averageFitness() + " || " + p.best().fitness);
//			p.crossover();
//			p.mutation();
//		
//			p.clearFitness();
//		}
		

	}

}
