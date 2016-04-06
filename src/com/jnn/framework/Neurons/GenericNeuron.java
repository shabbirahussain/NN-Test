/**
 * 
 */
package com.jnn.framework.Neurons;

import static com.jnn.framework.Constants.*;

/**
 * @author shabbirhussain
 *
 */
public class GenericNeuron implements INeuron{
	// Default serialization ID
	private static final long serialVersionUID = 1L;
	private Double inputWeights[];
	private Double myLastOutput, myLastInput[];
	private Double myLearningRate;
	
	/*
	 * Default Constructor
	 * learningRate: Takes the learning rate for a neuron
	 */
	public GenericNeuron(){
		inputWeights   = new Double[0];
		myLastInput    = new Double[0];
		myLearningRate = LEARNING_RATE;
	}
	
	/*
	 * Maps given value to a output through a neural function
	 * x: An input number
	 * Returns: A double number generated for given input
	 */
	private static double thresholdFun(double x){
		return Math.tanh(x);
	}
	
	/* 
	 * Extends the given list of inputWeights to the given size and initializes it to default value
	 * nlen: Length of array to initialize
	 */
	private void extendInitalizeWeights(Integer nlen){
		Double newWeights[] = new Double[nlen];
		Integer i, olen=inputWeights.length;
		
		for(i=0   ; i<olen; i++) newWeights[i] = inputWeights[i];
		for(i=olen; i<nlen; i++) newWeights[i] = Math.random();
		
		inputWeights = newWeights;
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
		
		myLastOutput = thresholdFun(myLastOutput);
		return myLastOutput;
	}
	
	/*
	 * Adjusts weights of current neuron to cope for error.
	 * errThisPat: Given error value to match for
	 */
	public Double adjustWeights(Double errThisPat){
		double x = 1 - (myLastOutput * myLastOutput);
		
		for(int i=0, l=myLastInput.length;i<l;i++){
			double weightChange = x;
			weightChange    *= inputWeights[i] * errThisPat * myLearningRate * myLastInput[i];
			inputWeights[i] -= weightChange;
		}
		
		return 0.0;
	}
}
