
public class BufferMS<T extends Comparable<T>> {

	private T[] items;
	private int producerCounter = 0;
	private int consumerCounter = 0;
	//boolean empty = true;

	public BufferMS(int size) {
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