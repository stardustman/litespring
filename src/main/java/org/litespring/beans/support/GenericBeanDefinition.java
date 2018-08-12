package org.litespring.beans.support;

import org.litespring.beans.BeanDefinition;


public class GenericBeanDefinition implements BeanDefinition {
    //<bean id="">
	private String id;
	//<bean class="">
    private String beanClassName;
    
    //默认单例
    private boolean  singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;
    
    public GenericBeanDefinition(String id,String beanClassName) {
    	this.id = id;
    	this.beanClassName = beanClassName;
	}
	
	public String getBeanClassName() {
		return this.beanClassName;
	}

	public boolean isPrototype() {
		return this.prototype;
	}

	public boolean isSingleton() {
		return this.singleton;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope; 
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
	    this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

}
