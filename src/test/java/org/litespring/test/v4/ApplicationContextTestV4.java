package org.litespring.test.v4;



import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v4.PetStoreService;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年9月9日 下午5:08:19
 * @version 1.0
 * @description
 */
public class ApplicationContextTestV4 {

	@Test
	public void testGetBeanProperty() {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
		 PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
		 
		 Assert.assertNotNull(petStore.getAccountDao());
		 Assert.assertNotNull(petStore.getItemDao());
	}

}
