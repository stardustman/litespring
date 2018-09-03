package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.support.BeanDefinitionRegistry;
import org.litespring.beans.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.utils.ClassUtils;
import org.litespring.utils.StringUtils;

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

	public static final String PROPERTY_ELEMENT = "property";
	public static final String REF_ATTRIBUTE = "ref";
	public static final String VALUE_ATTRIBUTE = "value";
	public static final String NAME_ATTRIBUTE = "name";

	/**
	 * 构造注入对应的XML
	 */
	public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
	public static final String TYPE_ATTRIBUTE = "type";

	BeanDefinitionRegistry registry;

	protected final Log logger = LogFactory.getLog(getClass());

	public XMLBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	@SuppressWarnings("unchecked")
	public void loadBeanDefinitions(Resource resource) {
		// InputStream is = null;
		// ClassLoader cl = ClassUtils.getDefaultClassLoader();
		// is = cl.getResourceAsStream(configFile);
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

				// 实例的scope
				if (ele.attribute(SCOPE_ATTRIBUTE) != null) {
					bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
				}
				/**
				 * 解析构造注入的参数
				 */
				parseConstructorArgElements(ele, bd);
				parsePropertyElement(ele, bd);
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

	private void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
          Iterator iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
	      while(iter.hasNext()){
	    	  Element ele =(Element) iter.next();
	    	  parseConstructorArgElement(ele, bd);
	      }
	}
	
	private void  parseConstructorArgElement(Element ele, BeanDefinition bd){
		String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
		String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
		Object value = parsePropertyValue(ele, bd, null);
		ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
		if (StringUtils.hasLength(typeAttr)) {
			valueHolder.setType(typeAttr);
		}
		if(StringUtils.hasLength(nameAttr)){
			valueHolder.setName(nameAttr);
		}
		bd.getConstructorArgument().addArgumentValue(valueHolder);
	}

	public void parsePropertyElement(Element beanElem, BeanDefinition bd) {
		Iterator iter = beanElem.elementIterator(PROPERTY_ELEMENT);
		while (iter.hasNext()) {
			Element propElem = (Element) iter.next();
			String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
			if (!StringUtils.hasLength(propertyName)) {
				logger.fatal("Tag 'property' must have a 'name' attribute ");
				return;
			}
			Object val = parsePropertyValue(propElem, bd, propertyName);
			PropertyValue pv = new PropertyValue(propertyName, val);
			bd.getPropertyValues().add(pv);
		}

	}

	private Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
		String elementName = (propertyName != null) ? "<property> element for property'" + propertyName + "'"
				: "<constructor-arg> element";
		boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);
		boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);

		if (hasRefAttribute) {
			String refName = ele.attributeValue(REF_ATTRIBUTE);
			if (!StringUtils.hasText(refName)) {
				logger.error(elementName + " contains empty 'ref' attribute");
			}
			RuntimeBeanReference ref = new RuntimeBeanReference(refName);
			return ref;
		} else if (hasValueAttribute) {
			TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
			return valueHolder;
		} else {
			throw new RuntimeException(elementName + " must specify a ref or value");
		}
	}

}
