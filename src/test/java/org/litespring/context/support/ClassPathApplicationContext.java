package org.litespring.context.support;

import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.context.ApplicationContext;

public class ClassPathApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	public ClassPathApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(configFile);
		
	}

	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}

}
