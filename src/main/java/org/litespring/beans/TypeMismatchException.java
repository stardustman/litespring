package org.litespring.beans;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月25日 下午5:54:11
 * @version 1.0
 * @description
 */
public class TypeMismatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Object value;
	private Class<?> requireType;
	
	public TypeMismatchException(Object value, Class<?> requiredType) {
	  super("Failed to convert value:" + value + "to type " + requiredType);
	  this.value = value;
	  this.requireType = requiredType;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public Class<?> getRequireType() {
		return this.requireType;
	}

}
