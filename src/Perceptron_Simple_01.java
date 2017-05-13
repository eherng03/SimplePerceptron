import java.util.ArrayList;

public class Perceptron_Simple_01 {
	
	private final int MAXITER = 106*40;
	private final double ALFA = (double) 0.005;
	
	private double[] weights;
	private double[][] samples;
	private double umbral;
	private double gamma;
	private int control;
	private int time;
	private double errorAceptable;
	
	public Perceptron_Simple_01(double[] weights, double[][] samples, double umbral, double errorAceptable, double gamma){
		this.weights = weights;
		this.samples = samples;
		this.umbral = umbral;
		this.gamma = gamma;
		this.errorAceptable = errorAceptable;
		control = 0;
		time = 0;
		
	}


	public double[] train() {
		
		
		while(control < samples.length){
			double totalError = 0;
			double errorI = 0;
			double errorSinValorAbs = 0;
			int falsoPos = 0;
			int falsoNeg = 0;
			System.out.println("Pesos antes del entrenamiento:");
			for(int i = 0; i < weights.length; i++){
				System.out.println("W[" + i + "] = " + weights[i]);
			}
			calculateGamma(time);
			for(int i = 0; i < samples.length; i++){
				
				double output = calculateOutput(i);
				double esperada = samples[i][samples[0].length - 1];
				
				if(output != esperada){
					//=============================================================
					//System.out.print("La muestra " + i + " está mal entrenada.");
					if(output == 1 && esperada == 0){
						//System.out.println(" Es un falso positivo.");
						falsoPos++;
					}
					if(output == 0 && esperada == 1){
						//System.out.println(" Es un falso negativo.");
						falsoNeg++;
					}
					//=============================================================
					
					errorSinValorAbs = esperada - output;
					errorI = Math.abs(errorSinValorAbs);
					totalError = totalError + errorI;
					
					//=============================================================
					//Intervalo [-1, 1]
					//recalcularPesos(esperada, i);
					
					//Intervalo [0, 1]
					recalcularPesos(errorSinValorAbs, i);
					//=============================================================
				}else{ 
					control++;
					//System.out.println("La muestra " + i + " está bien entrenada.");
				}
				time++;
				
				if(time == MAXITER){
					control = samples.length;
					break;
				}
			}
			/*
			totalError = Math.abs(totalError / (2*(samples.length)));
			System.out.println("Han pasado " + time + " segundos. Hay un error de " + totalError);
			*/
			totalError = Math.abs(totalError / (samples.length));
			System.out.println("Han pasado " + time + " segundos. Hay un error de " + totalError);
			System.out.println("Hay " + falsoNeg + " falsos negativos.");
			System.out.println("Hay " + falsoPos + " falsos positivos.");
			System.out.println("Hay " + (falsoNeg+falsoPos) +  " muestras mal entrenadas.");
			System.out.println("Hay " + (samples.length - (falsoNeg+falsoPos)) +  " muestras bien entrenadas.");
			System.out.println("-----------------------------------------------------------------");
			
			
			
			//con error aceptable como criterio de parada
			
			if((totalError < errorAceptable) && time != 0)
				break;
				
				
		}
		System.out.println("Pesos finales:");
		for(int i = 0; i < weights.length; i++){
			System.out.println("W[" + i + "] = " + weights[i]);
		}
		return weights;
	}
	
	private void recalcularPesos(double errorI, int i) {
		for(int j = 0; j < samples[0].length - 1; j++){
			double incremento = (gamma * errorI * samples[i][j]);
			weights[j] += incremento;
		}
		umbral = umbral + (gamma * errorI * (-1));
		control = 0;
	}


	public double calculateOutput(int sample){
		double potential = 0;
		for(int i = 0; i < weights.length; i++){
			potential = potential + (weights[i] * samples[sample][i]);
		}
		potential = potential - umbral;
		if(potential >= 0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	public void calculateGamma(int time){
		//double c = 5;
		double c = (double)MAXITER * (1/(double)4);			//JUGAR CON LA FRACCION
		double b = (double)Math.exp(-ALFA * ((double)time - c));
		double a = ((double)-1) / ((double)1 + b);
		gamma =  a + (double)1;
		System.out.println("La razón de aprendizaje es: " +  gamma);
	}


}
