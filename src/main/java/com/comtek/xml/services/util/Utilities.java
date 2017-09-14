package com.comtek.xml.services.util;

import java.io.File;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class Utilities {

	public static Document filePathToDomDocument(String path) throws Exception {
		File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(inputFile);
	}
	
	public static String documentToString(Document doc) throws TransformerException {
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(domSource, result);
		return writer.toString();
	}

	public static List<Element> getChildElementsByNames(Element element, String... names) {
		NodeList nl = element.getChildNodes();
		List<Element> elements = new LinkedList<>();

		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			for (String name : names) {
				if (nl.item(i).getNodeName().equals(name)) {
					elements.add((Element)nl.item(i));
					break;
				}
			}
		}
		
		return elements;
	}

	public static String getElementAttributeValue(Element element, String attrName) {
		NamedNodeMap nnm = element.getAttributes();

		for (int i = 0; i < nnm.getLength(); i++) {
			if (nnm.item(i).getNodeName().equals(attrName)) {
				return nnm.item(i).getNodeValue();
			}
		}

		return null;
	}

	public static void setElementAttributeValue(Element element, String attrName, String attrValue) {
		NamedNodeMap nnm = element.getAttributes();

		for (int i = 0; i < nnm.getLength(); i++) {
			if (nnm.item(i).getNodeName().equals(attrName)) {
				nnm.item(i).setNodeValue(attrValue);
			}
		}
	}

	public static void printNodeAttributes(NamedNodeMap nnm) {
		for (int i = 0; i < nnm.getLength(); i++) {
			System.out.println(nnm.item(i).getNodeName() + " - " + nnm.item(i).getNodeValue());
		}
	}
}
