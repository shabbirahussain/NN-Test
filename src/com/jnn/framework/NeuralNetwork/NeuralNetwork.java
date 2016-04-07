/**
 * 
 */
package com.jnn.framework.NeuralNetwork;

import java.io.Serializable;

/**
 * @author shabbirhussain
 *
 */
public interface NeuralNetwork extends Serializable{
	/**
	 * Given an input array of fields 
	 * @param inputVector Input array of values
	 * @return An array of output from network
	 */
	public Double[] calculateOutput(Double inputVector[]);
	
	/**
	 * Trains the network with one pattern
	 * @param expectedOutput Expected Output of the neural network
	 * @param errorTolerance Error tolerance for training in number of decimal places
	 * @param inputVector Input array of values
	 */
	public void train(Double expectedOutput, Integer errorTolerance, Double inputVector[]);

}
