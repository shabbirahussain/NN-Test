/**
 * 
 */
package com.jnn.framework.Neurons;

/**
 * @author shabbirhussain
 * Geenric Neuron class
 */
public abstract class GenericNeuron implements Neuron{
	// Default serialization ID
	private static final long serialVersionUID = 1L;
	protected Double inputWeights[];
	protected Double myLastOutput, myLastInput[];
	protected Double myLearningRate;
	
	/**
	 * Default Constructor
	 */
	public GenericNeuron(){
		inputWeights   = new Double[0];
		myLastInput    = new Double[0];
	}
	
	/**
	 * @return The last output of neuron
	 */
	public Double getLastOutput(){
		return myLastOutput;
	}
	
	/**
	 * Maps given value to a output through a neural function
	 * @param x An input number
	 * @return A double number generated for given input
	 */
	protected abstract double thresholdFun(double x);
	
	/* 
	 * Extends the given list of inputWeights to the given size and initializes it to default value
	 * nlen Length of array to initialize
	 */
	protected void extendInitalizeWeights(Integer nlen){
		Double newWeights[] = new Double[nlen];
		Integer i, olen=inputWeights.length;
		
		for(i=0   ; i<olen; i++) newWeights[i] = inputWeights[i];
		for(i=olen; i<nlen; i++) newWeights[i] = Math.random();
		
		inputWeights = newWeights;
	}
}
