/**
 * 
 */
package com.jnn.framework.NeuralNetwork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.jnn.framework.Neurons.GenericNeuron;
import com.jnn.framework.Neurons.INeuron;

/**
 * @author shabbirhussain
 * Holds the network and methods required to manage one
 */
public class GenericNeuralNetwork implements Serializable, INeuralNetwork{
	// Serialization version id
	private static final long serialVersionUID = -58884961167664281L;
	
	private List<List<INeuron>> network;  // Stores the neural network
	private Integer  numLayers;   // Number of layers in a network
	
	/*
	 * Default constructor
	 * numHiddenLayers: Number of hidden layers to build. Minimum is one
	 * numHiddenNeuron: Total number of hidden neurons to build
	 */
	public GenericNeuralNetwork(Integer numHiddenLayers, Integer numHiddenNeuron){
		numHiddenLayers = (numHiddenLayers<=0)? 1 : numHiddenLayers;
		numLayers = numHiddenLayers + 2; //Add an input and output layer
		Integer neuronsPerLayer = (int) Math.ceil(numHiddenNeuron / numHiddenLayers);
		
		List<INeuron> layer; 

		// Create an input layer
		layer  =  new ArrayList<INeuron>();
		layer.add(new GenericNeuron());
		network.add(layer);
		
		// Create hidden layers
		for(int i=1; i<numHiddenLayers; i++){
			layer =  new ArrayList<INeuron>();
			for(int j=0; j<neuronsPerLayer; j++)
				layer.add(new GenericNeuron());
			network.add(layer);
		}
		
		// Create Output Layer
		layer  =  new ArrayList<INeuron>();
		layer.add(new GenericNeuron());
		network.add(layer);	
	}
	
	/*
	 * Trains the network with one pattern
	 * output     : Given output to compare
	 * inputVector: Given array of input values
	 */
	public void train(Double output, Double inputVector[]){
		Double neuralOutput = this.calculateOutput(inputVector)[0];
		
		//calculate the error
		Double errThisPat = output - neuralOutput;
		for(int l=(network.size()-1); l>=0; l--){
			Double residualError = 0.0;	
			for(INeuron n: network.get(l))
				residualError += n.adjustWeights(errThisPat);
			
			errThisPat = residualError;
		}
	}
	
	/*
	 * Given an input array of fields 
	 * Returns: Neural output of network.
	 */
	public Double[] calculateOutput(Double inputVector[]){
		List<INeuron> layer; 
				
		for(int l=0;l<numLayers;l++){
			layer  =  network.get(l);
			
			Double neuralOutput[] = new Double[layer.size()];
			for(int n=0, ls=layer.size(); n<ls; n++){
				neuralOutput[n] = layer.get(n).fire(inputVector);

			inputVector = neuralOutput;
			}	
		}
		return inputVector;
	}
}



