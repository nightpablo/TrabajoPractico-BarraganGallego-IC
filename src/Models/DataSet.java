package Models;

public class DataSet {
	private double[][] inputs;
	private String[] labelDigits;
	private int contador;
	
	public DataSet(int height, int weigth, int inputCount) {
		inputs = new double[inputCount][height*weigth];
		labelDigits = new String[inputCount];
		contador = 0;
	}
	
	public double[][] getInputs() {
		return inputs;
	}
	public void setInputs(double[][] inputs) {
		this.inputs = inputs;
	}
	public String[] getLabelDigits() {
		return labelDigits;
	}
	public void setLabelDigits(String[] labelDigits) {
		this.labelDigits = labelDigits;
	}
	
	public void loadData(double[] input, String labelDigit) {
		inputs[contador] = input;
		labelDigits[contador] = labelDigit;
		contador++;
	}
	public double[] getDataIndex(int index) {
		return inputs[index];
	}
}
