package group11.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import group11.stream.input.BufferedInputStream;
import group11.stream.input.BufferedWithSizeInputStream;
import group11.stream.input.GenericInputStreamInterface;
import group11.stream.input.MappingInputStream;
import group11.stream.input.SystemInputStream;
import group11.stream.output.GenericOuputStreamInterface;
import group11.stream.output.SystemOutputStream;

/**
 * This class is used for recording execution time of a test case
 * @author JML
 *
 */
public class TimerTest {
	
	public static final int SYSTEM_CALL_IMPLEMENTATION = 1;
	public static final int BUFFERED_IMPLEMENTATION = 2;
	public static final int BUFFERED_WTH_SIZE_IMPLEMENTATION = 3;
	public static final int MAPPING_IMPLEMENTATION = 4;
	
	
	/**
	 * Test reading file by input stream
	 * @param input
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public static Long readTest(GenericInputStreamInterface input, String filePath) throws IOException{
		long startTime = System.currentTimeMillis();
	      try {
			input.open(filePath);
			try {
				while(!input.isEndOfStream()){
					  int number = input.readNext();
					  System.out.println(number);
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      input.close();
	      long endTime = System.currentTimeMillis();
	      return endTime - startTime;
   }
	
	/**
	 * Test writing file by output stream
	 * @param output
	 * @param filePath
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public static Long writeTest(GenericOuputStreamInterface output, String filePath, String data) throws IOException{
		long startTime = System.currentTimeMillis();
		output.create(filePath);
		for (char c : data.toCharArray()) {
		       output.write(c);
		}
		output.close();
		long endTime = System.currentTimeMillis();
	    return endTime - startTime;
	}
	
	public static void runReadSingleTestCase(String filePath, Integer implementation, Integer bufferedSize, Integer mapCapacity) throws FileNotFoundException, IOException{
		GenericInputStreamInterface inputStream;
		switch (implementation) {
		case SYSTEM_CALL_IMPLEMENTATION:
			inputStream = new SystemInputStream();
			break;
		case BUFFERED_IMPLEMENTATION:
			inputStream = new BufferedInputStream();
			break;
		case BUFFERED_WTH_SIZE_IMPLEMENTATION:
			inputStream = new BufferedWithSizeInputStream();
			break;
		case MAPPING_IMPLEMENTATION:
			inputStream = new MappingInputStream();
		default:
			inputStream = new SystemInputStream();
			break;
		}
		Long execTime = readTest(inputStream, filePath);
		inputStream.close();
		System.out.println("Execution time: " + execTime + "ms");
		
	}
	
	public static void runReadWriteKStreamTestCase(String readingDirectory, String copiedDirectory, String execTimeFilePath, String prefix, String suffix, Integer nTime, Integer bufferSize, Integer mapCapacity){
		// Should we put in thread
	}
	
	public static void runMergeSortTestCase(String readFilePath, String resultFilePath, String execTimeFilePath, Integer availableMem, Integer nbStreamPerPass, Integer readImplemenation,Integer readBufferSize, Integer readMemCapacity, Integer writeImplementation, Integer writeBufferSize, Integer writeMemCapacity, Integer nbRunTimes) throws FileNotFoundException, IOException{
		SystemOutputStream execTimeStream = new SystemOutputStream();
		execTimeStream.create(execTimeFilePath);
		for(int i =0; i< nbRunTimes; i++){			
			Long startTime = System.currentTimeMillis();
			Utility.mergeSort(readFilePath, availableMem, nbStreamPerPass, resultFilePath, readImplemenation, writeImplementation);
			Long endTime = System.currentTimeMillis();		
			System.out.println((i+1) + ".Execution time: " + (endTime - startTime) + "ms");
			// write execution to disk
			execTimeStream.write(((Long)(endTime - startTime)).toString());
			execTimeStream.write(" ");
		}
		execTimeStream.close();
	}
}

