package com.jnn.framework.Neurons;


public class BiasNeuron extends GenericNeuron{

	// Serialization version ID
	private static final long serialVersionUID = 1L;

	/**
	 * Maps given value to a output through a neural function
	 * @param x An input number
	 * @return Bias output every time
	 */
	@Override
	protected Double thresholdFun(Double x){
		return 1.0;
	}
	
	/**
	 * Does nothing for this type of neuron
	 * @param expectedOutput Expected outputs in the last run(Neuron has just one output)
	 * @return Depreciated. No longer used
	 */
	@Override
	public Double[] train(Double expectedOutput[]){
		return (new Double[0]);
	}
	
	/**
	 * Function used to trigger a neural response. 
	 * @param inputVector Input array of values
	 * @return An neural output from neuron
	 */
	@Override
	public Double fire(Double inputVector[]){
		myLastOutput = 1.0;
		return myLastOutput;
	}
}
