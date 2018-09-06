package org.litespring.beans.support;

import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月22日 下午5:58:39
 * @version 1.0
 * @description 解析beanID --> bean Instance
 */
public class BeanDefinitionValueResolver {
	private final BeanFactory beanFactory;
	
	public BeanDefinitionValueResolver(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	public Object resolveValueIfNecessary(Object value){
		if(value instanceof RuntimeBeanReference){
			RuntimeBeanReference ref = (RuntimeBeanReference) value;
			String refName = ref.getBeanName();
			Object bean = this.beanFactory.getBean(refName);
			return bean;
		} else if(value instanceof TypedStringValue){
			return ((TypedStringValue) value).getValue();
		}
		
		else{
			throw new RuntimeException("the value " + value + " has not implemented");
		}
	}
	
	

}
