package org.litespring.beans.support;

import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

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
	private ClassLoader beanClassLoader;
	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}
	
	public AbstractApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
	    Resource resource = this.getResourceByPath(configFile);
	    reader.loadBeanDefinitions(resource);
	    factory.setBeanClassLoader(this.getBeanClassLoader());
	}

	protected abstract Resource getResourceByPath(String path);
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;

	}
	
	public ClassLoader getBeanClassLoader() {
		return this.beanClassLoader != null ? beanClassLoader: ClassUtils.getDefaultClassLoader();
	}


}
