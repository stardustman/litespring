package org.litespring.beans.factory.config;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月22日 下午3:29:28
 * @version 1.0
 * @description
 */
public class RuntimeBeanReference {
	
    private final String beanName;
    
    public RuntimeBeanReference(String beanName){
    	this.beanName = beanName;
    }
    
    public String getBeanName(){
    	return this.beanName;
    }
    
    
}
