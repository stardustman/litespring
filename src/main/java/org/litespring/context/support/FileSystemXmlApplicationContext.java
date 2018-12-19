package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

	private DefaultBeanFactory factory = null;

	public FileSystemXmlApplicationContext(String configFile) {
		// second refactor
		/*
		 * factory = new DefaultBeanFactory(); XMLBeanDefinitionReader reader =
		 * new XMLBeanDefinitionReader(factory); Resource resource = new
		 * FileSystemResource(configFile); reader.loadBeanDefinitions(resource);
		 */

		// first refactor
		// reader.loadBeanDefinitions(configFile);

		super(configFile);
	}

	@Override
	protected Resource getResourceByPath(String path) {
		return new FileSystemResource(path);
	}

	// second refactor
	/*
	 * public Object getBean(String beanID) { return factory.getBean(beanID); }
	 */

}
