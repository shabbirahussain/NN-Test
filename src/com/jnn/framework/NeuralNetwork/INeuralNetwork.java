/**
 * 
 */
package com.jnn.framework.NeuralNetwork;

import java.io.Serializable;

/**
 * @author shabbirhussain
 *
 */
public interface INeuralNetwork extends Serializable{
	/*
	 * Given an input array of fields 
	 * Returns: Neural output of network.
	 */
	public Double[] calculateOutput(Double inputVector[]);
	
	/*
	 * Trains the network with one pattern
	 * output     : Given output to compare
	 * inputVector: Given array of input values
	 */
	public void train(Double output, Double inputVector[]);

}
