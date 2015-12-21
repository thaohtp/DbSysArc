package group11.stream.input;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MappingInputStream implements GenericInputStreamInterface{
	
	FileChannel fc;
	//Size can be changed when we use variable lenght, if we want to load the whole file use fc.size() where the variable is located
	ByteBuffer mb;
	Integer lenght=20;
	
	@SuppressWarnings({ "resource", "static-access" })
	public void open(String filepath) throws IOException{
		File file =new File(filepath);
		fc = new RandomAccessFile(file, "r").getChannel(); 
		mb =  fc.map(
			        FileChannel.MapMode.READ_ONLY, 0, fc.size());
				
	}

	@Override
	public Integer readNext() throws IOException{
		char nextChar = 0;
		StringBuffer content = new StringBuffer("");
		
		/*while (fc.read(mb)>0){
			//mb.flip();
			for (int i = 0; i < mb.limit(); i++){
				nextChar=(char)mb.get();
				content.append(nextChar);
				mb.clear();
			}
			*/
		while(mb.hasRemaining() && nextChar != ' ' ){
				nextChar=(char)mb.get();
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
		if(mb.hasRemaining() ==true){
			return false;
		}
		else
			return true;
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
			mb.clear();
	}

	
	
}
