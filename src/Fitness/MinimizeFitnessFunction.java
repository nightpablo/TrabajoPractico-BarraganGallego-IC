package Fitness;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class MinimizeFitnessFunction extends FitnessFunction {
	
	public double objetivo;
	
	public MinimizeFitnessFunction(double objetivo) {
		this.objetivo = objetivo;
	}

	@Override
	protected double evaluate(IChromosome a_subject) {
		return 0.0
			;
	}
}
