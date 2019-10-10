package Models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MultiLayerPerceptron implements Cloneable
{
	protected double			fLearningRate = 0.6;
	protected Layer[]			fLayers;
	protected TransferFunction 	fTransferFunction;

	
	/**
	 * Crea una red neuronal MLP
	 * 
	 * @param layers Número de neuronas para cada capa.
	 * @param learningRate Aprendizaje constante
	 * @param fun Función de transferencia
	 */
	public MultiLayerPerceptron(int[] layers, double learningRate, TransferFunction fun)
	{
		fLearningRate = learningRate;
		fTransferFunction = fun;
		
		fLayers = new Layer[layers.length];
		
		for(int i = 0; i < layers.length; i++)
		{			
			if(i != 0)
			{
				fLayers[i] = new Layer(layers[i], layers[i - 1]);
			}
			else
			{
				fLayers[i] = new Layer(layers[i], 0);
			}
		}
	}
	

	
	/**
	 * Correr la red
	 * 
	 * @param input Valores de entrada
	 * @return Valores de salida devueltos por la red
	 */
	public double[] execute(double[] input)
	{
		int i;
		int j;
		int k;
		double new_value;
		
		double output[] = new double[fLayers[fLayers.length - 1].Length];
		
		// Inserta la entrada
		for(i = 0; i < fLayers[0].Length; i++)
		{
			fLayers[0].Neurons[i].Value = input[i];
		}
		
		// Ejecucción - Oculta + Salida
		for(k = 1; k < fLayers.length; k++)
		{
			for(i = 0; i < fLayers[k].Length; i++)
			{
				new_value = 0.0;
				for(j = 0; j < fLayers[k - 1].Length; j++)
					new_value += fLayers[k].Neurons[i].Weights[j] * fLayers[k - 1].Neurons[j].Value;
				
				new_value += fLayers[k].Neurons[i].Bias;
				
				fLayers[k].Neurons[i].Value = fTransferFunction.evalute(new_value);
			}
		}
		
		
		// Retornar Salida
		for(i = 0; i < fLayers[fLayers.length - 1].Length; i++)
		{
			output[i] = fLayers[fLayers.length - 1].Neurons[i].Value;
		}
		
		return output;
	}
	
	
	
	/**
	 * Algoritmo de retropropagación para aprendizaje asistido
	 * (Versión de hilos múltiples)
	 * 
	 * Convergencia no segura y muy lenta; use como criterio de detención una norma 
	 * entre los errores anteriores y actuales, y un número máximo de iteraciones.
	 * 
	 * Wikipedia:
	 * 	Los datos de entrenamiento se dividen en lotes igualmente grandes para cada uno de los hilos. 
	 *  Cada hilo ejecuta las propagaciones hacia adelante y hacia atrás. Los deltas de peso y umbral 
	 *  se suman para cada uno de los hilos. Al final de cada iteración, todos los hilos deben pausarse 
	 *  brevemente para que los deltas de peso y umbral se sumen y apliquen a la red neuronal. 
	 *  Este proceso continúa para cada iteración.
	 * 
	 * @param input Valores de entrada
	 * @param output Valores de salida esperados
	 * @param nthread Número de hilos para generar para aprender
	 * @return Error delta entre salida generada y salida esperada
	 */
	public double backPropagateMultiThread(double[] input, double[] output, int nthread)
	{
		return 0.0;
	}

	
	
	/**
	 * Algoritmo de retropropagación para aprendizaje asistido
	 * (Versión de hilo único)
	 * 
	 * Sin convergencia y muy lenta convergencia; use como 
	 * criterio de detención una norma entre los errores 
	 * anteriores y actuales, y un número máximo de iteraciones.
	 * 
	 * @param input Valores de entrada (escalados entre 0 y 1)
	 * @param output Valores de salida esperados (escalados entre 0 y 1)
	 * @return Error delta entre salida generada y salida esperada
	 */
	public double backPropagate(double[] input, double[] output)
	{
		double new_output[] = execute(input);
		double error;
		int i;
		int j;
		int k;
		
		/* doutput = salida correcta (output) */
		
		// Calculamos el error de la salida
		for(i = 0; i < fLayers[fLayers.length - 1].Length; i++)
		{
			error = output[i] - new_output[i];
			fLayers[fLayers.length - 1].Neurons[i].Delta = error * fTransferFunction.evaluteDerivate(new_output[i]);
		} 
	
		
		for(k = fLayers.length - 2; k >= 0; k--)
		{
			// Calcule el error de la capa actual y recalcule los deltas

			for(i = 0; i < fLayers[k].Length; i++)
			{
				error = 0.0;
				for(j = 0; j < fLayers[k + 1].Length; j++)
					error += fLayers[k + 1].Neurons[j].Delta * fLayers[k + 1].Neurons[j].Weights[i];
								
				fLayers[k].Neurons[i].Delta = error * fTransferFunction.evaluteDerivate(fLayers[k].Neurons[i].Value);				
			}
			
			// Actualiza los pesos de la siguiente capa
			for(i = 0; i < fLayers[k + 1].Length; i++)
			{
				for(j = 0; j < fLayers[k].Length; j++)
					fLayers[k + 1].Neurons[i].Weights[j] += fLearningRate * fLayers[k + 1].Neurons[i].Delta * 
							fLayers[k].Neurons[j].Value;
				fLayers[k + 1].Neurons[i].Bias += fLearningRate * fLayers[k + 1].Neurons[i].Delta;
			}
		}	
		
		// Calculamos el error
		error = 0.0;
		
		for(i = 0; i < output.length; i++)
		{
			error += Math.abs(new_output[i] - output[i]);
			
			//System.out.println(output[i]+" "+new_output[i]);
		}

		error = error / output.length;
		return error;
	}
	
	
	/**
	 * Guarda una red MLP en archivo
	 * 
	 * @param path Direccion en donde guardar la red MLP
	 * @return true Si se guardó correctamente
	 */
	public boolean save(String path)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
		}
		catch (Exception e) 
		{ 
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Subir una red MLP desde el archivo
	 * @param path Direccion en donde cargar la red MLP
	 * @return Red MLP cargada desde el archivo o nula
	 */
	public static MultiLayerPerceptron load(String path)
	{
		try
		{
			MultiLayerPerceptron net;
			
			FileInputStream fin = new FileInputStream(path);
			ObjectInputStream oos = new ObjectInputStream(fin);
			net = (MultiLayerPerceptron) oos.readObject();
			oos.close();
			
			return net;
		}
		catch (Exception e) 
		{ 
			return null;
		}
	}
	
	

	/**
	 * @return Aprendizaje constante
	 */
	public double getLearningRate()
	{
		return fLearningRate;
	}
	
	
	/**
	 * 
	 * @param ratio
	 */
	public void	setLearningRate(double rate)
	{
		fLearningRate = rate;
	}
	
	
	/**
	 * Configurar una nueva función de transferencia
	 * 
	 * @param fun Función de transferencia
	 */
	public void setTransferFunction(TransferFunction fun)
	{
		fTransferFunction = fun;
	}
	
	
	
	/**
	 * @return Tamaño de capa de entrada
	 */
	public int getInputLayerSize()
	{
		return fLayers[0].Length;
	}
	
	
	/**
	 * @return Tamaño de capa de salida
	 */
	public int getOutputLayerSize()
	{
		return fLayers[fLayers.length - 1].Length;
	}
}

