package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.support.BeanDefinitionRegistry;
import org.litespring.beans.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.utils.ClassUtils;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月7日 下午12:53:27
 * @version 1.0
 * @description
 */
public class XMLBeanDefinitionReader {
	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	private static final String SCOPE_ATTRIBUTE = "scope";
	

    BeanDefinitionRegistry registry;

	public XMLBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry =  registry;
	}
	

	@SuppressWarnings("unchecked")
	public void loadBeanDefinitions(Resource resource) {
		//InputStream is = null;
		//ClassLoader cl = ClassUtils.getDefaultClassLoader();
		//is = cl.getResourceAsStream(configFile);
		InputStream is = null;
		try {
			is = resource.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(is);
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
				
				//实例的scope
				if(ele.attribute(SCOPE_ATTRIBUTE) != null){
					bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
				}
				
				this.registry.registerBeanDefinition(id, bd);
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreException("IOException parsing XML");
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
