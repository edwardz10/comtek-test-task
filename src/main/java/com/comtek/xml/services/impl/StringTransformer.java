package com.comtek.xml.services.impl;

import com.comtek.xml.services.Transformer;

public class StringTransformer implements Transformer<String> {

	@Override
	public String transform(String object) throws Exception {
		return new StringBuilder(object).reverse().toString();
	}

}
