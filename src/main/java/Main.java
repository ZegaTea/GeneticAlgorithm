import ga.GeneticAlgorithm;
import ga.Population;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).init();
//        System.out.println("----------------------------------");
//        printPopulation(population, "TARGET CHROMOSOME:\t" + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        int generationNumber = 0;
        while (population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
            generationNumber++;
            System.out.println("----------------------------------------------------------------------");
            population = geneticAlgorithm.evolve(population);
            population.sortChromosomesByFitness();
            System.out.println("Generation #" + generationNumber + "\t|\tFittest chromosome fitness:\t"
                    + population.getChromosomes()[0].getFitness());
            printPopulation(population, "TARGET CHROMOSOME:\t" + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
        }

    }

    public static void printPopulation(Population population, String heading) {
        System.out.println(heading);
        System.out.println("-----------------");
        for (int i = 0; i < population.getChromosomes().length; i++) {
            System.out.println("Chromosome\t#" + i + "\t:\t" + Arrays.toString(population.getChromosomes()[i].getGenes())
                    + "\t|\tFitness:\t" + population.getChromosomes()[i].getFitness());
        }
    }
}
