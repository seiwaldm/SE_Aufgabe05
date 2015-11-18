import java.io.PrintStream;

public class ConsumerMS<T extends Comparable<T>> implements Runnable {
	private BufferMS buffer;
	private PrintStream ps;
	private T item;

	public ConsumerMS(BufferMS buffer, PrintStream ps) {
		this.buffer = buffer;
		this.ps = ps;
	}

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
