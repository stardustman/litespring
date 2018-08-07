package org.litespring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.litespring.utils.Assert;



public class FileSystemResource implements Resouce {
	private String path;
	
	private  File file;

	public FileSystemResource(String path) {
		Assert.notNull(path,"path must not be null");
		this.path = path;
	}
	public InputStream getInputStream() throws IOException {
		
		File file = new File(path);
		return new FileInputStream(file);
	}

	public String getDescription() {
		return "file" + this.file.getAbsolutePath();
	}

}
