package ga;

public class GeneticAlgorithm {
    public static final int POPULATION_SIZE = 8;
    public static final int[] TARGET_CHROMOSOME = {1, 1, 1, 1, 0, 0, 1, 1, 1, 0};
    public static final int NUMB_OF_ELITE_CHROMOSOMES = 1;
    public static final int TOURNAMENT_SELECTION_SIZE = 4;
    private static final double MUTATION_RATE = 0.25;

    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population) {
        Population crossoverPopulation = new Population(population.getChromosomes().length);
        for (int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i++) {
            crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];

        }

        for (int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++) {
            Chromosome c1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome c2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossoverPopulation.getChromosomes()[i] = crossoverChromosome(c1, c2);

        }
        return crossoverPopulation;
    }

    private Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.getChromosomes().length);
        for (int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i++) {
            mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
        }

        for (int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++){
            mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
        }
        return mutatePopulation;
    }

    private Chromosome crossoverChromosome(Chromosome c1, Chromosome c2) {
        Chromosome crossoverChromosome = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length);
        for (int i = 0; i < crossoverChromosome.getGenes().length; i++) {
            if (Math.random() < 0.5) {
                crossoverChromosome.getGenes()[i] = c1.getGenes()[i];
            } else {
                crossoverChromosome.getGenes()[i] = c2.getGenes()[i];
            }
        }
        return crossoverChromosome;
    }

    private Chromosome mutateChromosome(Chromosome chromosome) {
        Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int i = 0; i < chromosome.getGenes().length; i++) {
            if (Math.random() < MUTATION_RATE) {
                if (Math.random() < 0.5) {
                    mutateChromosome.getGenes()[i] = 1;
                } else {
                    mutateChromosome.getGenes()[i] = 0;
                }
            } else {
                mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
            }
        }
        return mutateChromosome;
    }

    private Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
        for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
            tournamentPopulation.getChromosomes()[i] = population
                    .getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
        }
        tournamentPopulation.sortChromosomesByFitness();
        return tournamentPopulation;
    }
}
