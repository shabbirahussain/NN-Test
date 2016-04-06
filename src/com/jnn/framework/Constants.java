package com.jnn.framework;
/**
 * This class contains the training constants for a neural network.
 */
public final class Constants {
	public static final Double LEARNING_RATE = 0.7;
	public static final Double LINEAR_LEARNING_RATE = 0.07;
	
	public static final String OBJECT_STORE_LOCATION="/Users/shabbirhussain/Google Drive/NEU/Code/NN-Test/resources/";
	public static final String BASIC_NETWORK_STORAGE="network.ser";
	public static final String FULL_NW_STORAGE_PATH = OBJECT_STORE_LOCATION + BASIC_NETWORK_STORAGE;
	
	public static final Integer NUM_HIDDEN_LAYERS =  1;
	public static final Integer NUM_HIDDEN_NEURONS= 10;

	public static final String FILE_DELIMITER     = ",";
	public static final String TRAINING_FILE_LOC  = "/Users/shabbirhussain/Documents/Temp/PacmanData/run1.dat";
	public static final Integer NUM_TRAINING_CYLCE= 500;
}
