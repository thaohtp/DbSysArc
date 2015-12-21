package group11.stream.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Implementation of output stream using system calls for writing
 * 
 * @author JML
 *
 */
public class SystemOutputStream implements GenericOuputStreamInterface {
	FileOutputStream fos;
	Writer writer;

	@Override
	public void create(String filePath) throws IOException {

		// FileOutputStream fos = null;
		File file = null;
		try {
			// create file output stream
			file = new File(filePath);
			fos = new FileOutputStream(file);
			// create a new OutputStreamWriter
			writer = new OutputStreamWriter(fos, "utf-8");

		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
	}

	@Override
	public void write(char c) throws IOException {
		try {

			// write something in the file
			writer.write(c);
			writer.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void write(String data) throws IOException {
		try {
			// write something in the file
			writer.write(data);
			writer.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void write(Integer data) throws IOException {
		try {
			// write something in the file
			writer.write(data.toString());
			writer.write(" ");
			writer.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void close() throws FileNotFoundException {
		// flush the stream
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
