package group11.stream.input;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Implementation of input stream using system calls for writing
 * @author JML
 *
 */
public class SystemInputStream implements GenericInputStreamInterface{
	DataInputStream stream;
	FileInputStream fis;
	
	public void open(String filePath) throws FileNotFoundException{
		File file = new File(filePath);
		fis = new FileInputStream(file);
		stream = new DataInputStream(fis);
	}
	
	public Integer readNext() throws IOException{
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
	
	public boolean isEndOfStream() throws IOException{
		if(stream.available() > 0){
			return false;
		}
		else
			return true;
		}
	
	public void fread () throws IOException{
		BufferedInputStream bis = new BufferedInputStream( fis );
		stream = new DataInputStream( bis );
		@SuppressWarnings("deprecation")
		String lectura = stream.readLine();
		System.out.println(lectura);
	}
	
	public void close() throws IOException{
		stream.close();
	}
}



