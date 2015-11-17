import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import javaDemo.Consumer;
import javaDemo.Producer;

public class DemoConcurrencyMS {

	public static void main(String[] args){

		BufferMS buffer = new BufferMS(10);
		BufferedReader input = null;
		try {
			FileInputStream fstream = new FileInputStream("Input.txt");
			input = new BufferedReader(new InputStreamReader(fstream));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		Thread prod = new Thread(new ProducerMS(buffer, input));
		Thread con1 = new Thread(new ConsumerMS(buffer, System.out));
		Thread con2 = new Thread(new ConsumerMS(buffer, System.err));
		
		prod.start();
		con1.start();
		con2.start();
		
	}
}
