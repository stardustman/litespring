package org.litespring.test.v2;


import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.support.ClassPathXmlApplicationContext;
import org.litespring.context.ApplicationContext;
import org.litespring.service.v1.PetStoreService;


public class ApplicationContextTestV2 {

	@Test
	public void testGetBean() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
	}
	
	

}
