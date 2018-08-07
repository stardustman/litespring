package org.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {
	 DefaultBeanFactory factory = null;
	 XMLBeanDefinitionReader reader = null;
	 
	 /**
	  * 每一个测试用例都会执行 before 作为钩子 hook
	  */
     @Before
     public void setUP(){
    	factory = new DefaultBeanFactory();
    	System.out.println(factory);
 		reader = new XMLBeanDefinitionReader(factory);
 		System.out.println(reader);
     }
	
	
	@Test
	public void testGetBean() {
		/*DefaultBeanFactory factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);*/
		
		reader.loadBeanDefinitions("petstore-v1.xml");
	
		BeanDefinition bd = (BeanDefinition) factory.getBeanDefinition("petStore");
		// 3:判断类型
		assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName() );
		// 4:得到实例
		PetStoreService petStore = (PetStoreService) factory.getBean("petStore");
		assertNotNull(petStore);
	}

	@Test
	public void testInvalidBean() {
		/*DefaultBeanFactory factory = new DefaultBeanFactory();
		XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);*/

		reader.loadBeanDefinitions("petstore-v1.xml");
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			return;
		}
		Assert.fail("expect BeanCreationException");
	}

	@Test
	public void testInvalidXML() {

		try {
			DefaultBeanFactory factory = new DefaultBeanFactory();
			XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
			
			reader.loadBeanDefinitions("xxxx.xml");
		} catch (BeanDefinitionStoreException e) {
			return;
		}
		Assert.fail("expect DefinitionStoreException");
	}
}
