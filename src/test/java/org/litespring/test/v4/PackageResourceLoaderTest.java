package org.litespring.test.v4;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.PackageResourceLoader;
import org.litespring.core.io.Resource;

public class PackageResourceLoaderTest {

	@Test
	public void testGetResouce() throws IOException {
		PackageResourceLoader loader = new PackageResourceLoader();
		Resource[] resources = loader.getResources("org.litespring.dao.v4");
		Assert.assertEquals(2, resources.length);
	}

}
