package org.litespring.beans.support;

import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;


public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

	private DefaultBeanFactory factory = null;
	public FileSystemXmlApplicationContext(String configFile) {
		//second refactor
	    /* factory = new DefaultBeanFactory();
	     XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
	     Resource resource = new FileSystemResource(configFile);
	     reader.loadBeanDefinitions(resource);*/
	     
	     //first refactor
	     //reader.loadBeanDefinitions(configFile);
		
		super(configFile);
	}
	@Override
	protected Resource getResourceByPath(String configFile) {
		return new FileSystemResource(configFile);
	}
	

	//second refactor
	/*public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}*/

}
