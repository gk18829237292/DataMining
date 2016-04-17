package com.gk.dmMethod;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NaiveBayesianTest {
	
	NaiveBayesian bayes = null;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("bayes Init");
		bayes = new NaiveBayesian();
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void testSetTrainSetFile() {
		try {
			System.out.println(1);
			bayes.setTrainSetFile("F:\\my_JAVA1\\DataMining\\src\\tarinSet.txt");
			bayes.generatePredictedModel();
			bayes.getModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		fail("Not yet implemented");
	}

	@Test
	public void testSetTestSetFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testGeneratePredictedModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrecdite() {
		fail("Not yet implemented");
	}

}
