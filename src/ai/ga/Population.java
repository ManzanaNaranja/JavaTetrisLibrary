package ai.ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Population {
	private Individual[] individuals;
	private int size;
	public static double chanceCrossover = 0.5;
	public static double chanceMutation = 0.005;
	public static int tournamentSize = 2;
	
	public Population(int size) {
		this.size = size;
		this.individuals = new Individual[size];
		for(int i = 0; i < this.size; i++) {
			individuals[i] = new Individual();
		}
	}
	public void calculateFitness() {
		for(Individual i : individuals) {
			i.calculateFitness();
		}
	}
	
	public void tournamentSelection() {
		ArrayList<Individual> tournament = new ArrayList<Individual>();	
		Individual[] chosen = new Individual[this.size];
		for(int index = 0; index < this.size; index++) {
			tournament.clear();
			for(int i = 0; i < tournamentSize; i++) {
				tournament.add(individuals[(int) (Math.random() * this.size)]);
			}
			Individual best = null;
			double bestFit = -Double.MAX_VALUE;
			for(Individual i : tournament) {
				if(i.fitness == -1) {
					i.calculateFitness();
				}
				if(i.fitness > bestFit) {
					bestFit = i.fitness;
					best = i;
				}
			}
			chosen[index] = best;
		}
		this.individuals = chosen;
	}
	
	public void crossover() {
		for(int i = 0; i < this.size; i+=2) {
			if(Math.random() < Population.chanceCrossover) {
				individuals[i].crossover(individuals[i+1]);
			}
		}
		
	}
	
	public void mutation() {
		for(int i = 0; i < this.size; i++) {
			if(Math.random() < Population.chanceMutation) {
				individuals[i].mutation();
			}
		}
	}
	
	public double averageFitness() {
		double sum = 0.0;
		for(Individual i : individuals) {
			sum += i.fitness;
		}
		return sum / size;
	}
	
	public Individual best() {
		double bestFit = -Double.MAX_VALUE;
		Individual best = null;
		for(Individual i : individuals) {
			double fitness = i.fitness;
			if(fitness > bestFit) {
				best = i;
				bestFit = fitness;
			}
		}
		return best;
	}
	
	public void clearFitness() {
		for(Individual i : this.individuals) {
			i.clearFitness();
		}
	}
	
	
	public String toString() {
		String ret = "";
		for(Individual i : individuals) {
			if(i != null) ret += Arrays.toString(i.dna) + " [" + i.fitness + "]\n";
			else {
				ret += "null\n";
			}
		}
		return ret + "\n" + "length: " + individuals.length;
	}
}
