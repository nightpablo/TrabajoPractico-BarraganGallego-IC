package Models;

public interface TransferFunction 
{
	/**
	 * Función de transferencia
	 * @param value Valor de entrada
	 * @return Valor de la función
	 */
	public double evalute(double value);
	
	
	/**
	 * Función derivada
	 * @param value Valor de entrada
	 * @return Valor de la funcion derivada.
	 */
	public double evaluteDerivate(double value);
}
