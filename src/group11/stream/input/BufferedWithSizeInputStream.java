package group11.stream.input;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedWithSizeInputStream implements GenericInputStreamInterface{

	DataInputStream stream;
	FileInputStream fis;
	
	@Override
	public void open(String filePath) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//Change the size of the stream wanted
		Integer size=15;
		File file = new File(filePath);
		fis = new FileInputStream(file);
		java.io.BufferedInputStream buf = new java.io.BufferedInputStream(fis,size);
		stream = new DataInputStream(buf);
	}

	@Override
	public Integer readNext() throws IOException {
		// TODO Auto-generated method stub
		char nextChar = 0;
		StringBuffer content = new StringBuffer("");
		while((stream.available()>0 && nextChar != ' ' )){
			nextChar = (char) stream.read();
			content.append(nextChar);				
		}		
		if(content.toString().compareTo(" ") == 0)
		{
			return null;
		}
		return Integer.parseInt(content.toString().trim());
	}

	@Override
	public boolean isEndOfStream() throws IOException {
		// TODO Auto-generated method stub
		if(stream.available() > 0){
			return false;
		}
		else
			return true;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		stream.close();
		
	}

}
