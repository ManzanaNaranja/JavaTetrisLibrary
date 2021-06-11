package ai.geneticAlgo;

public class Population {
	Individual[] individuals;
	int popsize;
	
	public Population(int popsize) {
		this.popsize = popsize;
		this.individuals = new Individual[popsize];
		
		for(int i = 0; i < popsize; i++) {
			this.individuals[i] = new Individual();
		}
	}
	
	public void calculateFitness() {
		for(int i = 0; i < individuals.length; i++) {
//			individuals[i].calculateFitness();
			double sum = 0;
			for(int j = 0; j < individuals[i].genes.length; j++) {
				sum += 1 - individuals[i].genes[j];
			}
			individuals[i].fitness = sum;
		}
	}
	
	public double getAverage() {
		int a = 0;
		for(int i = 0; i < individuals.length; i++) {
			a += individuals[i].fitness;
		}
		return a / individuals.length;
	}
	
	public Individual getFittest() {
		double maxFit = Double.MIN_VALUE;
		Individual best = null;
		for(int i = 0; i < individuals.length; i++) {
			if(maxFit < individuals[i].fitness) {
				maxFit = individuals[i].fitness;
				best = individuals[i];
			}
		}
		return best;
	}
	
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].fitness > individuals[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].fitness > individuals[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }
}
