package org.litespring.beans.support;

import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class ClassPathXmlApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	public ClassPathXmlApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource(configFile);
		reader.loadBeanDefinitions(configFile);
		//reader.loadBeanDefinitions(resource);
		
	}

	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}

}
