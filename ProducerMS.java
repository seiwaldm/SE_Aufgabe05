import java.io.BufferedReader;
import java.io.IOException;

public class ProducerMS implements Runnable {
	private BufferMS buffer;
	private BufferedReader file;

	public ProducerMS(BufferMS buffer, BufferedReader file) {
		this.buffer = buffer;
		this.file = file;
	}

	public void run() {
		BufferedReader br = new BufferedReader(file);
		String item;
		try {
			item = file.readLine();
			while (item != null) {
				buffer.put(item);
				item = file.readLine();
			}
			buffer.put("DONE");
			buffer.put("DONE");
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
