/**
 * PS Software Engineering WS2015 <br>
 * <br>
 * 
 * Buffer Class to save instances of an object for further processing by a
 * Consumer
 * 
 * @author Kevin Schoergnhofer, Markus Seiwald
 *
 */
public class Buffer<T extends Comparable<T>> {

	private T[] items;
	private int producerCounter = 0;
	private int consumerCounter = 0;

	/**
	 * default constructor
	 * @param size sets the maximum number of items the Buffer can save at once
	 */
	public Buffer(int size) {
		this.items = (T[]) new Comparable[size];
	}

	protected synchronized void put(T item) throws InterruptedException {
		while (items[producerCounter] != null) {
			wait();
		}
		items[producerCounter] = item;
		producerCounter++;
		if (producerCounter == items.length)
			producerCounter = 0;
		notifyAll();
	}

	protected synchronized T take() throws InterruptedException {
		while (items[consumerCounter] == null) {
			wait();
		}
		T item = items[consumerCounter];
		items[consumerCounter] = null;
		consumerCounter++;
		if (consumerCounter == items.length)
			consumerCounter = 0;
		notifyAll();
		return item;
	}
}