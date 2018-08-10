package org.litespring.beans.support;

import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月10日 下午9:11:35
 * @version 1.0
 * @description 模板方法
 * ### ClassPathXmlApplication 与 FileSystemXmlApplication构造函数
Resource resource = new ClassPathResource(configFile);
Resource resource = new FileSystemResource(configFile);不同
其他的都是重复代码    
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}
	
	public AbstractApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
	    Resource resource = this.getResourceByPath(configFile);
	    reader.loadBeanDefinitions(resource);
	}

	protected abstract Resource getResourceByPath(String configFile);

}
