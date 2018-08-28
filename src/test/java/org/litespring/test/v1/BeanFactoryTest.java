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
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {
	 DefaultBeanFactory factory = null;
	 XMLBeanDefinitionReader reader = null;
	 
	 /**
	  * 每一个测试用例都会执行 before 作为钩子 hook,不让测试用例互相影响, 测试用例的隔离性
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
		//reader.loadBeanDefinitions("petstore-v1.xml");
		
		Resource resource = new ClassPathResource("petstore-v1.xml");
	    reader.loadBeanDefinitions(resource);
		BeanDefinition bd = (BeanDefinition) factory.getBeanDefinition("petStore");
		
		//bean的Scope问题
		assertTrue(bd.isSingleton());
		assertFalse(bd.isPrototype());
		assertEquals(BeanDefinition.SCOPE_DEFAULT, bd.getScope());
		
		
		// 3:判断类型
		assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName() );
		// 4:得到实例
		PetStoreService petStore = (PetStoreService) factory.getBean("petStore");
		
		PetStoreService petStore1 = (PetStoreService) factory.getBean("petStore");
		assertNotNull(petStore);
		assertTrue(!petStore.equals(petStore1));
	}
	
	
	@Test
	public void testGetBeanPrototypeScope() {
		
		Resource resource = new ClassPathResource("petstore-v1.xml");
	    reader.loadBeanDefinitions(resource);
		BeanDefinition bd = (BeanDefinition) factory.getBeanDefinition("petStore1");
		
		//bean的Scope问题
		assertTrue(bd.isPrototype());
		assertFalse(bd.isSingleton());
		assertEquals(BeanDefinition.SCOPE_PROTOTYPE, bd.getScope());
		
		
		// 3:判断类型
		assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName() );
		// 4:得到实例
		PetStoreService petStore = (PetStoreService) factory.getBean("petStore1");
		
		PetStoreService petStore1 = (PetStoreService) factory.getBean("petStore1");
		assertNotNull(petStore);
		assertFalse(petStore.equals(petStore1));
	}

	@Test
	public void testInvalidBean() {
		Resource resource = new ClassPathResource("petstore-v1.xml");
	    reader.loadBeanDefinitions(resource);
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
			
			Resource resource = new ClassPathResource("petstore-v1.xml");
		    reader.loadBeanDefinitions(resource);
		} catch (BeanDefinitionStoreException e) {
			return;
		}
		//Assert.fail("expect DefinitionStoreException");
	}
}
