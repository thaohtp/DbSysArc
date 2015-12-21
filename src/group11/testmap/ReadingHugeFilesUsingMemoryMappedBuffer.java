package group11.testmap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ReadingHugeFilesUsingMemoryMappedBuffer {

	FileChannel fc;
	MappedByteBuffer mb;
	Integer lenght=20;
	//Para corregir poner fc.size() en el lenght
	
	public void open(String filepath) throws IOException{
	File file =new File(filepath);
	fc = new RandomAccessFile(file, "r").getChannel(); 
	mb = (MappedByteBuffer)fc.map(
		        FileChannel.MapMode.READ_ONLY, 0, fc.size());
	
	}
	
	public Integer readNext() throws IOException{
		char nextChar = 0;
		StringBuffer content = new StringBuffer("");
		
		//while (mb.limit()>0)
		//{mb.flip();
	
		System.out.println("Is end of file? " + mb.position());
		
		while(mb.hasRemaining() && nextChar != ' ' ){
			
			nextChar=(char)mb.get();
      	  	content.append(nextChar);			
			}
			//mb.clear();
		//}
			if(content.toString().compareTo(" ") == 0)
			{
				return null;
			}
			return Integer.parseInt(content.toString().trim());
			
		
		}
		
	
	public void close() throws IOException {
		// TODO Auto-generated method stub
			mb.clear();
	}
	
	public boolean isEndOfStream() throws IOException {
		// TODO Auto-generated method stub
		if(mb.hasRemaining() ==true){
			return false;
		}
		else
			return true;
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ReadingHugeFilesUsingMemoryMappedBuffer input=new ReadingHugeFilesUsingMemoryMappedBuffer();
		String filepath="D:\\Test.txt";
		
		input.open(filepath);
		while(!input.isEndOfStream()){
			  int number = input.readNext();
			  System.out.println(number);
		  }
		 input.close();
		
		
			/*File file = new File(filepath);
	        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
	        MappedByteBuffer buffer = fileChannel.map(
	        FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
	        // the buffer now reads the file as if it were loaded in memory. note
	        // that for smaller files it would be faster
	        // to just load the file in memory
	        // lets see if this buffer is loaded fully into memory
	        System.out.println(buffer.isLoaded());
	        char nextChar = 0;
	        StringBuffer content = new StringBuffer("");
	        while(buffer.hasRemaining()){
	        	  nextChar=(char)buffer.get();
	        	  content.append(nextChar);
	        	  System.out.print(nextChar);
	        }
	              
	      
		          /*if(content.toString().compareTo(" ") == 0)
		  		{
	        	   return null;
		  		}*/
		          //System.out.println(Integer.parseInt(content.toString().trim()));
	        // the mappedbytebuffer can be used as a normal buffer to do read and/or
	        // write operations
	        // read the size
	       // System.out.println(buffer.capacity());
	     
	}

}
