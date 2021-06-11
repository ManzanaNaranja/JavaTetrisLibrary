package ai.geneticAlgo;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneticAlgorithm {
	
	Population pop = new Population(1000);
	ArrayList<Individual> matingpool;
	Individual fittest, secondFittest;
	int generation = 0;
	double mutationrate = .3;
	
	public GeneticAlgorithm() {
		while(generation < 500) {
			generation++;
			System.out.println("GEN: " + generation);
			pop.calculateFitness();
			System.out.println("BEST Fitness: " + pop.getFittest().fitness);
//			System.out.println("BEST GENES: " + Arrays.toString(pop.getFittest().genes));
			this.selection();
			this.reproduce();
		}
	}
	
    public void selection() {
    	matingpool = new ArrayList<Individual>();
//    	for(int i = 0; i < pop.popsize; i++) {
//    		for(int j = 0; j < Math.pow(pop.individuals[i].fitness, 2); j++) {
//    			matingpool.add(pop.individuals[i]);
//    		}
//    	}
    	
    	matingpool.add(pop.getFittest());
    	matingpool.add(pop.getSecondFittest());
    }
    
    public void reproduce() {
    	for(int i = 0; i < pop.popsize; i++) {
    		int a = (int) (Math.random() * this.matingpool.size());
        	int b = (int) (Math.random() * this.matingpool.size());
        	
        	Individual p1 = matingpool.get(a);
        	Individual p2 = matingpool.get(b);
        	Individual child = p1.crossover(p2);
        	pop.individuals[i] = child;
        	child.mutate(this.mutationrate);
    	}
    	
    }
	
	
	
	public static void main(String[] args) {
		 new GeneticAlgorithm();
	
	}
}
