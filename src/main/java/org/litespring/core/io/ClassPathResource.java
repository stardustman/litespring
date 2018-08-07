package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

import org.litespring.utils.ClassUtils;

import com.sun.swing.internal.plaf.synth.resources.synth_it;

public class ClassPathResource implements Resouce {
    private String path;
    private ClassLoader classLoader;
    
    public ClassPathResource(String path) {
    	this(path, (ClassLoader)null);
    	
	}
    public ClassPathResource(String path,ClassLoader classLoader) {
    	this.path = path;
    	this.classLoader = (classLoader != null )? classLoader : ClassUtils.getDefaultClassLoader();
	}
	
	public InputStream getInputStream() throws IOException {
		InputStream is = this.classLoader.getResourceAsStream(path);
		if (is != null) {
			return is;
		}
		return null;
	}

	public String getDescription() {
		return this.path;
	}

}
