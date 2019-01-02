package ga;

import java.util.Arrays;

public class Population {
    private Chromosome[] chromosomes;

    public Population(int length) {
        this.chromosomes = new Chromosome[length];
    }

    public Population init() {
        for (int x = 0; x < chromosomes.length; x++) {
            chromosomes[x] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).init();
        }
        sortChromosomesByFitness();
        return this;
    }

    public void sortChromosomesByFitness() {
        Arrays.sort(chromosomes, (c1, c2) -> {
            int flag = 0;
            if (c1.getFitness() > c2.getFitness()) flag = -1;
            else if (c1.getFitness() < c2.getFitness()) flag = 1;
            return flag;
        });
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }
}
