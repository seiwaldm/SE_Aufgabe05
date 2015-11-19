import java.io.PrintStream;

/**
 * PS Software Engineering WS2015 <br>
 * <br>
 * 
 * Consumer Class that takes instances of an object from a defined Buffer and
 * writes them to a PrintStream
 * 
 * @author Kevin Schoergnhofer, Markus Seiwald
 *
 */
public class Consumer<T extends Comparable<T>> implements Runnable {
	private Buffer buffer;
	private PrintStream ps;
	private T item;

	public Consumer(Buffer buffer, PrintStream ps) {
		this.buffer = buffer;
		this.ps = ps;
	}

	/**
	 * lets the Consumer start its Job as defined in Class description
	 */
	public void run() {
		try {
			do {
				item = (T) buffer.take();
				ps.println(item);
			} while (!item.equals("DONE"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}