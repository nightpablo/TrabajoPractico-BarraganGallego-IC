package Test;


import Models.MultiLayerPerceptron;

import java.io.File;

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
//		withoutLearning();
	}
	
	
	private static void withoutLearning() {
		int size = 32;
		int npatt = 36;
		File[] characterFolder = new File(directory+"\\img\\patterns").listFiles();
		MultiLayerPerceptron net = MultiLayerPerceptron.load(directory+"\\resources\\red.txt");
		if(net == null) { System.out.println("Problemas con la red"); return;}
		
		double[] inputs = ImageProcessingBW.loadImage(directory+"\\img\\patterns\\N\\18.png", size, size);
		double[] output = net.execute(inputs);

		int max = 0;
		for(int i = 0; i < npatt; i++)
			if(output[i] > output[max])
			{
				max = i;
			}
		
		System.out.println("El valor máximo es: "+String.format ("%.2f", (float)output[max] * 100)+"%. El caracter de la imagen corresponde a: "+characterFolder[max].getName());
	}


	public static void TestPrecision(int maxit)
	{
		
		int size = 32;
		double error = 0.0;
		int nimagesxpatt = 89;
		int npatt = 36;
		
		int[] layers = new int[]{ size*size, size, npatt };
		
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.6, new SigmoidalTransfer());
		
		File[] characterFolder = new File(directory+"\\img\\patterns").listFiles();
		
		/* Aprendizaje */
		for(int i = 0; i < maxit; i++)
		{
			for(int k = 1; k < nimagesxpatt; k++)
			{
				for(int j = 1; j < npatt+1; j++)
				{		
					String pattern = directory+"\\img\\patterns\\"+characterFolder[j-1].getName()+"\\"+k+".png";
					double[] inputs = ImageProcessingBW.loadImage(pattern, size, size);
					
					if(inputs == null)
					{
						System.out.println("No se encuentra el fichero "+pattern);
						continue;
						
					}
					double[] output = new double[npatt];

					for(int l = 0; l < npatt; l++)
						output[l] = 0.0;
					
					output[j-1] = 1.0;
					
					
					// Entrenamiento
					error = net.backPropagate(inputs, output);
					System.out.println("El error del caracter "+characterFolder[j-1].getName()+" es: "+String.format ("%.2f", (float)error * 100)+"% ("+i+" "+j+" "+k+")");
				}
			}
		}
		
		System.out.println("¡Aprendizaje Completada!");
		
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
		
		System.out.println("El valor máximo es: "+String.format ("%.2f", (float)output[max] * 100)+"%. El caracter de la imagen corresponde a: "+characterFolder[max].getName());
		
		
		net.save(directory+"\\resources\\red.txt");
		
	}
}
