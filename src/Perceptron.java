
public class Perceptron {
	
	private int[] weights;
	private int[][] samples;
	private int umbral;
	private int gamma;
	private int control;
	private int time;
	
	public Perceptron(int[] weights, int[][] samples, int umbral){
		this.weights = weights;
		this.samples = samples;
		this.umbral = umbral;
		gamma = 1;
		control = 0;
	}

	public int[] train() {
		while(control != samples.length){
			if(time == 20)
				break;
			for(int i = 0; i < samples.length; i++){
				int output = calculateOutput(i);
				int esperada = samples[i][samples[0].length - 1];
				if(output != esperada){
					for(int j = 0; j < samples[0].length - 1; j++){
						weights[j] += gamma * samples[i][samples[0].length - 1] * samples[i][j];
					}
					umbral += gamma * samples[i][samples[0].length - 1] * (-1);
					control = 0;
				}else{ control++;	}
			}
			time++;
		}
		return weights;
	}
	
	public int calculateOutput(int sample){
		int potential = 0;
		for(int i = 0; i < weights.length; i++){
			potential += weights[i]*samples[sample][i];
		}
		potential = potential + umbral;
		if(potential >= 0){
			return 1;
		}else{
			return 0;
		}
	}


}
