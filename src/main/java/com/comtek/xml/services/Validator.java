package com.comtek.xml.services;

public interface Validator<E> {
	boolean isValid(E object, boolean transform) throws Exception;
}
