package ag;

import java.util.ArrayList;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

@SuppressWarnings("serial")
public class DiamondFitnessFunction extends FitnessFunction {

	private ArrayList<Diamond> diamantes;
	private ArrayList<String> criteriosClasif;

	public DiamondFitnessFunction(ArrayList<Diamond> diamantes, ArrayList<String> criteriosClasif) {
		super();
		this.diamantes = diamantes;
		this.criteriosClasif = criteriosClasif;
	}

	/*
	 * Criterios de valoracion de diamantes: Clarity: Nivel de pureza, a más puro
	 * (IF) mayor costo (Mejor) --> IF > VVS1 > VVS2 > VS1 > VS2 > SI12 > SI2 > I1
	 * --> (Peor) Color: Intensidad de color: A mayor transparencia (D) mayor costo
	 * (Mejor) --> D > E > F > G > H > I > J--> (Peor) Cut: Brilo, talla y destello:
	 * a mayor calidad de corte, mayor precio (Mejor) --> Ideal > Premium >
	 * Very_Good > Good > Fair --> (Peor) Carat: peso en quilates: 1 quilate = 0,20
	 * gr. A mayor quilate, mayor precio, debido a su rareza: Desde 0.2 hasta 5.01
	 * Los mejores diamantes serán escogidos en base al criterio seleccionado y al
	 * presupuesto que ingresa el usuario.
	 */

	@Override
	protected double evaluate(IChromosome ic) {
		double score = 0;
		for (int i = 0; i < ic.size(); i++) {
			int j = (Integer) ic.getGene(i).getAllele();
			Diamond diamond = diamantes.get(j);
			for (String criterios : criteriosClasif) {

				// criterio clarity
				if (criterios.equalsIgnoreCase(diamond.getClarity())) {
					score += rateDiamond(diamond.getClarity());
				}
				// criterio color
				if (criterios.equalsIgnoreCase(diamond.getColor())) {
					score += rateDiamond(diamond.getColor());
				}
				// criterio cut
				if (criterios.equalsIgnoreCase(diamond.getCut())) {
					score += rateDiamond(diamond.getCut());
				}
				// criterio carat y costo:
				if (criterios.matches("[0-9]+\\.*[0-9]*")) {
					double d = Double.parseDouble(criterios);
					// criterio carat
					if (d >= 0.2 && d <= 5.01) {
						if (diamond.getCarat() >= 0.2 && diamond.getCarat() <= 2) {
							score -= 1;
							// score +=2;
						} else if (diamond.getCarat() > 2 && diamond.getCarat() <= 3) {
							score += 0;
							// score +=1;
						} else {
							score += 1;
							// score +=0;
						}
					}
					// 326 es el costo mínimo del diamante más barato del dataset
					if (d >= 326) { // criterio costo, basado en el presupuesto ingresado
						if (d > diamond.getPrice()) {
							score += 1;
							// score+=1;
						} else {
							score -= 1;
							// score+=0;
						}

					}
				}
			}
		}
		// valida que el score sea positivo, caso contrario, retorna 0
		return score < 0 ? 0 : score;
	}

	// Score basado en los parámetros de calidad 4C
	private double rateDiamond(String criterios) {

		double score = 0;
		switch (criterios) {
		case "IF": // Lo mejores aummentan el score
		case "VVS1":
		case "D":
		case "Ideal":
		case "Premiun":
			score += 2;
			break;
		case "VVS2":
		case "VS1":
		case "E":
		case "F":
		case "Very-Good":
			score += 1;
			break;
		case "VS2":
		case "SI12":
		case "G":
		case "H":
		case "Good":
			score += 0;
			break;
		default: // los peores reducen el score
			score -= 1;
			break;
		}

		return score;
	}

}
