package com.orcunguducu.dynamic.util;

import org.junit.Test;

public class DataGeneratorTest {

	@Test(expected = Exception.class)
	public void createData() {
		DataGenerator.createData(true);
	}
	
}
