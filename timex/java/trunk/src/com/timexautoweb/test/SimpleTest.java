package com.timexautoweb.test;

import junit.framework.TestCase;

public class SimpleTest extends TestCase {
	int value1, value2, expectedResult;

	protected void setUp() throws Exception {
		super.setUp();
		value1 = 2;
		value2 = 3;
		expectedResult = 5;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddSuccess() {
		assertTrue(value1 + value2 == expectedResult);
	}

	public void testAddFail() {
		assertFalse(value1 - value2 == expectedResult);
	}
}
