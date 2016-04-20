package com.jnn.framework;
/**
 * This class contains the training constants for a neural network.
 */
public final class Constants {
	public static final Integer ERROR_TOLLERANCE   = 2;   // Number of decimal places
	public static final Boolean DEBUG = true;
	
	public static final Double EXP_LEARNING_RATE = 0.7;
	public static final Double LINEAR_LEARNING_RATE = 0.07;
	
	public static final String OBJECT_STORE_LOCATION="/Users/shabbirhussain/Google Drive/NEU/Code/NN-Test/resources/";
	public static final String BASIC_NETWORK_STORAGE="network.ser";
	public static final String FULL_NW_STORAGE_PATH = OBJECT_STORE_LOCATION + BASIC_NETWORK_STORAGE;
	
	public static final Integer NUM_HIDDEN_LAYERS = 0;
	public static final Integer NUM_HIDDEN_NEURONS= 1;

	public static final String FILE_DELIMITER     = ",";
	public static final String TRAINING_FILE_LOC  = "/Users/shabbirhussain/Documents/Temp/PacmanData/smart1.dat";
	public static final String TESTING_FILE_LOC   = "/Users/shabbirhussain/Documents/Temp/PacmanData/smart1.dat";
	
	public static final Integer NUM_TRAINING_CYLCE= 500;
	
	public static final Boolean CREATE_NEW_NETWORK = true;
}
