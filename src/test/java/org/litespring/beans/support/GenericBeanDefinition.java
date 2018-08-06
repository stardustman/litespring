package org.litespring.beans.support;

import org.litespring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
    //<bean id="">
	private String id;
	//<bean class="">
    private String beanClassName;
    
    public GenericBeanDefinition(String id,String beanClassName) {
    	this.id = id;
    	this.beanClassName = beanClassName;
	}
	
	public String getBeanClassName() {
		return this.beanClassName;
	}

}
