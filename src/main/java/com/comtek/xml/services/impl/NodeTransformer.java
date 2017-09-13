package com.comtek.xml.services.impl;

import org.w3c.dom.Node;

import com.comtek.xml.services.Transformer;

public class NodeTransformer implements Transformer<Node> {

	private Transformer<String> stringTransformer;

	public NodeTransformer(Transformer<String> stringTransformer) {
		this.stringTransformer = stringTransformer;
	}
	
	@Override
	public Node transform(Node object) throws Exception {
		// TODO Auto-generated method stub
		return object;
	}

}
