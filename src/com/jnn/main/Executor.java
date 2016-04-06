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
import static com.jnn.framework.Constants.*;

import com.jnn.framework.NeuralNetwork.GenericNeuralNetwork;
import com.jnn.framework.NeuralNetwork.INeuralNetwork;

/**
 * @author shabbirhussain
 *
 */
public final class Executor {
	private static INeuralNetwork network;  // Stores the neural network

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		loadOrCreateNetwork(FULL_NW_STORAGE_PATH);
		trainOnInputs(TRAINING_FILE_LOC, NUM_TRAINING_CYLCE);
		saveNetwork(FULL_NW_STORAGE_PATH);
	}

	/*
	 * Trains network on given input file. Where input file is a CSV with first field consists of output value and rest of fields define attributes to train in HEX
	 * dataFileFullPath: File input stream for requested training session
	 * numOfCycles: Number of training cycles
	 */
	public static void trainOnInputs(String dataFileFullPath, Integer numOfCycles) throws IOException, ArrayIndexOutOfBoundsException{
		BufferedReader br;
		String line, fields[];
		Double output, inputVector[];
		
		for(int c=0; c<numOfCycles; c++){
			br = new BufferedReader(new FileReader(dataFileFullPath));
			while ((line = br.readLine()) != null) {
				c++;
				// use comma as separator
				fields = line.split(FILE_DELIMITER);
				output = Double.parseDouble(fields[0]);
				
				inputVector = new Double[fields.length - 1];
				for(int i=1, l=fields.length; i<l; i++)
					inputVector[i] = Double.parseDouble(fields[i]);
				
				network.train(output, inputVector);
			}
			br.close();
		}
	}
	
	/*
	 * Saves the network to a file in serialized object format
	 * fullFilePath: Takes fully qualified file path
	 */
	private static void saveNetwork(String fullFilePath) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullFilePath));
		oos.writeObject(network);
		oos.close();
	}
	
	/*
	 * Loads a Neural network or if none exists creates one and saves it to the filessystem.
	 * fullFilePath: Takes fully qualified file path
	 */
	private static void loadOrCreateNetwork(String fullFilePath) throws IOException, ClassNotFoundException{
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullFilePath));
			network = (INeuralNetwork) ois.readObject();
			ois.close();
		}catch(FileNotFoundException e){
			network = new GenericNeuralNetwork(NUM_HIDDEN_LAYERS, NUM_HIDDEN_NEURONS);
			saveNetwork(fullFilePath);
		}
	}
}
