package ag;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class DiamondOptimizacion {

	private Configuration conf;
	private DiamondFitnessFunction diamondFF;
	private Chromosome diamCcrom;
	private ArrayList<Diamond> diamantes = new ArrayList<>();
//	private ArrayList<String> criteriosClasif = new ArrayList<>();

	private static final int MAX_ALLOWED_EVOLUTIONS = 50;
	private static final int MAX_EVOLVE = 10;

	public void inicializar(int poblacion, ArrayList<String> criteria, int numD, JTextArea jt) {

		// numD: numero de los mjores diamantes que requiere el usuario.
		// poblacion: num de inidividuos para evolución
		// criteria: criterios para determinar los numD mejores diamantes

		try {
			// Algoritmo Genetico
			conf = new DefaultConfiguration();
			conf.setPopulationSize(poblacion);
			diamantes = loadData("src/files/DiamondAG.csv"); // lista de diamantes
			diamondFF = new DiamondFitnessFunction(diamantes, criteria);
			conf.setFitnessFunction(diamondFF);

			// Arreglo de Genes
			// ¿Cuantos diamantes quiere que te muestre?
			Gene[] diamondGenES = new Gene[numD];
			for (int i = 0; i < diamondGenES.length; i++) {
				// Obtiene un indice de los mejore diamante
				diamondGenES[i] = new IntegerGene(conf, 0, diamantes.size() - 1);
				diamondGenES[i].setAllele(i); // ponerle un valor como tal al cromiosoma.
			}
			diamCcrom = new Chromosome(conf, diamondGenES);
			conf.setSampleChromosome(diamCcrom);
			testSelection(jt);

		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}

	}

	public void testSelection(JTextArea jt) {
		try {
			Genotype population = Genotype.randomInitialGenotype(conf);
			// Mejor solucion
			IChromosome mejorSolucion = diamCcrom;
			for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
				population.evolve(MAX_EVOLVE);
				IChromosome mejorCandidato = population.getFittestChromosome();
				/*
				 * En un punto de la lista, se puede caer en un minimo global, Es decir, en una
				 * de las evoluciones ya se encontró al mejor y al seguir evolucionándolo, puede
				 * cambiar su comportamiento y los individuos se pierden.
				 */
				if (mejorCandidato.getFitnessValue() > mejorSolucion.getFitnessValue()) {
					mejorSolucion = mejorCandidato;
				}
			}
			printSolution(mejorSolucion, jt);
			Configuration.reset();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}

	}

	private void printSolution(IChromosome bestSolution, JTextArea jt) {
		jt.append("*=========================================*\n");
		jt.append("Fitness Value: " + bestSolution.getFitnessValue());
		jt.append("\nTotal diamantes analizados: "+diamantes.size()+"\n");
		// recorrer c/u de los genes del cromosoma
		for (int i = 0; i < bestSolution.size(); i++) {
			/*
			 * Cada gen tiene un índice que indica la posición de los numD mejores diamantes
			 */
			int j = (Integer) bestSolution.getGene(i).getAllele();
			jt.append(diamantes.get(j).toString()+"\n");
		}
		jt.append("*=========================================*\n");
	}

	public ArrayList<Diamond> loadData(String file) {

		ArrayList<Diamond> data = new ArrayList<Diamond>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				String[] a = line.split(";");
				// Desde la posicon 3 son solo de tipo Double:
				Double[] b = new Double[a.length - 3];
				for (int i = 3; i < a.length; i++) {
					// convertir string a double en el mismo array
					b[i - 3] = Double.parseDouble(a[i]);
				}
				data.add(new Diamond(a[0], a[1], a[2], b[0], b[1], b[2], b[3], b[4], b[5], b[6]));
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

}
