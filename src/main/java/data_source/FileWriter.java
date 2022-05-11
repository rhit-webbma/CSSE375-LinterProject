package data_source;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileWriter {
	
private PrintStream stream;
	
	public FileWriter(String filename) {
		try {
			this.stream = new PrintStream(new FileOutputStream(filename, false));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(String log) {
		stream.printf(log);
	}

}
