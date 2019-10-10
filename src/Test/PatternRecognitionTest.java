package Test;


import Models.MultiLayerPerceptron;
import Functions.SigmoidalTransfer;
import Utils.ImageProcessingBW;


/** Reconocimiento de patrones de personajes (OCR) */
public class PatternRecognitionTest
{
	
	private static String directory = System.getProperty("user.dir");

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		TestPrecision(50);
	
	}
	
	public static double TestPrecision(int maxit)
	{
		
		int size = 32;
		double error = 0.0;
		int nimagesxpatt = 89;
		int npatt = 36;
		
		int[] layers = new int[]{ size*size, size, npatt };
		
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.6, new SigmoidalTransfer());
		
		/* Aprendizaje */
		for(int i = 0; i < maxit; i++)
		{
			for(int k = 1; k < nimagesxpatt; k++)
			{
				for(int j = 1; j < npatt+1; j++)
				{		
					String pattern = directory+"\\img\\patterns\\"+j+"\\"+k+".png";
					double[] inputs = ImageProcessingBW.loadImage(pattern, size, size);
					
					if(inputs == null)
					{
						System.out.println("Cant find "+pattern);
						continue;
						
					}
					double[] output = new double[npatt];

					for(int l = 0; l < npatt; l++)
						output[l] = 0.0;
					
					output[j-1] = 1.0;
					
					
					// Entrenamiento
					error = net.backPropagate(inputs, output);
					System.out.println("Error is "+error+" ("+i+" "+j+" "+k+" )");					
				}
			}
		}
		
		System.out.println("Learning completed!");
		
		/* Test */
		int correct = 0;
		
	
		double[] inputs = ImageProcessingBW.loadImage(directory+"\\img\\test.png", size, size);
		double[] output = net.execute(inputs);

		int max = 0;
		for(int i = 0; i < npatt; i++)
			if(output[i] > output[max])
			{
				max = i;
			}
		
		System.out.println("Il valore massimo e' "+output[max]+" pattern "+(max+1));
		
		return (double) ((double) (npatt*nimagesxpatt) - (double) correct) / (double) (npatt*nimagesxpatt);
	}
}
