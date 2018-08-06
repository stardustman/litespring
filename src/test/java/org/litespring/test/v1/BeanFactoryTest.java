package org.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;




public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		//1:解析XML获取bean工厂
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		//2:获取petStore的bean定义
		BeanDefinition bd = factory.getBeanDefinition("petStore");
        //3:判断类型
		assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());
		//4:得到实例
		PetStoreService petStore = (PetStoreService) factory.getBean("petStore");
		assertNotNull(petStore);
	}
	
	@Test
	public void testInvalidBean(){
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			return;
		}
		Assert.fail("expect BeanCreationException");
	}

}
