package org.litespring.beans.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.utils.ClassUtils;

public class DefaultBeanFactory implements BeanFactory {

	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	public DefaultBeanFactory(String configFile) {
		loadBeanDefinition(configFile);
	}

	public BeanDefinition getBeanDefinition(String beanID) {
		return this.beanDefinitionMap.get(beanID);
	}

	// bean definition change to bean instance
	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if (bd == null) {
			throw new BeanCreationException("Bean Definiton does not exist");
		}
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();

		} catch (Exception e) {
			throw new BeanCreationException("create bean for "+ beanClassName + "failed",e);
		} 

	}

	private void loadBeanDefinition(String configFile) {
		System.out.println(configFile);
		InputStream is = null;
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		is = cl.getResourceAsStream(configFile);
		SAXReader reader = new SAXReader();
		try {
			// XML -> Document
			Document doc = reader.read(is);
			Element root = doc.getRootElement(); // <beans></beans>
			Iterator<Element> iter = root.elementIterator();
			while (iter.hasNext()) {
				Element ele = (Element) iter.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
				this.beanDefinitionMap.put(id, bd);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
