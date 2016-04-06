/**
 * 
 */
package com.jnn.framework.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

import com.jnn.framework.Neurons.*;

/**
 * @author shabbirhussain
 * Holds the network and methods required to manage one
 */
public class GenericNeuralNetwork implements NeuralNetwork{
	// Serialization version id
	private static final long serialVersionUID = -58884961167664281L;
	
	private List<List<Neuron>> network;  // Stores the neural network
	private Integer  numLayers;           // Number of layers in a network
	private Double errorGradient[];       // Gradient error propogation
	
	/**
	 * Default constructor
	 * @param numHiddenLayers: Number of hidden layers to build. Minimum is one 
	 * @param numHiddenNeuron: Total number of hidden neurons to build
	 */
	public GenericNeuralNetwork(Integer numHiddenLayers, Integer numHiddenNeuron){
		numHiddenLayers = (numHiddenLayers<=0)? 1 : numHiddenLayers;
		numLayers = numHiddenLayers + 1; //Add an input and output layer
		Integer neuronsPerLayer = (int) Math.ceil(numHiddenNeuron / numHiddenLayers);
		
		List<Neuron> layer; 
		network = new ArrayList<List<Neuron>>();
		
		
		// Create hidden layers
		for(int i=0; i<numHiddenLayers; i++){
			layer =  new ArrayList<Neuron>();
			for(int j=0; j<neuronsPerLayer; j++)
				layer.add(new ExpFunctionNeuron());
			network.add(layer);
		}
		
		
		// Create Output Layer
		layer  =  new ArrayList<Neuron>();
		layer.add(new LinearFunctionNeuron());
		network.add(layer);
		
		
		// Calculate error gradient
		Double totalGradient = 0.0;
		errorGradient = new Double[numLayers];
		for(int l=0; l<numLayers; l++){
			errorGradient[l] =  Math.exp(numLayers - l);
			totalGradient += errorGradient[l];
		}
		for(int l=0; l<numLayers; l++)
			errorGradient[l] /= totalGradient;
		
	}
	
	/**
	 * Trains the network with one pattern
	 * @param expectedOutput: Expected Output of the neural network
	 * @param inputVector: Input array of values
	 */
	public void train(Double expectedOutput, Double inputVector[]){
		Double neuralOutput = this.calculateOutput(inputVector)[0];
		
		
		//calculate the error
		Double errThisPat    = expectedOutput - neuralOutput;
		Double errGradient[] = new Double[numLayers];
		for(int i=0; i<numLayers; i++)
			errGradient[i]   =  errThisPat/numLayers;
		
		for(int i=(numLayers-1); i>=0; i--){
			
		}
		
		//printInputs(output, inputVector, errThisPat, neuralOutput);
		
		for(int l=(numLayers-1); l>=0; l--){
			List<Neuron> layer = network.get(l);
			for(Neuron n: layer)
				n.adjustWeights(errThisPat * errorGradient[l]);
		}

	}
	
	/**
	 * Given an input array of fields 
	 * @param inputVector: Input array of values
	 * @return An array of output from network
	 */
	public Double[] calculateOutput(Double inputVector[]){
		List<Neuron> layer; 
				
		for(int l=0;l<numLayers;l++){
			layer  =  network.get(l);
			Double neuralOutput[] = new Double[layer.size()];
			for(int n=0, ls=layer.size(); n<ls; n++)
				neuralOutput[n] = layer.get(n).fire(inputVector);
			
			inputVector = neuralOutput;	
		}
		return inputVector;
	}
	

	private void printInputs(Double output, Double inputVector[], Double error, Double nnoutput){
		System.out.print("Cycle o/p= " + output + " [ ");
		for(Double i : inputVector) System.out.print(Math.round(i) +" ");
		System.out.println("] n/o= " + nnoutput + " Error= " + error);
	}
	
}



