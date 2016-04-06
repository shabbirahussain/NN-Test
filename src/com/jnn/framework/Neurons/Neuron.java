/**
 * 
 */
package com.jnn.framework.Neurons;

import java.io.Serializable;

/**
 * @author shabbirhussain
 * An generic interface for a neuron
 */
public interface Neuron extends Serializable{
	
	/**
	 * Function used to trigger a neural response. 
	 * @param inputVector: Input array of values
	 * @return An neural output from neuron
	 */
	Double fire(Double inputVector[]);
	
	/**
	 * Adjusts weights of current neuron to cope for error.
	 * @param errThisPat: Error in this pattern that has to be adjusted
	 * @return Depreciated. No longer used
	 */
	Double adjustWeights(Double errThisPat);
}
