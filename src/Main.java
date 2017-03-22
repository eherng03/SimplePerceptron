import java.util.Random;

public class Main {
	public static void main(String[] args){
		//==================================================================
		//								DATOS
		//==================================================================
		int s = 4; //Numero de muestras
		int n = 2; //numero de coordenadas
		int[] weights = new int[n];
		for(int i = 0; i < n; i++){
			Random rand = new Random();
			weights[i] = rand.nextInt(1) + (-1);
		}
		//muestras[s][n]
		int[][] samples = {{0,0,0}, {0, 1, 0}, {1, 0, 0}, {1, 1, 1}};	

		Perceptron perceptron = new Perceptron(weights, samples, -1);
		weights = perceptron.train();
	}

}
