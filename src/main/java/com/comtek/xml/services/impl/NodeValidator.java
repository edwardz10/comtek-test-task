package com.comtek.xml.services.impl;

import javax.xml.soap.Node;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.comtek.xml.services.Validator;

public class NodeValidator implements Validator<Element> {

	protected void printNodeAttributes(NamedNodeMap nnm) {
		for (int i = 0; i < nnm.getLength(); i++) {
			System.out.println(nnm.item(i).getNodeName() + " - " + nnm.item(i).getNodeValue());
		}
	}
	
	@Override
	public boolean isValid(Element object) throws Exception {
		System.out.println("Element type: " + object.getNodeType());
		System.out.println("Attributes: ");
		printNodeAttributes(object.getAttributes());
		
		NodeList nl = object.getChildNodes();

		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Element name: " + nl.item(i).getNodeName());
				printNodeAttributes(nl.item(i).getAttributes());
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

}
