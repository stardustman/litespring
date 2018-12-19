package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

	private DefaultBeanFactory factory = null;

	public ClassPathXmlApplicationContext(String configFile) {
		// second refactor
		/*
		 * factory = new DefaultBeanFactory(); XMLBeanDefinitionReader reader =
		 * new XMLBeanDefinitionReader(factory); Resource resource = new
		 * ClassPathResource(configFile); reader.loadBeanDefinitions(resource);
		 */

		// first refactor
		// reader.loadBeanDefinitions(configFile);
		super(configFile);

	}
	// second refactor
	/*
	 * public Object getBean(String beanID) { return factory.getBean(beanID); }
	 */

	@Override
	protected Resource getResourceByPath(String path) {
		return new ClassPathResource(path, this.getBeanClassLoader() );
	}

}
