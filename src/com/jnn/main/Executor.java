/**
 * 
 */
package com.jnn.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import static com.jnn.framework.Constants.*;

import com.jnn.framework.NeuralNetwork.GenericNeuralNetwork;
import com.jnn.framework.NeuralNetwork.NeuralNetwork;

/**
 * @author shabbirhussain
 *
 */
public final class Executor {
	private static NeuralNetwork network;  // Stores the neural network

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		if(CREATE_NEW_NETWORK)
			createNewNetwork();
		else
			loadOrCreateNetwork(FULL_NW_STORAGE_PATH);
		
		
		trainOnInputs(TRAINING_FILE_LOC, ERROR_TOLLERANCE, NUM_TRAINING_CYLCE);
		saveNetwork(FULL_NW_STORAGE_PATH);
		
		testNetworkOn(TESTING_FILE_LOC, ERROR_TOLLERANCE);
	}

	/**
	 * Runs the Neural Network on given test file
	 * @param dataFileFullPath: Full path of data file to test
	 * @throws IOException: Exceptions while accessing file
	 * @throws ArrayIndexOutOfBoundsException: Input CSV should have at least 2 fields
	 */
	private static void testNetworkOn(String dataFileFullPath, Integer errorTolerance)  throws IOException, ArrayIndexOutOfBoundsException{
		BufferedReader br;
		String line, fields[];
		Double expectedOutput, inputVector[];
		System.out.println("\nTesting Network...\n");
		
		br = new BufferedReader(new FileReader(dataFileFullPath));
		while ((line = br.readLine()) != null) {
			// use comma as separator
			fields = line.split(FILE_DELIMITER);
			
			// Read expected output from file
			expectedOutput = Double.parseDouble(fields[0]);
			System.out.print("Expected= " + expectedOutput );
			
			inputVector = new Double[fields.length];
			for(int i=1, l=fields.length; i<l; i++)
				inputVector[i-1] = Double.parseDouble(fields[i]);
			
			inputVector[fields.length-1] = 1.0; // Add bias input
			
			// Calculate output from Neural network
			Double actualOutput = network.calculateOutput(inputVector)[0];
			
			// Display results
			BigDecimal bd = new BigDecimal(Double.toString(actualOutput));
			bd = bd.setScale(errorTolerance, BigDecimal.ROUND_HALF_UP);
			System.out.print("\tRnd(Actual)= " + bd + "\tActual= " + actualOutput + "\n");
		}
		br.close();
	}
	
	/**
	 * Trains network on given input file. Where input file is a CSV with first field consists of output value and rest of fields define attributes to train in HEX
	 * @param dataFileFullPath: File input stream for requested training session
	 * @param numOfCycles: Number of training cycles
	 * @throws IOException: Exceptions while accessing file
	 * @throws ArrayIndexOutOfBoundsException: Input CSV should have at least 2 fields
	 */
	public static void trainOnInputs(String dataFileFullPath, Integer errorTolerance, Integer numOfCycles) throws IOException, ArrayIndexOutOfBoundsException{
		BufferedReader br;
		String line, fields[];
		Double expectedOutput, inputVector[];
		System.out.println("\nTraining Network...\n");
		
		for(int c=0; c<numOfCycles;){
			br = new BufferedReader(new FileReader(dataFileFullPath));
			while ((line = br.readLine()) != null) {
				c++;
				// use comma as separator
				fields = line.split(FILE_DELIMITER);
				
				expectedOutput = Double.parseDouble(fields[0]);
				
				inputVector = new Double[fields.length];
				for(int i=1, l=fields.length; i<l; i++)
					inputVector[i-1] = Double.parseDouble(fields[i]);
				
				inputVector[fields.length-1] = 1.0; // Add bias input
				
				network.train(expectedOutput, errorTolerance, inputVector);
			}
			br.close();
		}
	}
	
	/**
	 * Saves the network to a file in serialized object format
	 * @param fullFilePath: Takes fully qualified file path
	 * @throws IOException: Exceptions while accessing file
	 */
	public static void saveNetwork(String fullFilePath) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullFilePath));
		oos.writeObject(network);
		oos.close();
	}
	
	/**
	 * Loads a Neural network or if none exists creates one and saves it to the filessystem.
	 * @param fullFilePath: Takes fully qualified file path
	 * @throws IOException: Exceptions while accessing file
	 * @throws ClassNotFoundException: Unable to load object class is obsolete
	 */
	public static void loadOrCreateNetwork(String fullFilePath) throws IOException, ClassNotFoundException{
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullFilePath));
			network = (GenericNeuralNetwork) ois.readObject();
			ois.close();
		}catch(FileNotFoundException e){
			createNewNetwork();
		}
	}
	
	/**
	 * Creates a new neutal network to train
	 */
	public static void createNewNetwork(){
		network = new GenericNeuralNetwork(NUM_HIDDEN_LAYERS, NUM_HIDDEN_NEURONS);
	}
}
