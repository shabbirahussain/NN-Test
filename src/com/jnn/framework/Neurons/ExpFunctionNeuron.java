package com.jnn.framework.Neurons;

import static com.jnn.framework.Constants.LEARNING_RATE;

public class ExpFunctionNeuron extends GenericNeuron{

	// Serialization version ID
	private static final long serialVersionUID = 3L;

	/**
	 * Default constructor
	 */
	public ExpFunctionNeuron(){
		super.myLearningRate = LEARNING_RATE;
	}
	
	/**
	 * Maps given value to a output through a neural function
	 * @param x An input number
	 * @return A double number generated for given input
	 */
	@Override
	protected double thresholdFun(double x){
		return Math.tan(x);
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
		
		myLastOutput = thresholdFun(myLastOutput);
		return myLastOutput;
	}
	
	/**
	 * Adjusts weights of current neuron to cope for error.
	 * @param errThisPat Error in this pattern that has to be adjusted
	 * @return Depreciated. No longer used
	 */
	public Double adjustWeights(Double errThisPat){
		double x = 1 - (myLastOutput * myLastOutput);
		//System.out.print(errThisPat +"\t EO = " + myLastOutput + " \t");
		
		for(int i=0, l=myLastInput.length;i<l;i++){
			double weightChange = x;
			weightChange    *= inputWeights[i] * errThisPat * myLearningRate * myLastInput[i];
			//double weightChange     = errThisPat * myLearningRate * (myLastInput[i]);
			//System.out.print("\t" + inputWeights[i] + " + "+ weightChange + ",");
			inputWeights[i] += weightChange;
		}

		// Re calculate error 
		errThisPat= myLastOutput - fire(myLastInput); 
		//System.out.println("\t" + myLastOutput);
		return errThisPat;
	}
}
