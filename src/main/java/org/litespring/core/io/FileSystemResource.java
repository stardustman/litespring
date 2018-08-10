package org.litespring.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.litespring.utils.ClassUtils;

public class FileSystemResource implements Resource {
	private String path;

	private File file;

	private ClassLoader classLoader;

	public FileSystemResource(String path) {
	     this(path, (ClassLoader)null);
	}

	public FileSystemResource(String path, ClassLoader classLoader) {
		this.path = path;
		this.classLoader = (classLoader != null) ? classLoader : ClassUtils.getDefaultClassLoader();
	}

	public InputStream getInputStream() throws IOException {
		InputStream is = this.classLoader.getResourceAsStream(path);
		if (is == null) {
			throw new FileNotFoundException(path + " can be opened");
		}
		return is;
	}

	public String getDescription() {
		return "file" + "[" + this.file.getAbsolutePath() + "]";
	}

}
