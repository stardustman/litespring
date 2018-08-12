package org.litespring.beans.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.utils.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
		implements ConfigurableBeanFactory, BeanDefinitionRegistry {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	public DefaultBeanFactory() {

	}

	private ClassLoader beanClassLoader;

	// bean definition change to bean instance
	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if (bd == null) {
			throw new BeanCreationException("Bean Definiton does not exist");
		}

		if (bd.isSingleton()) {
			Object bean = this.getSingleton(beanID);
			if (bean == null) {
				bean = createBean(bd);
				this.registerSingleton(beanID, bean);
			}
		}
		return createBean(bd);

	}

	/**
	 * 创建bean是就可以做手脚了
	 * @param bd
	 * @return
	 */
	private Object createBean(BeanDefinition bd) {
		ClassLoader cl = this.getBeanClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();

		} catch (Exception e) {
			throw new BeanCreationException("create bean for " + beanClassName + "failed", e);
		}

	}

	public BeanDefinition getBeanDefinition(String beanID) {
		return this.beanDefinitionMap.get(beanID);
	}

	public void registerBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}

	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;

	}

	public ClassLoader getBeanClassLoader() {
		return this.beanClassLoader != null ? beanClassLoader : ClassUtils.getDefaultClassLoader();
	}

	/*
	 * private void loadBeanDefinition(String configFile) { InputStream is =
	 * null; ClassLoader cl = ClassUtils.getDefaultClassLoader(); is =
	 * cl.getResourceAsStream(configFile); SAXReader reader = new SAXReader();
	 * try { // XML -> Document Document doc = reader.read(is); Element root =
	 * doc.getRootElement(); // <beans></beans> Iterator<Element> iter =
	 * root.elementIterator(); while (iter.hasNext()) { Element ele = (Element)
	 * iter.next(); String id = ele.attributeValue(ID_ATTRIBUTE); String
	 * beanClassName = ele.attributeValue(CLASS_ATTRIBUTE); BeanDefinition bd =
	 * new GenericBeanDefinition(id, beanClassName);
	 * this.beanDefinitionMap.put(id, bd); } } catch (DocumentException e) {
	 * throw new BeanDefinitionStoreException("IOException parsing XML"); }
	 * finally { if (is != null) { try { is.close(); } catch (IOException e) {
	 * e.printStackTrace(); } } } }
	 */

}
