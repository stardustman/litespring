package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.beans.support.BeanDefinitionValueResolver;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.core.io.ClassPathResource;
import org.litespring.dao.v2.AccountDao;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月22日 下午5:58:18
 * @version 1.0
 * @description
 */
public class BeanDefinitionValueResolverTest {

	@Test
	public void testResolveRuntimeBeanReference(){
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
		
		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
		RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
		Object value = resolver.resolveValueIfNecessary(reference);
		
		Assert.assertNotNull(value);
		Assert.assertTrue(value instanceof AccountDao);
		
	}
	
	
	@Test
	public void testResolveTypedValue(){
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
		
		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
		TypedStringValue stringValue = new TypedStringValue("test");
		Object value = resolver.resolveValueIfNecessary(stringValue);
		
		Assert.assertNotNull(value);
		Assert.assertEquals("test", value);
		
	}
}
