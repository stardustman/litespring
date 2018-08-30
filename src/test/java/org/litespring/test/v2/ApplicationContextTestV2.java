package org.litespring.test.v2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.litespring.beans.support.ClassPathXmlApplicationContext;
import org.litespring.context.ApplicationContext;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;
import org.litespring.service.v2.PetstoreService;






public class ApplicationContextTestV2 {

	@Test
	public void testGetBeanProperty() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
		PetstoreService petStore = (PetstoreService)ctx.getBean("petStore");
		
		assertNotNull(petStore.getAccountDao());
		assertNotNull(petStore.getItemDao());
		
		assertTrue(petStore.getAccountDao() instanceof AccountDao);
		assertTrue(petStore.getItemDao() instanceof ItemDao);
		
		assertEquals("stardustman",petStore.getOwner());
		assertEquals(2, petStore.getVersion()); 
		
	}

}