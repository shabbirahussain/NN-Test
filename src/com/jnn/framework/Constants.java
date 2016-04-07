package com.jnn.framework;
/**
 * This class contains the training constants for a neural network.
 */
public final class Constants {
	public static final Double LEARNING_RATE = 0.00007;
	public static final Double LINEAR_LEARNING_RATE = 0.07;
	
	public static final String OBJECT_STORE_LOCATION="/Users/shabbirhussain/Google Drive/NEU/Code/NN-Test/resources/";
	public static final String BASIC_NETWORK_STORAGE="network.ser";
	public static final String FULL_NW_STORAGE_PATH = OBJECT_STORE_LOCATION + BASIC_NETWORK_STORAGE;
	
	public static final Integer NUM_HIDDEN_LAYERS =  3;
	public static final Integer NUM_HIDDEN_NEURONS= 20;

	public static final String FILE_DELIMITER     = ",";
	public static final String TRAINING_FILE_LOC  = "/Users/shabbirhussain/Documents/Temp/PacmanData/run1.dat";
	public static final String TESTING_FILE_LOC   = "/Users/shabbirhussain/Documents/Temp/PacmanData/run1.dat";
	
	public static final Integer NUM_TRAINING_CYLCE= 5000;
	
	public static final Integer ERROR_TOLLERANCE   = 15;        // Number of decimal places
	
	public static final Boolean CREATE_NEW_NETWORK = false;
}
