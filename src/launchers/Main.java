package launchers;

import ai.furry.FurryBrain;
import ai.ga.Individual;
import ai.ga.Population;
import tetris.Move;
import tetris.Tetris;

public class Main {

	public static void main(String[] args) {
//		Tetris g = new Tetris();
//		while(g.game_over() == false) {
//			FurryBrain f = new FurryBrain(new Individual().dna);
//			Move m = f.bestMove(g);
//			if(m != null) {
//				g.move(m, "allowend");
//			} else {
//				g.drop();
//			}
//			System.out.println(g.lines_cleared());
//		}
		
		Population population = new Population(1000);
		for(int i = 0; i < 100; i++) {
			population.tournamentSelection();
			System.out.println(population.best().fitness + " [ ] "+ population.best());
			population.crossover();
			population.mutation();
			population.clearFitness();
		}
		

	}

}
