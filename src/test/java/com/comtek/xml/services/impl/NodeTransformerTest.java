package com.comtek.xml.services.impl;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.comtek.xml.services.util.Utilities;

public class NodeTransformerTest {

	private StringTransformer stringTransformer;
	private ElementTransformer elementTransformer;

	private DocumentBuilder db;
	
	@Before
	public void setUp() throws Exception {
		stringTransformer = new StringTransformer();
		elementTransformer = new ElementTransformer(stringTransformer);

		db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	private Document testXmlToDocument(String xmlPath) throws Exception {
		return db.parse(getClass().getResourceAsStream(xmlPath));
	}

	@Test
	public void nodeWithUniqueIdentifiersIsValidTest() throws Exception {
		Document doc = testXmlToDocument("nodeWithUniqueIdentifiers.xml");
		Element transformedElement = elementTransformer.transform(doc.getDocumentElement());

		assertEquals(Utilities.getElementAttributeValue(transformedElement, "name"), "c.kcats");
	}
	
}
