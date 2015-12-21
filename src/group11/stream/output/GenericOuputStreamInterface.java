package group11.stream.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Generic interface for output stream
 * @author JML
 *
 */
public interface GenericOuputStreamInterface {
	public void create(String filePath) throws IOException;
	public void write(char c) throws IOException;
	public void write(Integer data) throws IOException;
	public void write(String data) throws IOException;
	public void close() throws FileNotFoundException, IOException;
}
