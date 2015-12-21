
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		File file = new File("C:/test2.txt");
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);

			System.out.println("Total file size to read (in bytes) : " + fis.available());
			DataInputStream dataStream = new DataInputStream(fis);
			int k = dataStream.readInt();
//			Integer x = new Integer();
			System.out.println(k);

			int content;
			Long from = System.nanoTime();
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
			}
			Long to = System.nanoTime();
			System.out.println("Time for fileinput stream: " + (to - from));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		
//		File file2 = new File("C:\\test2.txt");
//		FileInputStream fis2 = null;
//		BufferedInputStream bis2 = null;
//		DataInputStream dis2 = null;
//
//		try {
//
//			fis2 = new FileInputStream(file2);
//
//			bis2 = new BufferedInputStream(fis2);
//			dis2 = new DataInputStream(bis2);
//
//			Long from = System.nanoTime();
//			while (dis2.available() != 0) {
//				System.out.println(dis2.readLine());
//			}
//			Long to = System.nanoTime();
//			System.out.println("Tim for buffered: " + (to - from));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				fis2.close();
//				bis2.close();
//				// dis2.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//
//		File file3 = new File("C:\\test2.txt");
//		FileInputStream fis3 = null;
//		BufferedInputStream bis3 = null;
//		DataInputStream dis3 = null;
//
//		try {
//			fis3 = new FileInputStream(file3);
//			bis3 = new BufferedInputStream(fis3, 100);
//			dis3 = new DataInputStream(bis3);
//			Long from = System.nanoTime();
//			while (dis3.available() != 0) {
//				System.out.println(dis3.readLine());
//			}
//			Long to = System.nanoTime();
//			System.out.println("Time for buffered with size: " + (to - from));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				fis2.close();
//				bis2.close();
//				// dis2.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}

//		int length = 0x8FFFFFF; // 128 Mb
//
//		MappedByteBuffer out = new RandomAccessFile("C:\\test2.txt", "rw").getChannel()
//				.map(FileChannel.MapMode.READ_WRITE, 0, length);
////		for (int i = 0; i < length; i++) {
////			out.put((byte) 'x');
////		}
//		 // the buffer now reads the file as if it were loaded in memory.
//        System.out.println(out.isLoaded());  //prints false
//        System.out.println(out.capacity());  //Get the size based on content size of file
//         
//        //You can read the file from this buffer the way you like.
//        for (int i = 0; i < out.limit(); i++)
//        {
//            System.out.print((char) out.get()); //Print the content of file
//        }
//		System.out.println("Finished writing");
	}
}