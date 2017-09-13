package com.comtek.xml.services;

public interface Transformer<E> {
	E transform(E object) throws Exception;
}
