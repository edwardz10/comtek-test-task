package com.comtek.xml.services.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringValidatorTest {

	private StringValidator stringValidator;

	@Before
	public void setUp() {
		stringValidator = new StringValidator();
	}
	
	@Test
	public void identifierIsValidTest() {
		String identifier = "abc";
		assertTrue(stringValidator.isValid(identifier, false));
	}

	@Test
	public void identifierStartingWithDigisIsInvalidTest() {
		String identifier = "1abc";

		assertFalse(stringValidator.isValid(identifier, false));
	}

	@Test
	public void cKeywordIdentifierIsInvalidTest() {
		String identifier = "double";

		assertFalse(stringValidator.isValid(identifier, false));
	}	
}
