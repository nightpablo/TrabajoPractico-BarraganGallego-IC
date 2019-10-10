package Models;

public class Layer 
{
	public Neuron 	Neurons[];
	public int 		Length;
	
	/**
	 * Capa de neuronas
	 * 
	 * @param l Tama�o de la capa
	 * @param prev Tama�o de la capa anterior.
	 */
	public Layer(int l, int prev)
	{
		Length = l;
		Neurons = new Neuron[l];
		
		for(int j = 0; j < Length; j++)
			Neurons[j] = new Neuron(prev);
	}
}
