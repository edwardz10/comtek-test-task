package com.comtek.xml.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class NodeValidatorTest {

	private StringTransformer stringTransformer;
	private ElementTransformer nodeTransformer;
	private StringValidator stringValidator;
	private ElementValidator nodeValidator;

	private DocumentBuilder db;
	
	@Before
	public void setUp() throws Exception {
		stringValidator = new StringValidator();
		stringTransformer = new StringTransformer();
		nodeTransformer = new ElementTransformer(stringTransformer);
		nodeValidator = new ElementValidator(nodeTransformer, stringValidator);

		db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	private Document testXmlToDocument(String xmlPath) throws Exception {
		return db.parse(getClass().getResourceAsStream(xmlPath));
	}
	
	@Test
	public void nodeWithUniqueIdentifiersIsValidTest() throws Exception {
		Document doc = testXmlToDocument("nodeWithUniqueIdentifiers.xml");

		assertTrue(nodeValidator.isValid(doc.getDocumentElement(), false));
	}

	@Test
	public void nodeWithIdentifiersWithoutNameAttrIsValidTest() throws Exception {
		Document doc = testXmlToDocument("nodeWithIdentifiersWithoutNameAttr.xml");

		assertFalse(nodeValidator.isValid(doc.getDocumentElement(), false));
	}

	@Test
	public void nodeWithNonUniqueIdentifiersIsValidTest() throws Exception {
		Document doc = testXmlToDocument("nodeWithNonUniqueIdentifiers.xml");

		assertFalse(nodeValidator.isValid(doc.getDocumentElement(), false));
	}

}
