package group11.stream.input;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Generic interface for input stream
 * @author JML
 *
 */
public interface GenericInputStreamInterface {
	public void open(String filePath) throws FileNotFoundException, IOException;
	public Integer readNext() throws IOException;
	public boolean isEndOfStream() throws IOException;
	public void close() throws IOException;
	
}
