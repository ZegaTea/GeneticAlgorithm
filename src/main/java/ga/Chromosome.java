package ga;

import java.util.Arrays;

public class Chromosome {
    private boolean isFitnessChange = true;
    private int[] genes;
    private int fitness = 0;

    public Chromosome(int length) {
        this.genes = new int[length];
    }

    public Chromosome init() {
        for (int x = 0; x < genes.length; x++) {
            if (Math.random() >= 0.5) genes[x] = 1;
            else genes[x] = 0;
        }
        return this;
    }

    public int recalculateFitness() {
        int chromosomeFitness = 0;
        for (int x = 0; x < genes.length; x++) {
            if (genes[x] == GeneticAlgorithm.TARGET_CHROMOSOME[x]) chromosomeFitness++;
        }
        return chromosomeFitness;
    }


    public String toString() {
        return Arrays.toString(this.genes);
    }


    public int getFitness() {
        if (isFitnessChange) {
            fitness = recalculateFitness();
        }
        return fitness;
    }

    public int[] getGenes() {
        isFitnessChange = true;
        return genes;
    }

}
