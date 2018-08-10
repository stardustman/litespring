package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

public class BeanCreationException extends BeansException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanCreationException(String msg) {
		super(msg);
		
	}

	public BeanCreationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
