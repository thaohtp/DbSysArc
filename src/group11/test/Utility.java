package group11.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import group11.stream.input.BufferedInputStream;
import group11.stream.input.BufferedWithSizeInputStream;
import group11.stream.input.GenericInputStreamInterface;
import group11.stream.input.MappingInputStream;
import group11.stream.input.SystemInputStream;
import group11.stream.output.BufferedOutputStream;
import group11.stream.output.BufferedWithSizeOutputStream;
import group11.stream.output.GenericOuputStreamInterface;
import group11.stream.output.MappingOutputStream;
import group11.stream.output.SystemOutputStream;

public class Utility {

	public static Integer MAXIMUM_RANDOM_NUMBER = 65536;
	public static Integer UNSIGNED_MINIMUM_RANDOM_NUMBER = 32768;
	
	public static final int SYSTEM_CALL_IMPLEMENTATION = 1;
	public static final int BUFFERED_IMPLEMENTATION = 2;
	public static final int BUFFERED_WTH_SIZE_IMPLEMENTATION = 3;
	public static final int MAPPING_IMPLEMENTATION = 4;

	// public U outputStream;
	//
	// public Utility() {
	// System.out.println((getClass().getGenericSuperclass()).getTypeName());
	// }
	//
	// /**
	// * Multi-way merge
	// * <p> From d sorted input streams of 32-bit integers, create single
	// output stream in sorted order </p>
	// * @param input: an array of sorted stream
	// * @param sortedOrder: put <code>true</code> if sort in ascending order,
	// <code>false</code> if sort in descending order
	// * @param output: empty output stream instance
	// * @return
	// * @throws IOException
	// * @throws IllegalAccessException
	// * @throws InstantiationException
	// */
	// @SuppressWarnings("rawtypes")
	// public void merge(ArrayList<GenericInputStreamInterface> isArray, boolean
	// isAscending, String outputFile) throws IOException,
	// InstantiationException, IllegalAccessException{
	// // initialize comparator for priority queue
	// Comparator<Integer> comparator = new Comparator<Integer>() {
	// @Override
	// public int compare(Integer o1, Integer o2) {
	// if((isAscending && o1 > o2) || (!isAscending && o1 < o2)){
	// return 1;
	// }
	// else
	// {
	// if((isAscending && o1 < o2) || (!isAscending && o1 > o2)){
	// return -1;
	// }
	// else
	// return 0;
	// }
	//
	// }
	// };
	//
	// PriorityQueue<Integer> queue = new PriorityQueue<>(comparator);
	//
	// // push elements into priority queue
	// for(int i =0; i<isArray.size(); i++){
	// GenericInputStreamInterface stream = isArray.get(i);
	// while(!stream.isEndOfStream()){
	// queue.add(stream.readNext());
	// }
	// }
	// System.out.print(outputStream.getClass().toString());
	// outputStream = (U) (outputStream.getClass().newInstance());
	// outputStream.create(outputFile);
	// // pop element from queue to output stream
	// while(!queue.isEmpty()){
	// Integer element = queue.poll();
	// outputStream.write(element);
	// }
	// outputStream.close();
	//// return outputStream;
	//
	// }

	/**
	 * Multi-way merge
	 * <p>
	 * From d sorted input streams of 32-bit integers, create single output
	 * stream in sorted order
	 * </p>
	 * 
	 * @param input:
	 *            an array of sorted stream
	 * @param sortedOrder:
	 *            put <code>true</code> if sort in ascending order,
	 *            <code>false</code> if sort in descending order
	 * @param output:
	 *            empty output stream instance
	 * @return
	 * @throws IOException
	 */
	public static GenericOuputStreamInterface merge(ArrayList<GenericInputStreamInterface> inputStreamArray,
			boolean isAscending, String outputFile, Integer outputImplementation) throws IOException {
		// initialize comparator for priority queue
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if ((isAscending && o1 > o2) || (!isAscending && o1 < o2)) {
					return 1;
				} else {
					if ((isAscending && o1 < o2) || (!isAscending && o1 > o2)) {
						return -1;
					} else
						return 0;
				}

			}
		};

		PriorityQueue<Integer> queue = new PriorityQueue<>(comparator);

		// push elements into priority queue
		for (int i = 0; i < inputStreamArray.size(); i++) {
			GenericInputStreamInterface stream = inputStreamArray.get(i);
			while (!stream.isEndOfStream()) {
				queue.add(stream.readNext());
			}
			// close stream after reading
			stream.close();
		}

		// pop element from queue to output stream
		GenericOuputStreamInterface outputStream = createOutputStreamInstance(outputImplementation);
		outputStream.create(outputFile);
		while (!queue.isEmpty()) {
			Integer element = queue.poll();
			outputStream.write(element);
		}
		outputStream.close();
		return outputStream;

	}

	/**
	 * Depend on the implementation number user want to use, create stream instance
	 * @param implementationNumber
	 * @return {@link GenericInputStreamInterface}
	 */
	public static GenericInputStreamInterface createInputStreamInstance(Integer implementationNumber){
		switch (implementationNumber) {
		case SYSTEM_CALL_IMPLEMENTATION:
			return new SystemInputStream();
		case BUFFERED_IMPLEMENTATION:
			return new BufferedInputStream();
		case BUFFERED_WTH_SIZE_IMPLEMENTATION:
			return new BufferedWithSizeInputStream();
		case MAPPING_IMPLEMENTATION:
			return new MappingInputStream();
		default:
			return new SystemInputStream();
		}
	}
	
	/**
	 * Depend on the implementation number user want to use, create stream instance
	 * @param implementationNumber
	 * @return {@link GenericOuputStreamInterface}
	 */
	public static GenericOuputStreamInterface createOutputStreamInstance(Integer implementationNumber){
		switch (implementationNumber) {
		case SYSTEM_CALL_IMPLEMENTATION:
			return new SystemOutputStream();
		case BUFFERED_IMPLEMENTATION:
			return new BufferedOutputStream();
		case BUFFERED_WTH_SIZE_IMPLEMENTATION:
			return new BufferedWithSizeOutputStream();
		case MAPPING_IMPLEMENTATION:
			return new MappingOutputStream();
		default:
			return new SystemOutputStream();
		}
	}

	
	public static void mergeSort(String inputFile, Integer memCapacity, Integer streamCount, String outputFile, Integer readImplementation, Integer writeImplementation)
			throws FileNotFoundException, IOException {

		// 1.Read single and split into (size of file)/(memCapacity) arrays
		GenericInputStreamInterface inputStream = createInputStreamInstance(readImplementation);
		inputStream.open(inputFile);
		ArrayList<String> referenceList = new ArrayList<>();

		ArrayList<ArrayList<Integer>> intMatrix = new ArrayList<>();
		ArrayList<Integer> intList = new ArrayList<>();
		while (!inputStream.isEndOfStream()) {
			Integer element = inputStream.readNext();
			intList.add(element);
			// check size of each list at most memCapacity
			if (intList.size() == memCapacity) {
				intMatrix.add(intList);
				intList = new ArrayList<>();
			}
		}

		if (intList.size() < memCapacity) {
			intMatrix.add(intList);
		}

		// 2. Sort each array and put to each output stream
		String tempDirectory = "D:\\tempLab";
		String txtFileName = "temp";
		for (int i = 0; i < intMatrix.size(); i++) {
			ArrayList<Integer> temp = intMatrix.get(i);
			Collections.sort(temp);

			GenericOuputStreamInterface outputStream = createOutputStreamInstance(writeImplementation);
			String txtFilePath = (tempDirectory + "\\" + txtFileName + i);
			outputStream.create(txtFilePath);
			for (int j = 0; j < temp.size(); j++) {
				outputStream.write(temp.get(j));
			}
			outputStream.close();
			referenceList.add(txtFilePath);
		}

		// 3.Store reference of output stream

		// 4.Loop through references of output stream
		// 4.1 Get streamCount first streams
		// 4.2 Merge those streams into one single stream
		// 4.3 Put result at the end
		// 4.4 Loop until one single stream left
		int refSize = referenceList.size();
		while (referenceList.size() > 1) {
			refSize++;
			int toIndex = streamCount;
			if (referenceList.size() < streamCount) {
				toIndex = referenceList.size();
			}

			// 4.1 Get streamCount first streams
			List<String> processedRef = referenceList.subList(0, toIndex);
			ArrayList<GenericInputStreamInterface> streamList = new ArrayList<>();
			// create arraylist of input streams

			while (processedRef.size() > 0) {
				GenericInputStreamInterface tempStream = createInputStreamInstance(readImplementation);
				tempStream.open(processedRef.get(0));
				streamList.add(tempStream);
				processedRef.remove(0);
			}

			// 4.2 Merge those streams into one single stream
			String txtFilePath = (tempDirectory + "\\" + txtFileName + refSize);
			Utility.merge(streamList, true, txtFilePath, writeImplementation);

			// remember to close stream after processing
			for (int m = 0; m < streamList.size(); m++) {
				streamList.get(m).close();
			}
			// 4.3 Put result at the end
			referenceList.add(txtFilePath);
		}

		// Copy result file from tempLab directory to another place
		GenericOuputStreamInterface outputStream = createOutputStreamInstance(writeImplementation);
		outputStream.create(outputFile);
		GenericInputStreamInterface tempResStream = createInputStreamInstance(readImplementation);
		tempResStream.open(referenceList.get(0));
		while (!tempResStream.isEndOfStream()) {
			Integer temp = tempResStream.readNext();
			outputStream.write(temp);
		}

		// Close stream
		tempResStream.close();
		outputStream.close();

		// Delete temporary directory
//		Utility.cleanUpDirectory(tempDirectory);

	}

	private static void cleanUpDirectory(String directoryPath) {
		File file = new File(directoryPath);
		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length != 0) {
				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					fileDelete.delete();
				}

			}

		}
	}

	public static void generateRandomFile(Integer numberPerFile, Integer numberOfFiles, String outputDirectory, String filePrefix, String fileSuffix) throws IOException {
		Random random=new Random();
		for(int i =0; i< numberOfFiles; i++){
			String filePath = outputDirectory + "\\" + filePrefix + i + "." + fileSuffix;
			GenericOuputStreamInterface outputStream = new SystemOutputStream();
			outputStream.create(filePath);
			for(int j = 0; j< numberPerFile; j++){				
				int randomNumber=(random.nextInt(MAXIMUM_RANDOM_NUMBER + UNSIGNED_MINIMUM_RANDOM_NUMBER) - UNSIGNED_MINIMUM_RANDOM_NUMBER);
				outputStream.write(randomNumber);
			}
			outputStream.close();
		}
	}
}
