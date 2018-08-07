package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resouce {
	InputStream getInputStream() throws IOException;
	String getDescription();

}
