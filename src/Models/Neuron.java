package Models;

import java.io.Serializable;

public class Neuron implements Serializable
{
	public double		Value;
	public double[]		Weights;
	public double		Bias;
	public double		Delta;
	
	public Neuron(int prevLayerSize)
	{
		Weights = new double[prevLayerSize];
		Bias = Math.random() / 10000000000000.0;
		Delta = Math.random() / 10000000000000.0;
		Value = Math.random() / 10000000000000.0;
		
		for(int i = 0; i < Weights.length; i++)
			Weights[i] = Math.random() / 10000000000000.0;
	}
}
