
public class ConsumerMS<T extends Comparable<T>> implements Runnable {
	private BufferMS buffer;
	private boolean errStream;
	private T item;

	public ConsumerMS(BufferMS buffer, boolean errStream) {
		this.buffer = buffer;
		this.errStream = errStream;
	}

	public void run() {
		try {
			do {
				item = (T) buffer.take();
				if (errStream)
					System.err.println(item);
				else
					System.out.println(item);
			} while (!item.equals("DONE"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
