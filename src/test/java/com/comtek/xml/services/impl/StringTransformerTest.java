package com.comtek.xml.services.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringTransformerTest {

	private StringTransformer stringTransformer;

	@Before
	public void setUp() throws Exception {
		stringTransformer = new StringTransformer();
	}

	@Test
	public void transformStringTest() throws Exception {
		assertEquals(stringTransformer.transform("mama"), "amam");
	}
}
