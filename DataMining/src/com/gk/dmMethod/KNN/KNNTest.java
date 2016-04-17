package com.gk.dmMethod.KNN;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KNNTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPrecdite() {
		KNN knn = new KNN();
		knn.setTrainSet("tarinSet.txt");
		knn.setTestSet("testSet.txt");
		try {
			knn.precdite(System.out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		fail("Not yet implemented");
	}

}
