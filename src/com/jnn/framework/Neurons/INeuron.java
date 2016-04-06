/**
 * 
 */
package com.jnn.framework.Neurons;

import java.io.Serializable;

/**
 * @author shabbirhussain
 * An generic interface for a neuron
 */
public interface INeuron extends Serializable{
	
	/*
	 * Function used to trigger a neural response. 
	 * inputVector: An array of input triggers received by a neuron. inputVector can be of any arbitrary range allowed by java. Any missing weights are initialized automatically on the first call to defaults in class.
	 */
	public Double fire(Double inputVector[]);
	
	/*
	 * Adjusts weights of current neuron to cope for error.
	 * errThisPat: Given error value to match for
	 */
	public Double adjustWeights(Double errThisPat);
}
