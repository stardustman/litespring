package org.litespring.test.v1;


import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.support.ClassPathXmlApplicationContext;
import org.litespring.beans.support.FileSystemXmlApplicationContext;
import org.litespring.context.ApplicationContext;
import org.litespring.service.v1.PetStoreService;


public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
	}
	
	@Test
	public void testGetBeanFromFileSystemXmlApplicationContext(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("E:\\petstore-v1.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
	}

}
