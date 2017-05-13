import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("all")
public class Main {
	public static void main(String[] args){
		Random rand = new Random();
		int max = 1;
		int min = 0;
		/*
		//==================================================================
		//								AND
		//==================================================================
		double[][] datos = {{-1,-1,-1}, {-1, 1, -1}, {1, -1, -1}, {1, 1, 1}};	

		double[] weights = new double[datos[0].length - 1];
			
		for(int i = 0; i < weights.length; i++){
				Random rand1 = new Random();
				weights[i] = rand1.nextDouble();
		}
		double umbral = min + rand.nextDouble() * max;
		
		Perceptron_Simple_01 perceptron = new Perceptron_Simple_01(weights, datos, umbral, (double) 0.1, (double)1);
		weights = perceptron.train();
		*/
		
		
		//==================================================================
		//								DATOS
		//==================================================================
		File file = new File("appendicitis.dat");
        Scanner inFile = null;
		try {
			inFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        int numberOfLines = 0;
        
        ArrayList<String> cadenas = new ArrayList<String>();
        while (inFile.hasNextLine()){
			numberOfLines++;
			cadenas.add(inFile.nextLine());
        }

        double[][] datos = new double[numberOfLines][cadenas.get(0).split(",").length];
        
        for(int i = 0; i < numberOfLines; i++){
        	datos[i] = processLine(cadenas.get(i));
        }
        
    	//==================================================================
		//								PESOS
		//==================================================================
        
        double[] weights = new double[datos[0].length - 1];
		
		for(int i = 0; i < weights.length; i++){
			Random rand1 = new Random();
			weights[i] = min + rand1.nextDouble() * max;
			//weights[i] = 1;
		}
		
		//==================================================================
		//							ENTRENAMIENTO
		//==================================================================
		Perceptron_Simple_01 perceptron = new Perceptron_Simple_01(weights, datos, (min + rand.nextDouble() * max), (double) 0.01, 1);
        perceptron.train();
        
        
	}


	/**
	 * 
	 * @param nextLine
	 * @return
	 */
	private static double[] processLine(String nextLine) {
		String[] datos = nextLine.split(",");
		double[] result = new double[datos.length];
		for(int i = 0; i < datos.length; i++){
			result[i] = Double.parseDouble(datos[i]);
		}
		return result;
	}
}
