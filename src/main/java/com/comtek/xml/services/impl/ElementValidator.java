package com.comtek.xml.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;

import com.comtek.xml.services.Validator;
import com.comtek.xml.services.util.Utilities;


public class ElementValidator implements Validator<Element> {

	final ElementTransformer elementTransformer;
	final StringValidator stringValidator;

	private Element currentElement;
	private List<Element> currentIdentifierElements;
	
	public ElementValidator(ElementTransformer nodeTransformer, StringValidator stringValidator) {
		this.elementTransformer = nodeTransformer;
		this.stringValidator = stringValidator;
	}

	protected boolean isElementValid(Element element) {
		boolean result = true;
		System.out.println("Element '" + element.getNodeName() + "'");
		String elementName = Utilities.getElementAttributeValue(element, "name");
		Set<String> identifierNames = new HashSet<>();
		currentIdentifierElements = Utilities.getChildElementsByNames(element, "identifier");

		for (Element identifierElement : currentIdentifierElements) {
			String identifierName = Utilities.getElementAttributeValue(identifierElement, "name");

			if (identifierName != null) {
				identifierNames.add(identifierName);
			} else {
				System.out.println("Identifier name not found for element " + identifierElement.getNodeName());
				result = false;
			}
		}

		if (result) {
			if (identifierNames.size() != currentIdentifierElements.size()) {
				System.out.println("All identifiers are NOT unique within '" + elementName + "' element");
				result = false;
			}
		}
			
		if (result) {
			System.out.println("All child identifiers of element '" + element.getNodeName() + "' are unique (" + identifierNames + ")");
		}
		
		return result;
	}

	protected boolean areIdentifiersValid() {
		boolean result = true;

		for (Element identifier : currentIdentifierElements) {
			String identifierName = Utilities.getElementAttributeValue(identifier, "name");

			if (!stringValidator.isValid(identifierName, false)) {
				System.out.println("Identifier '" + identifierName + "' is NOT valid");
				result = false;
				break;
			}
		}

		if (result) {
			System.out.println("Child identifiers of element '" + currentElement.getNodeName() + "' are valid");
		}
		
		
		return result;
	}
	
	@Override
	public boolean isValid(Element object, boolean transform) throws Exception {
		boolean result = true;
		currentElement = object;

		result = isElementValid(currentElement);
		
		if (result) {
			result = areIdentifiersValid();
		}

		if (result) {
			elementTransformer.transform(currentElement);
			
			List<Element> childElements = Utilities.getChildElementsByNames(currentElement, "function", "section");
	
			for (Element childElement : childElements) {
				if (!isValid(childElement, transform)) {
					result = false;
					break;
				}
			}
		}

		return result;
	}

}
