package org.litespring.beans.support;

import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;


public class FileSystemXmlApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	public FileSystemXmlApplicationContext(String configFile) {
	     factory = new DefaultBeanFactory();
	     XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
	     //reader.loadBeanDefinitions(configFile);
	     Resource resource = new FileSystemResource(configFile);
	     reader.loadBeanDefinitions(resource);
	}

	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}

}
