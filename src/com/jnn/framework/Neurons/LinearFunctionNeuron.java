	/**
 * 
 */
package com.jnn.framework.Neurons;

import static com.jnn.framework.Constants.*;

/**
 * @author shabbirhussain
 *
 */
public class LinearFunctionNeuron extends GenericNeuron {
	// Default serialization ID
	private static final long serialVersionUID = 2L;

	/**
	 * Default Constructor
	 */
	public LinearFunctionNeuron() {
		super.myLearningRate = LINEAR_LEARNING_RATE;
	}

	/*
	 * Maps given value to a output through a neural function
	 * x: An input number
	 * Returns: A double number generated for given input
	 */
	protected static double thresholdFun(double x){
		return x;
	}

	/*
	 * Adjusts weights of current neuron to cope for error.
	 * errThisPat: Given error value to match for
	 */
	public Double adjustWeights(Double errThisPat){
		for(int i=0, l=myLastInput.length;i<l;i++){
			double weightChange = errThisPat * myLearningRate * myLastInput[i];
			double newWeight    = inputWeights[i] - weightChange;
			inputWeights[i] = Math.min(5, Math.max(-5, newWeight));
		}
		
		// Re calculate error 
		errThisPat += myLastOutput - fire(myLastInput);
		return errThisPat;
	}

}
