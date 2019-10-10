package Models;

public interface TransferFunction 
{
	/**
	 * Funci�n de transferencia
	 * @param value Valor de entrada
	 * @return Valor de la funci�n
	 */
	public double evalute(double value);
	
	
	/**
	 * Funci�n derivada
	 * @param value Valor de entrada
	 * @return Valor de la funcion derivada.
	 */
	public double evaluteDerivate(double value);
}
