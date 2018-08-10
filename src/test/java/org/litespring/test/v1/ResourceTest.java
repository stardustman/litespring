package org.litespring.test.v1;


import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class ResourceTest {

	@Test
	public void testClassPathResource() {
		Resource resource = new ClassPathResource("petstore-v1.xml");
		InputStream is = null;
		try {
			try {
				is = resource.getInputStream();
				Assert.assertNotNull(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} 
		 finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Test
	public void testFileSystemResource(){
		Resource resource = new FileSystemResource("E:\\petstore-v1.xml");
		InputStream is = null;
		try {
			try {
				is = resource.getInputStream();
				Assert.assertNotNull(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} 
		 finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
