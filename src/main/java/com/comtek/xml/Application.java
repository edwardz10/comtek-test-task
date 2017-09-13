package com.comtek.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.comtek.xml.services.Transformer;
import com.comtek.xml.services.Validator;
import com.comtek.xml.services.impl.NodeTransformer;
import com.comtek.xml.services.impl.NodeValidator;
import com.comtek.xml.services.impl.StringTransformer;
import com.comtek.xml.services.impl.StringValidator;

public class Application {

	private Validator<String> stringValidator;
	private Validator<Node> nodeValidator;
	private Transformer<String> stringTransformer;
	private Transformer<Node> nodeTransformer;

	private Document doc;

	public Application() {
		stringValidator = new StringValidator();
		stringTransformer = new StringTransformer();
		nodeValidator = new NodeValidator();
		nodeTransformer = new NodeTransformer(stringTransformer);
	}

	protected String validateTransformXml() {
		return "";
	}
	
	public String loadAndTransformXml(String path) throws Exception {
		File inputFile = new File("input.txt");
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

	}
}
