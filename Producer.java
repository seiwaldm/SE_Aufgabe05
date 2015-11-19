import java.io.BufferedReader;
import java.io.IOException;

/**
 * PS Software Engineering WS2015 <br>
 * <br>
 * 
 * Class that reads line by line from a txt-File and puts String into a
 * pre-defined Buffer for further processing
 * 
 * @author Kevin Schoergnhofer, Markus Seiwald
 *
 */
public class Producer implements Runnable {
	private Buffer buffer;
	private BufferedReader file;

	public Producer(Buffer buffer, BufferedReader file) {
		this.buffer = buffer;
		this.file = file;
	}

	/**
	 * lets the Producer start its Job as defined in Class description
	 */
	public void run() {
		BufferedReader br = new BufferedReader(file);
		String item;
		try {
			item = file.readLine();
			while (item != null) {
				buffer.put(item);
				item = file.readLine();
			}
			// put two times "DONE" to terminate both consumers (as defined in
			// exercise requirements):
			buffer.put("DONE");
			buffer.put("DONE");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}