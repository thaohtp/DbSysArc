package group11.stream.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class BufferedOutputStream implements GenericOuputStreamInterface {
	Writer writer;
	FileOutputStream fos;

	@Override
	public void create(String filePath) throws IOException {
		// TODO Auto-generated method stub
		// FileOutputStream fos = null;
		File file = null;
		try {
			// create file output stream
			file = new File(filePath);

			// create a new OutputStreamWriter
			fos = new FileOutputStream(file);
			java.io.BufferedOutputStream buf = new java.io.BufferedOutputStream(fos);
			writer = new BufferedWriter(new OutputStreamWriter(buf, "UTF-8"));

		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
	}

	public void write(char c) throws IOException {
		try {

			// OutputStream os = new FileOutputStream(file);
			// OutputStreamWriter writer = new OutputStreamWriter(os);

			// write something in the file
			writer.write(c);
			writer.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

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
