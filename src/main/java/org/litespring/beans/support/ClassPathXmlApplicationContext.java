package org.litespring.beans.support;

import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	public ClassPathXmlApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(configFile);
		
	}

	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}

}