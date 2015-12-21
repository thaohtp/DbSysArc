package group11.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static final int SYSTEM_CALL_IMPLEMENTATION = 1;
	public static final int BUFFERED_IMPLEMENTATION = 2;
	public static final int BUFFERED_WTH_SIZE_IMPLEMENTATION = 3;
	public static final int MAPPING_IMPLEMENTATION = 4;
	
	// public static void method1_Read(String file_ruta) throws IOException{
	// SystemInputStream input = new SystemInputStream();
	// input.open(file_ruta);
	// while(!input.isEndOfStream()){
	// int number = input.readNext();
	// System.out.println(number);
	// }
	// }
	// public static void method1_write (String file_ruta, char c) throws
	// IOException{
	// SystemOutputStream output = new SystemOutputStream();
	// File file= output.create(file_ruta);
	// output.write(c,file);
	//
	// }
	
	public static void runGenerateFiles(){
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    try {
			
			System.out.println("Enter number of generated number per file: ");
			String s = bufferRead.readLine();
			int numberPerFile = Integer.parseInt(s.trim());
			System.out.println("Enter number of generated files: ");
			s = bufferRead.readLine();
			int numberOfFiles = Integer.parseInt(s.trim());
			System.out.println("Enter directory path: ");
			s = bufferRead.readLine();
			String directory = s.trim();
			System.out.println("The file will be generated with name in format <Prefix><Sequence_number>.<suffix>");
			System.out.println("Enter file name prefix: ");
			s = bufferRead.readLine();
			String prefix = s.trim();
			System.out.println("Enter file name suffix: ");
			s = bufferRead.readLine();
			String suffix = s.trim();
			
			Utility.generateRandomFile(numberPerFile, numberOfFiles, directory, prefix, suffix);
			System.out.println("Task completed. Please check your file at " + directory);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static void runReadWriteFileTest(){
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    try {
			
			System.out.println("What test do you want to run");
			System.out.println("\t 1. Read and print out single file (Test InputStream)");
			System.out.println("\t 2. Read all K files in specific directory and create copied file. This testcase is used for testing opening K streams, reading/writing each stream, N times");
			System.out.println("\t Enter the test you wanna run (default 1): ");
			String s = bufferRead.readLine();
			int testcase = Integer.parseInt(s.trim());
			if(testcase != 2){
				System.out.println("Enter file path: ");
				s = bufferRead.readLine();
				String filePath = s.trim();
				System.out.println("Enter implementation number you want to test: ");
				System.out.println("\t 1. Stream use system call: ");
				System.out.println("\t 2. Buffered stream: ");
				System.out.println("\t 3. Buffered stream with size: ");
				System.out.println("\t 4. Mapping stream: ");
				s = bufferRead.readLine();
				Integer implementation = Integer.parseInt(s.trim());
				if(implementation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
					System.out.println("\t -> Enter buffer size: ");
					s = bufferRead.readLine();
					Integer bufferSize = Integer.parseInt(s.trim());
					TimerTest.runReadSingleTestCase(filePath, implementation, bufferSize, 0);
				}
				else{
					if(implementation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
						System.out.println("\t -> Enter mapping capacity size: ");
						s = bufferRead.readLine();
						Integer memCapacity = Integer.parseInt(s.trim());
						TimerTest.runReadSingleTestCase(filePath, implementation, 0, memCapacity);
					}
					else{
						TimerTest.runReadSingleTestCase(filePath, implementation, 0, 0);
					}
				}
				System.out.println("Task completed!");
			}
			else{
				System.out.println("Enter directory path of reading files: ");
				s = bufferRead.readLine();
				String readingDir = s.trim();
				System.out.println("Enter directory path of copied files: ");
				s = bufferRead.readLine();
				String copiedDir = s.trim();
				System.out.println("Enter expected file path of file which store execution time: ");
				s = bufferRead.readLine();
				String execTimeDir = s.trim();
				System.out.println("The file will be generated with name in format <Prefix><Sequence_time>_<Sequence_number>.<suffix>");
				System.out.println("Enter file name prefix: ");
				s = bufferRead.readLine();
				String prefix = s.trim();
				System.out.println("Enter file name suffix: ");
				s = bufferRead.readLine();
				String suffix = s.trim();
				System.out.println("Enter N times: ");
				s = bufferRead.readLine();				
				int N = Integer.parseInt(s.trim());
				System.out.println("Enter implementation number you want to test: ");
				System.out.println("\t 1. Stream use system call: ");
				System.out.println("\t 2. Buffered stream: ");
				System.out.println("\t 3. Buffered stream with size: ");
				System.out.println("\t 4. Mapping stream: ");
				s = bufferRead.readLine();				
				int implementation = Integer.parseInt(s.trim());
				if(implementation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
					System.out.println("\t -> Enter buffer size: ");
					s = bufferRead.readLine();
					Integer bufferSize = Integer.parseInt(s.trim());
				}
				else{
					if(implementation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
						System.out.println("\t -> Enter mapping capacity size: ");
						s = bufferRead.readLine();
						Integer memCapacity = Integer.parseInt(s.trim());
					}
					else{
					}
				}
				System.out.println("Task completed! Please check your file at " + copiedDir);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void runMergeSortTest(){
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String s;
		try {
			System.out.println("Enter file path: ");
			s = bufferRead.readLine();
			String filePath = s.trim();
			System.out.println("Enter expected file path of result file: ");
			s = bufferRead.readLine();
			String resultPath = s.trim();
			System.out.println("Enter expected file path of file which store execution time: ");
			s = bufferRead.readLine();
			String execTimeFilePath = s.trim();			
			System.out.println("Enter number of available main memory: ");
			s = bufferRead.readLine();
			Integer M = Integer.parseInt(s.trim());
			System.out.println("Enter number of stream to merge in one pass: ");
			s = bufferRead.readLine();
			Integer d = Integer.parseInt(s.trim());		
			
			// ask for implementation of reading
			Integer readBufferSize = 0;
			Integer readMemCapacity = 0;
			System.out.println("Enter implementation number you want to use for reading: ");
			System.out.println("\t 1. Stream use system call: ");
			System.out.println("\t 2. Buffered stream: ");
			System.out.println("\t 3. Buffered stream with size: ");
			System.out.println("\t 4. Mapping stream: ");
			s = bufferRead.readLine();
			Integer readImplentation = Integer.parseInt(s.trim());	
			
			if(readImplentation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
				System.out.println("\t -> Enter buffer size for reading: ");
				s = bufferRead.readLine();
				readBufferSize = Integer.parseInt(s.trim());
			}
			else{
				if(readImplentation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
					System.out.println("\t -> Enter mapping capacity size for reading: ");
					s = bufferRead.readLine();
					readMemCapacity = Integer.parseInt(s.trim());
				}
			}
			
			// ask for implementation of writing
			Integer writeBufferSize = 0;
			Integer writeMemCapacity = 0;
			System.out.println("Enter implementation number you want to use for writing: ");
			System.out.println("\t 1. Stream use system call: ");
			System.out.println("\t 2. Buffered stream: ");
			System.out.println("\t 3. Buffered stream with size: ");
			System.out.println("\t 4. Mapping stream: ");
			s = bufferRead.readLine();
			Integer writeImplemetation = Integer.parseInt(s.trim());
			if(writeImplemetation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
				System.out.println("\t -> Enter buffer size for writing: ");
				s = bufferRead.readLine();
				writeBufferSize = Integer.parseInt(s.trim());
			}
			else{
				if(writeImplemetation ==  BUFFERED_WTH_SIZE_IMPLEMENTATION){
					System.out.println("\t -> Enter mapping capacity size for writing: ");
					s = bufferRead.readLine();
					writeMemCapacity = Integer.parseInt(s.trim());
				}
			}
			System.out.println("Enter number of time you want to run test: ");
			s = bufferRead.readLine();
			Integer nbRunTimes = Integer.parseInt(s.trim());
			TimerTest.runMergeSortTestCase(filePath, resultPath, execTimeFilePath, M, d, readImplentation, readBufferSize, readMemCapacity, writeImplemetation, writeBufferSize, writeMemCapacity, nbRunTimes);
			System.out.println("Task completed! Please check your file at " + resultPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

		/*
		 * String file_ruta1 = "D:\\Test.txt"; GenericInputStreamInterface input
		 * = new BufferedWithSizeInputStream(); System.out.println(
		 * "Total execution time: " + TimerTest.readTest(input, file_ruta1) +
		 * " ms"); input.close();
		 * 
		 * String file_ruta5 = "D:\\Test.txt"; GenericInputStreamInterface
		 * input2 = new BufferedInputStream(); System.out.println(
		 * "Total execution time: " + TimerTest.readTest(input2, file_ruta1) +
		 * " ms"); input2.close(); /* String file_ruta2 = "D:\\test2.txt";
		 * SystemOutputStream output = new SystemOutputStream();
		 * System.out.println("Total execution time: " +
		 * TimerTest.writeTest(output, file_ruta2, "3 2 1") + " ms");
		 * 
		 * String file_ruta3 = "D:\\test3.txt"; BufferedOutputStream output2 =
		 * new BufferedOutputStream(); System.out.println(
		 * "Total execution time: " + TimerTest.writeTest(output2, file_ruta3,
		 * "3 2 1") + " ms");
		 * 
		 * String file_ruta4 = "D:\\test4.txt"; BufferedOutputStream output3 =
		 * new BufferedOutputStream(); System.out.println(
		 * "Total execution time: " + TimerTest.writeTest(output3, file_ruta4,
		 * "3 2 1") + " ms");
		 */

		// String file_ruta6 = "D:\\Test.txt";
		// GenericInputStreamInterface input6 = new MappingInputStream();
		// System.out.println("Total execution time: " +
		// TimerTest.readTest(input6, file_ruta6) + " ms");
		// /*String file_ruta4 = "C:\\test2.txt";
		// SystemInputStream SIS = new SystemInputStream();
		// SIS.open(file_ruta4);
		// SIS.fread();
		// */

		// BELOW CODE IS USED FOR TEST MULTI-WAY MERGE

		// String file1 = "D:\\test.txt";
		// String file2 = "D:\\test2.txt";
		// GenericInputStreamInterface istream1 = new SystemInputStream();
		// istream1.open(file1);
		// GenericInputStreamInterface istream2 = new SystemInputStream();
		// istream2.open(file2);
		//
		//
		// GenericOuputStreamInterface ostream = new MappingOutputStream();
		// String output = "D:\\output.txt";
		// ostream.create(output);
		//
		// ArrayList<GenericInputStreamInterface> isArray = new ArrayList<>();
		// isArray.add(istream1);
		// isArray.add(istream2);
		// Utility.merge(isArray, false, ostream);
		//
		// SystemInputStream testStream = new SystemInputStream();
		// System.out.println("Test: ");
		// TimerTest.readTest(testStream,output);

//		Utility.mergeSort("D:\\merge.txt", 3, 2, "D:\\result.txt");

		Utility.generateRandomFile(100, 3, "D:\\tempLab\\", "generate", "txt");
//		runGenerateFiles();
//		runReadWriteFileTest();
		TimerTest.runMergeSortTestCase("D:\\tempLab\\generate0.txt", "D:\\tempLab\\result.txt", "D:\\tempLab\\exec.txt", 30, 3, 1, 0, 0, 1, 0, 0, 10);
	}

}
