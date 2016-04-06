/**
 * 
 */
package com.jnn.framework.NeuralNetwork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.jnn.framework.Neurons.GenericNeuron;
import com.jnn.framework.Neurons.INeuron;
import com.jnn.framework.Neurons.LinearFunctionNeuron;

/**
 * @author shabbirhussain
 * Holds the network and methods required to manage one
 */
public class GenericNeuralNetwork implements INeuralNetwork{
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
		numLayers = numHiddenLayers + 1; //Add an input and output layer
		Integer neuronsPerLayer = (int) Math.ceil(numHiddenNeuron / numHiddenLayers);
		
		List<INeuron> layer; 
		network = new ArrayList<List<INeuron>>();
		
		
		// Create hidden layers
		for(int i=0; i<numHiddenLayers; i++){
			layer =  new ArrayList<INeuron>();
			for(int j=0; j<neuronsPerLayer; j++)
				layer.add(new GenericNeuron());
			network.add(layer);
		}
		
		// Create Output Layer
		layer  =  new ArrayList<INeuron>();
		layer.add(new LinearFunctionNeuron());
		network.add(layer);
	}
	
	private void printInputs(Double output, Double inputVector[], Double error, Double nnoutput){
		System.out.print("Cycle o/p= " + output + " [ ");
		for(Double i : inputVector) System.out.print(Math.round(i) +" ");
		System.out.println("] n/o= " + nnoutput + " Error= " + error);
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
		printInputs(output, inputVector, errThisPat, neuralOutput);
		for(int l=(network.size()-1); l>=0; l--){
			Double residualError = 0.0;
			List<INeuron> layer = network.get(l);
			for(INeuron n: layer)
				residualError += n.adjustWeights(errThisPat);

			//errThisPat = residualError;
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
			for(int n=0, ls=layer.size(); n<ls; n++)
				neuralOutput[n] = layer.get(n).fire(inputVector);
			
			inputVector = neuralOutput;	
		}
		return inputVector;
	}
}



