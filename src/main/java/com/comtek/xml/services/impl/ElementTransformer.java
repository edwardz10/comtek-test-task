package com.comtek.xml.services.impl;

import org.w3c.dom.Element;

import com.comtek.xml.services.Transformer;
import com.comtek.xml.services.util.Utilities;

public class ElementTransformer implements Transformer<Element> {

	private Transformer<String> stringTransformer;

	public ElementTransformer(Transformer<String> stringTransformer) {
		this.stringTransformer = stringTransformer;
	}
	
	@Override
	public Element transform(Element object) throws Exception {
		String name = Utilities.getElementAttributeValue(object, "name");
		String newName = stringTransformer.transform(name);
		System.out.print("Transform element " + object.getNodeName() + " name '" + name + "' to '" + newName + "'\n");
		Utilities.setElementAttributeValue(object, "name", newName);
		return object;
	}

}
