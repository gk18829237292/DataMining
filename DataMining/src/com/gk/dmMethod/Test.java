package com.gk.dmMethod;

public class Test {
	
	public static void main(String[] args) throws Exception{
		
		NaiveBayesian bayesian = new NaiveBayesian();
		bayesian.setTrainSetFile("F:\\my_JAVA1\\DataMining\\src\\tarinSet.txt");
		bayesian.setTestSetFile("F:\\my_JAVA1\\DataMining\\src\\testSet.txt");
		bayesian.generatePredictedModel();
		bayesian.getModel();
		System.out.println();
//		System.out.println("Start Precdite");
		bayesian.precdite();
	}
	
}
