package group11.stream.output;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class MappingOutputStream implements GenericOuputStreamInterface {

	MappedByteBuffer stream;
	FileChannel fc;
	
	@SuppressWarnings("resource")
	@Override
	public void create(String filePath) throws IOException {
		Integer capacity = 1;
//		@SuppressWarnings("resource")
//		RandomAccessFile mappedFile = new RandomAccessFile(filePath, "rw");
//		stream = mappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, capacity);
//		

		fc = new RandomAccessFile(filePath, "rw").getChannel();
		stream = fc.map(FileChannel.MapMode.READ_WRITE, 0, capacity);
		
		// stream.wrap(messageToWrite.getBytes(Charset.forName("ISO-8859-1")));
		// OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
		// TODO Auto-generated method stub
		// FileOutputStream fos = null;
		// File file =null;
		// Integer size=10;
		// try{
		// // create file output stream
		// file = new File(filePath);
		//
		// // create a new OutputStreamWriter
		// fos=new FileOutputStream(file);
		// java.io.BufferedOutputStream buf = new
		// java.io.BufferedOutputStream(fos,size);
		// stream = new DataOutputStream(buf);
		//
		// }catch(Exception e){
		// // if any error occurs
		// e.printStackTrace();
		// }
		// return file;
	}

	@Override
	public void write(char c) throws IOException {
		try {

			stream.putChar(c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void write(String data) throws IOException {
		try {
			for (char c : data.toCharArray()) {
				stream.putChar(c);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void write(Integer data) throws IOException {
		try {
			// write something in the file
			Charset charSet = Charset.forName("Cp1252");			
//			fc.write(ByteBuffer.wrap(data.toString().getBytes(charSet)));
//			fc.write(ByteBuffer.wrap(" ".toString().getBytes(charSet)));
			
			fc.write(stream.wrap(data.toString().getBytes(charSet)));
			fc.write(stream.wrap(" ".toString().getBytes(charSet)));
//			stream.putInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
 
	public void close() throws IOException {
		// flush the stream
		fc.close();

	}
}
