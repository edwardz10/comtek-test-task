package com.comtek.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.comtek.xml.services.impl.ElementTransformer;
import com.comtek.xml.services.impl.ElementValidator;
import com.comtek.xml.services.impl.StringTransformer;
import com.comtek.xml.services.impl.StringValidator;
import com.comtek.xml.services.util.Utilities;

public class Application {

	private StringValidator stringValidator;
	private ElementValidator elementValidator;
	private StringTransformer stringTransformer;
	private ElementTransformer elementTransformer;

	private Document doc;

	public Application() {
		stringValidator = new StringValidator();
		stringTransformer = new StringTransformer();
		elementTransformer = new ElementTransformer(stringTransformer);
		elementValidator = new ElementValidator(elementTransformer, stringValidator);
	}

	protected String validateXml(boolean transform) throws Exception {
		Element topNode = doc.getDocumentElement();

		return elementValidator.isValid(topNode, transform)
				? Utilities.documentToString(doc)
				: "";
	}

	public String loadAndTransformXml(String path) throws Exception {
        doc = Utilities.filePathToDomDocument(path);

        return validateXml(true);
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java -jar target/xml-parser-1.0-SNAPSHOT.jar <xml file>");
			return;
		}

		String xmlIn = args[0];

		try {
			String xmlOut = new Application().loadAndTransformXml(xmlIn);
			System.out.println("Validated and transformed " + xmlIn + ":");
			System.out.println(xmlOut);
		} catch (Exception e) {
			System.err.println("Failed to validate and transform " + xmlIn + e);
		}
	}
}
