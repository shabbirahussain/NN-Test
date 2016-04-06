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
	 * Function used to trigger a neural response. 
	 * inputVector: An array of input triggers received by a neuron. inputVector can be of any arbitrary range allowed by java. Any missing weights are initialized automatically on the first call to defaults in class.
	 */
	public Double fire(Double inputVector[]){
		myLastInput = inputVector;
		
		if(inputVector.length > inputWeights.length)
			extendInitalizeWeights(inputVector.length);
		
		myLastOutput=0.0;
		for(int i=0, l=inputVector.length;i<l;i++) 
			myLastOutput += (inputVector[i] * inputWeights[i]);
		
		return myLastOutput;
	}
	
	/*
	 * Adjusts weights of current neuron to cope for error.
	 * errThisPat: Given error value to match for
	 */
	public Double adjustWeights(Double errThisPat){
		for(int i=0, l=myLastInput.length;i<l;i++){
			double weightChange = errThisPat * myLearningRate * myLastInput[i];
			double newWeight    = inputWeights[i] + weightChange; 
			inputWeights[i] = Math.min(5, Math.max(-5, newWeight));
			
		}
		// Re calculate error 
		errThisPat += myLastOutput - fire(myLastInput);
		return errThisPat;
	}

}
