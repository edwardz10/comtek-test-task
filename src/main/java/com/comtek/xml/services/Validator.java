package com.comtek.xml.services;

public interface Validator<E> {
	boolean isValid(E object) throws Exception;
}
