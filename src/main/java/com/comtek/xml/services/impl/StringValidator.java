package com.comtek.xml.services.impl;

import java.util.Arrays;
import java.util.List;

import com.comtek.xml.services.Validator;

public class StringValidator implements Validator<String> {

	private static List<String> C_KEYWORDS = Arrays.asList("auto", "break", "case", "char",
			"const", "continue", "default", "do",
			"double", "else", "enum", "extern",
			"float", "for", "goto", "if",
			"int", "long", "register", "return",
			"short", "signed", "sizeof", "static",
			"struct", "switch", "typedef", "union",
			"unsigned", "void", "volatile", "while");

	public List<String> getCKeywords() {
		return C_KEYWORDS;
	}
	
	private boolean isUnderscore(char c) {
		return c == '_';
	}
	
	private boolean isLetter(char c) {
		return Character.isLetter(c);
	}

	private boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	@Override
	public boolean isValid(String object, boolean transform) {
		boolean result = true;
		char c;
		result = !C_KEYWORDS.contains(object);

		
		if (result) {
			c = object.charAt(0);
			result = isLetter(c) || isUnderscore(c);
		}
		
		if (result) {
			for (int i = 1; i < object.length(); i++) {
				c = object.charAt(i);
				result = isLetter(c)
						  || isUnderscore(c)
						  || isDigit(c);

				if (!result) break;
			}
		}
		
		return result;
	}
}
