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

	/**
	 * Maps given value to a output through a neural function
	 * @param x An input number
	 * @return A double number generated for given input
	 */
	@Override
	protected double thresholdFun(double x) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Function used to trigger a neural response. 
	 * @param inputVector Input array of values
	 * @return An neural output from neuron
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
	
	/**
	 * Adjusts weights of current neuron to cope for error.
	 * @param errThisPat Error in this pattern that has to be adjusted
	 * @return Depreciated. No longer used
	 */
	public Double adjustWeights(Double errThisPat){
		for(int i=0, l=myLastInput.length;i<l;i++){
			double weightChange = errThisPat * myLearningRate * myLastInput[i];
			double newWeight    = inputWeights[i] + weightChange; 
			//inputWeights[i] = newWeight;
			inputWeights[i] = Math.min(5, Math.max(-5, newWeight));
			
		}
		// Re calculate error 
		errThisPat += myLastOutput - fire(myLastInput);
		return errThisPat;
	}

}
