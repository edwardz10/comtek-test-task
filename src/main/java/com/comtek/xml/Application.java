package com.comtek.xml;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.comtek.xml.services.impl.NodeTransformer;
import com.comtek.xml.services.impl.NodeValidator;
import com.comtek.xml.services.impl.StringTransformer;
import com.comtek.xml.services.impl.StringValidator;

public class Application {

	private StringValidator stringValidator;
	private NodeValidator nodeValidator;
	private StringTransformer stringTransformer;
	private NodeTransformer nodeTransformer;

	private Document doc;

	public Application() {
		stringValidator = new StringValidator();
		stringTransformer = new StringTransformer();
		nodeValidator = new NodeValidator();
		nodeTransformer = new NodeTransformer(stringTransformer);
	}

	protected String validateTransformXml() throws Exception {
		Element topNode = doc.getDocumentElement();
		
		if (nodeValidator.isValid(topNode)) {
			nodeTransformer.transform(topNode);
		}
			
		return documentToString();
	}

	protected String documentToString() throws TransformerException {
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(domSource, result);
		return writer.toString();
	}
	
	public String loadAndTransformXml(String path) throws Exception {
		File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);

        return validateTransformXml();
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
