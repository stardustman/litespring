package org.litespring.beans.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanCreationException;
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
	 * 
	 * @param bd
	 * @return
	 */
	private Object createBean(BeanDefinition bd) {

		// 创建实例
		Object bean = instantiateBean(bd);
		// 设置属性
		populateBean(bd, bean);
		return bean;

	}

	private void populateBean(BeanDefinition bd, Object bean) {
		
		//得到bean的property
		List<PropertyValue> pvs = bd.getPropertyValues();

		//不需要setter注入
		if (pvs == null || pvs.isEmpty()) {
			return;
		}
		
		// this DefaultBeanFactory实例
		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
		
		try {
			for(PropertyValue pv : pvs){
				//<property name="accountDao" ref="accountDao"></property>
				//eg: propertyName accountDao 
				String propertyName = pv.getName();
				// originalValue 表示ref
				Object originalValue = pv.getValue();
				Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
			    
				//使用JavaBean提供的方法
				BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					if(pd.getName().equals(propertyName)){
						//调用petStore的setAccountDao的setter方法
						pd.getWriteMethod().invoke(bean, resolvedValue);
					    break;
					}
				}
			
			}
			
			
		} catch (Exception e) {
			throw new BeanCreationException("Failed to abtain BeanInfo for class [" + bd.getBeanClassName() + "]");
		}
		
		
	}

	private Object instantiateBean(BeanDefinition bd) {
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
