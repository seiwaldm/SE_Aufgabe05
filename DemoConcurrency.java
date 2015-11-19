import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * PS Software Engineering WS2015 <br>
 * <br>
 * 
 * Demo-Class to demonstrate functionality of Poducer - Buffer - Consumer
 * 
 * @author Kevin Schoergnhofer, Markus Seiwald
 *
 */
public class DemoConcurrency {

	public static void main(String[] args){

		Buffer buffer = new Buffer(10);
		BufferedReader input = null;
		String inputfilename = "Input.txt";
		try {
			FileInputStream fstream = new FileInputStream(inputfilename);
			input = new BufferedReader(new InputStreamReader(fstream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		
		Thread prod = new Thread(new Producer(buffer, input));
		Thread con1 = new Thread(new Consumer(buffer, System.out));
		Thread con2 = new Thread(new Consumer(buffer, System.err));
		
		prod.start();
		con1.start();
		con2.start();
	}
}